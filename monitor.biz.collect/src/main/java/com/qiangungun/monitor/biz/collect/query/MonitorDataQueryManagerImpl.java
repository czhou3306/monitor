/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.query;

import com.qiangungun.monitor.biz.shared.helper.ComputeFormulaHelper;
import com.qiangungun.monitor.common.model.GatherFile;
import com.qiangungun.monitor.common.model.KeyConstant;
import com.qiangungun.monitor.common.model.LineComputeData;
import com.qiangungun.monitor.common.util.CoreDateUtils;
import com.qiangungun.monitor.common.util.LineFromEndReader;
import com.qiangungun.monitor.repository.cache.ComputeFormulaCache;
import com.qiangungun.monitor.repository.cache.GatherFileCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: QuotaQueryManager.java, v0.1 2016年12月15日 下午4:49:03 czhou3306@gmail.com Exp $
 */
@Service("monitorDataQueryManager")
public class MonitorDataQueryManagerImpl implements MonitorDataQueryManager {

    private static final Logger  logger        = LoggerFactory
                                                   .getLogger(MonitorDataQueryManagerImpl.class);

    private final static String  DATA_ROOT_DIR = "/opt/logs/data/";

    @Resource
    private GatherFileCache      gatherFileCache;

    @Resource
    private ComputeFormulaCache  computeFormulaCache;

    @Resource
    private ComputeFormulaHelper computeFormulaHelper;

    private ExecutorService      es            = Executors.newFixedThreadPool(20);

    /** 
     * @see com.qiangungun.monitor.biz.collect.query.MonitorDataQueryManager#query(java.lang.String, java.util.Date, java.util.Date)
     */
    @Override
    public List<LineComputeData> query(String fileId, Date beginDate, Date endDate) {
        try {

            GatherFile gatherFile = gatherFileCache.getByFileId(fileId);
            String beginDateStr = CoreDateUtils.yyyymmdd(beginDate);
            String endDateStr = CoreDateUtils.yyyymmdd(endDate);

            //获得需要计算的文件所在的目录
            File dir = new File(DATA_ROOT_DIR + endDateStr + "/" + gatherFile.getSystemName() + "."
                                + fileId);
            if (!dir.exists()) {
                return new ArrayList<LineComputeData>();
            }

            //使用多线程进行计算
            List<Future<Map<String, LineComputeData>>> computeFutureList = computeDirData(fileId,
                dir, gatherFile.getGroupField().keySet(), beginDate, endDate);
            if (!StringUtils.equals(beginDateStr, endDateStr)) {
                dir = new File(DATA_ROOT_DIR + beginDateStr + "/" + gatherFile.getSystemName()
                               + "." + fileId);
                computeFutureList.addAll(computeDirData(fileId, dir, gatherFile.getGroupField()
                    .keySet(), beginDate, endDate));
            }

            List<LineComputeData> list = mergeResult(computeFutureList);
            Collections.sort(list);
            return list;
        } catch (Exception e) {
            logger.error("查询监控数据异常fileId=" + fileId, e);
            return new ArrayList<LineComputeData>();
        }
    }

    /**
     * 
     * @param computeFutureList
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private List<LineComputeData> mergeResult(List<Future<Map<String, LineComputeData>>> computeFutureList)
                                                                                                           throws InterruptedException,
                                                                                                           ExecutionException {
        Map<String, LineComputeData> finResultMap = new HashMap<String, LineComputeData>();
        for (Future<Map<String, LineComputeData>> f : computeFutureList) {
            Map<String, LineComputeData> result = f.get();

            Set<String> keys = result.keySet();
            for (String key : keys) {
                LineComputeData exist = finResultMap.get(key);
                LineComputeData temp = result.get(key);
                if (exist != null) {
                    Set<String> keySet = exist.getComputeMap().keySet();
                    for (String computeKey : keySet) {
                        BigDecimal nowData = exist.getComputeMap().get(computeKey);
                        BigDecimal newData = temp.getComputeMap().get(computeKey);
                        BigDecimal sumData = nowData.add(newData);
                        exist.getComputeMap().put(computeKey, sumData);
                    }
                } else {
                    finResultMap.put(key, temp);
                }
            }
        }

        return new ArrayList<LineComputeData>(finResultMap.values());
    }

    /**
     * 
     * @param terminalDate
     * @param dir
     * @param fl
     */
    private List<Future<Map<String, LineComputeData>>> computeDirData(final String fileId,
                                                                      File dir,
                                                                      final Collection<Integer> positionList,
                                                                      final Date beginDate,
                                                                      final Date endDate) {

        //因为是倒序读取，如果超过起始时间5分钟了，认为前面的数据基本不会再有有效的数据
        final Date terminalDate = CoreDateUtils.addMinutes(beginDate, -5);

        List<Future<Map<String, LineComputeData>>> fl = new ArrayList<Future<Map<String, LineComputeData>>>();
        for (final File file : dir.listFiles()) {
            Future<Map<String, LineComputeData>> f = es
                .submit(new Callable<Map<String, LineComputeData>>() {

                    @Override
                    public Map<String, LineComputeData> call() throws Exception {

                        Map<String, LineComputeData> result = new HashMap<String, LineComputeData>();
                        LineFromEndReader reader = new LineFromEndReader(file);
                        String line = null;
                        while ((line = reader.readLineHaveContent()) != null) {
                            try {
                                String[] array = StringUtils.split(line, KeyConstant.SPLIT_FLAG);

                                Date occureDate = CoreDateUtils.parseToDate(array[0],
                                    KeyConstant.LINE_DATE_FORMAT);

                                if (occureDate.before(terminalDate)) {
                                    break;
                                }

                                if (occureDate.before(beginDate) || occureDate.after(endDate)) {
                                    continue;
                                }

                                LineComputeData ld = new LineComputeData(array, computeFormulaCache
                                    .getByFileId(fileId));

                                String key = ld.getDate();
                                for (Integer i : positionList) {
                                    key = key + ld.getFieldValue().get(i);
                                }

                                LineComputeData exist = result.get(key);

                                if (exist == null) {
                                    result.put(key, ld);
                                } else {
                                    Set<String> keySet = exist.getComputeMap().keySet();
                                    for (String computeId : keySet) {
                                        //通过条件判断才计算
                                        BigDecimal nowData = exist.getComputeMap().get(computeId);
                                        BigDecimal newData = ld.getComputeMap().get(computeId);
                                        BigDecimal sumData = nowData.add(newData);
                                        exist.getComputeMap().put(computeId, sumData);

                                    }
                                }
                            } catch (Exception e) {
                                logger.error("line=" + line, e);
                            }
                        }
                        reader.close();
                        return result;
                    }

                });

            fl.add(f);
        }

        return fl;
    }
}
