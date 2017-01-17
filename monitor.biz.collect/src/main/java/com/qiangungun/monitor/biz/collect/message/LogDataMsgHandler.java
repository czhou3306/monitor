/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message;

import com.qiangungun.monitor.agent.model.AgentConstant;
import com.qiangungun.monitor.agent.model.BaseMsg;
import com.qiangungun.monitor.agent.model.LogDataMsg;
import com.qiangungun.monitor.biz.collect.message.split.SplitorFactory;
import com.qiangungun.monitor.common.enums.FormulaTypeEnum;
import com.qiangungun.monitor.common.model.*;
import com.qiangungun.monitor.common.util.CoreDateUtils;
import com.qiangungun.monitor.common.util.LockUtil;
import com.qiangungun.monitor.repository.GatherDataRepository;
import com.qiangungun.monitor.repository.cache.ComputeFormulaCache;
import com.qiangungun.monitor.repository.cache.GatherFileCache;
import com.qiangungun.monitor.repository.cache.MonitorFormulaCache;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author czhou3306@gmail.com
 * @version $Id: LogDataMsgHandler.java, v0.1 2016年11月22日 下午1:25:08 czhou3306@gmail.com Exp $
 */
@Service("logDataMsgHandler")
public class LogDataMsgHandler implements MessageHandler, InitializingBean, DisposableBean {

    private static final Logger     logger           = LoggerFactory
                                                         .getLogger(LogDataMsgHandler.class);

    private SplitorFactory          splitorFactory   = new SplitorFactory();

    @Resource
    private GatherFileCache         gatherFileCache;

    @Resource
    private ComputeFormulaCache     computeFormulaCache;

    @Resource
    private MonitorFormulaCache     monitorFormulaCache;

    @Resource
    private GatherDataRepository    gatherDataRepository;

    /**
     * 时间+ip+分组字段的值为key
     */
    private Map<String, GatherData> gatherDataMap    = new ConcurrentHashMap<String, GatherData>();

    private Map<String, GatherData> exceptionDataMap = new ConcurrentHashMap<String, GatherData>();

    /**
     * queue长度大于这个数的时候就会把前1000个写到文件里
     */
    private final static int        CLEAR_QUEUE_SIZE = 1000;

    private ExecutorService         executorService  = Executors.newFixedThreadPool(20);

    /**
     * @see com.qiangungun.monitor.biz.collect.message.MessageHandler#handle(com.qiangungun.monitor.agent.model.BaseMsg)
     */
    @Override
    public BaseMsg handle(final BaseMsg baseMsg) {

        //用线程池异步处理,不占用netty的接收线程
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                LogDataMsg logData = (LogDataMsg) baseMsg;
                try {
                    GatherFile gatherFile = gatherFileCache.getByFileId(logData.getFileId());
                    if (gatherFile == null) {
                        logger.warn("该文件还不在监控范围内,logData=" + logData);
                        return;
                    }

                    if (StringUtils.equals(AgentConstant.GATHER_LINE, gatherFile.getGatherType())) {
                        processLineData(logData, gatherFile);
                    } else if (StringUtils.equals(AgentConstant.GATHER_ERROR,
                        gatherFile.getGatherType())) {
                        processExceptionTrace(logData, gatherFile);
                    }

                } catch (Exception e) {
                    logger.error("保存数据异常logData=" + logData, e);
                }
            }

            private void processExceptionTrace(LogDataMsg logData, GatherFile gatherFile)
                                                                                         throws InterruptedException {
                //分拆数据
                String[] dataArray = splitorFactory.split(gatherFile, logData.getLineData());

                Date occureDate = CoreDateUtils.parseToDate(dataArray[0],
                    CoreDateUtils.STANDARD_FORMAT);

                ErrorGatherData data = new ErrorGatherData(logData.getApp(), logData.getFileId(),
                    occureDate, logData.getHostName(), logData.getIp(), dataArray[1]);

                String dataKey = data.getExceptionTrace();
                Lock lock = LockUtil.getLock(dataKey);
                try {
                    //锁住id
                    lock.lock();

                    ErrorGatherData existData = (ErrorGatherData) exceptionDataMap.get(dataKey);

                    if (existData != null) {
                        existData.getCount().getAndIncrement();
                    } else {
                        data.getCount().getAndIncrement();
                        exceptionDataMap.put(dataKey, data);
                    }

                } catch (Exception e) {
                    logger.error("拿不到锁，无法保存异常数据logData=" + logData, e);
                } finally {
                    lock.unlock();
                }

                if (exceptionDataMap.size() >= CLEAR_QUEUE_SIZE) {
                    writeFromQueueToFile(exceptionDataMap);
                }
            }

            /**
             *
             * @param logData
             * @param gatherFile
             * @throws InterruptedException
             */
            private void processLineData(LogDataMsg logData, GatherFile gatherFile)
                                                                                   throws InterruptedException {
                //分拆数据
                String[] dataArray = splitorFactory.split(gatherFile, logData.getLineData());

                LineGatherData gatherData = buildGatherData(logData, dataArray, gatherFile);
                String dataKey = buildDataKey(gatherData);

                Lock lock = LockUtil.getLock(dataKey);
                try {
                    lock.lock();
                    Collection<ComputeFormula> formulaList = computeFormulaCache
                        .getDistinctKeyByFileId(gatherData.getFileId());
                    LineGatherData existData = (LineGatherData) gatherDataMap.get(dataKey);

                    if (existData != null) {
                        compute(dataArray, existData, formulaList);
                    } else {
                        compute(dataArray, gatherData, formulaList);
                        gatherDataMap.put(dataKey, gatherData);
                    }

                } catch (Exception e) {
                    logger.error("保存数据失败logData=" + logData, e);
                } finally {
                    lock.unlock();
                }

                if (gatherDataMap.size() >= CLEAR_QUEUE_SIZE) {
                    writeFromQueueToFile(gatherDataMap);
                }
            }

            /**
             *
             * @param dataArray
             * @param gatherData
             * @param formulaList
             */
            private void compute(String[] dataArray, LineGatherData gatherData,
                                 Collection<ComputeFormula> formulaList) {
                gatherData.getCount().getAndIncrement();
                for (ComputeFormula formula : formulaList) {
                    if (formula.getFormulaType() == FormulaTypeEnum.SUM) {
                        BigDecimal temp = gatherData.getComputeMap().get(formula.getComputeKey());
                        temp = temp.add(new BigDecimal(dataArray[formula.getComputeFieldPosition()]));
                        gatherData.getComputeMap().put(formula.getComputeKey(), temp);
                    } else if (formula.getFormulaType() == FormulaTypeEnum.COUNT) {
                        BigDecimal temp = gatherData.getComputeMap().get(formula.getComputeKey());
                        temp = temp.add(new BigDecimal("1"));
                        gatherData.getComputeMap().put(formula.getComputeKey(), temp);
                    }
                }
            }

            private LineGatherData buildGatherData(LogDataMsg logData, String[] dataArray,
                                                   GatherFile gatherFile) {

                Date occureDate = CoreDateUtils.parseToDate(dataArray[0],
                    CoreDateUtils.STANDARD_FORMAT);

                Map<Integer, String> groupFieldValue = new TreeMap<Integer, String>();

                for (Integer i : gatherFile.getGroupField().keySet()) {
                    groupFieldValue.put(i, dataArray[i]);
                }

                LineGatherData data = new LineGatherData(logData.getApp(), gatherFile.getFileId(),
                    occureDate, logData.getHostName(), logData.getIp(), groupFieldValue,
                    computeFormulaCache.getDistinctKeyByFileId(gatherFile.getFileId()));

                return data;
            }

        });

        return null;

    }

    private String buildDataKey(LineGatherData gatherData) {
        String dataKey = gatherData.getDateValue() + gatherData.getGroupFieldValueString();
        return dataKey;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {

                logger.info("定时写数据文件");
                try {
                    flushData(gatherDataMap, 1);
                    flushData(exceptionDataMap, 1);
                } catch (Exception e) {
                    logger.error("定时保存数据异常", e);
                }
            }

        }, 0, 60, TimeUnit.SECONDS);
    }

    /**
     * @throws InterruptedException
     */
    private void flushData(Map<String, GatherData> gatherDataMap, int timeUnit)
                                                                               throws InterruptedException {
        if (MapUtils.isEmpty(gatherDataMap)) {
            return;
        }

        List<GatherData> list = new ArrayList<>(gatherDataMap.size());
        if (timeUnit == 0) {
            list.addAll(gatherDataMap.values());
        } else {
            for (Map.Entry<String, GatherData> entry : gatherDataMap.entrySet()) {
                final String key = entry.getKey();
                Lock lock = LockUtil.getLock(key);

                try {
                    lock.lock();
                    GatherData gatherData = entry.getValue();
                    Date baseLine = CoreDateUtils.addMinutes(new Date(), -timeUnit);
                    //1分钟以前的,全部写到文件里
                    if (gatherData.getOccureDate().before(baseLine)) {
                        list.add(gatherData);
                        gatherDataMap.remove(key);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        gatherDataRepository.batchSave(list);
    }

    private void writeFromQueueToFile(Map<String, GatherData> gatherDataMap)
                                                                            throws InterruptedException {
        if (MapUtils.isEmpty(gatherDataMap)) {
            return;
        }

        List<GatherData> list = new ArrayList<>(gatherDataMap.size());

        for (Map.Entry<String, GatherData> entry : gatherDataMap.entrySet()) {
            final String key = entry.getKey();
            Lock lock = LockUtil.getLock(key);

            try {
                lock.lock();
                GatherData gatherData = entry.getValue();
                if (null == gatherData) {
                    continue;
                }

                list.add(gatherData);
                gatherDataMap.remove(key);

                if (list.size() >= CLEAR_QUEUE_SIZE) {
                    break;
                }

            } finally {
                lock.unlock();
            }
        }

        gatherDataRepository.batchSave(list);
    }

    /**
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() throws Exception {

        logger.info("bean销毁了，把数据都保存到文件中");
        try {
            flushData(gatherDataMap, 0);
            flushData(exceptionDataMap, 0);
        } catch (Exception e) {
            logger.error("销毁保存数据异常", e);
        }

    }
}
