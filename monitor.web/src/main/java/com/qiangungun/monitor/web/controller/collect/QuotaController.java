/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.controller.collect;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiangungun.monitor.biz.shared.helper.ComputeFormulaHelper;
import com.qiangungun.monitor.common.enums.BizCodeEnum;
import com.qiangungun.monitor.common.enums.TimeGroupEnum;
import com.qiangungun.monitor.common.model.ComputeFormula;
import com.qiangungun.monitor.common.model.GatherFile;
import com.qiangungun.monitor.common.model.KeyConstant;
import com.qiangungun.monitor.common.model.LineComputeData;
import com.qiangungun.monitor.common.model.MonitorFormula;
import com.qiangungun.monitor.common.util.CoreDateUtils;
import com.qiangungun.monitor.common.util.LineFromEndReader;
import com.qiangungun.monitor.repository.cache.GatherFileCache;
import com.qiangungun.monitor.repository.cache.MonitorFormulaCache;
import com.qiangungun.monitor.web.controller.BaseController;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: QuotaController.java, v0.1 2016年12月1日 下午3:24:33 czhou3306@gmail.com Exp $
 */
@Controller
public class QuotaController extends BaseController {

    private static final Logger  logger = LoggerFactory.getLogger(QuotaController.class);

    @Resource
    private MonitorFormulaCache  monitorFormulaCache;

    @Resource
    private GatherFileCache      gatherFileCache;

    @Resource
    private ComputeFormulaHelper computeFormulaHelper;

    ExecutorService              es     = Executors.newFixedThreadPool(100);

    @ResponseBody
    @RequestMapping(value = "/quota/query.html", method = RequestMethod.GET)
    public String split(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam String monitorId, @RequestParam String beginDate,
                        @RequestParam String endDate,
                        final @RequestParam(required = false) String timeGroup) throws Exception {

        final MonitorFormula mf = monitorFormulaCache.get(monitorId);
        GatherFile gatherFile = gatherFileCache.getByFileId(mf.getFileId());
        final Date begin = CoreDateUtils.yyyymmddhh24miss(beginDate);
        final Long beginLong = Long.valueOf(beginDate);

        final Date end = CoreDateUtils.yyyymmddhh24miss(endDate);
        final Long endLong = Long.valueOf(endDate);
        //final Date terminalDate = CoreDateUtils.addMinutes(begin, -5);

        File dir = new File("/opt/logs/data/" + CoreDateUtils.yyyymmdd(end) + "/"
                            + gatherFile.getSystemName() + "." + gatherFile.getFileId());

        if (!dir.exists()) {
            return newErrorResult(BizCodeEnum.NO_DATA);
        }

        List<Future<Map<String, LineComputeData>>> fl = new ArrayList<Future<Map<String, LineComputeData>>>();

        for (final File file : dir.listFiles()) {
            Future<Map<String, LineComputeData>> f = es
                .submit(new Callable<Map<String, LineComputeData>>() {

                    @Override
                    public Map<String, LineComputeData> call() throws Exception {

                        int count = 0;
                        long start = System.currentTimeMillis();
                        Map<String, LineComputeData> result = new HashMap<String, LineComputeData>();
                        LineFromEndReader reader = null;

                        try {
                            reader = new LineFromEndReader(file);
                            String line = null;
                            while ((line = reader.readLineHaveContent()) != null) {
                                try {
                                    count++;
                                    String[] array = StringUtils
                                        .split(line, KeyConstant.SPLIT_FLAG);

                                    /* Date occureDate = CoreDateUtils.parseToDate(array[0],
                                         KeyConstant.LINE_DATE_FORMAT);*/
                                    Long occureDateLong = Long.valueOf(array[0] + "00");

                                    //因为是倒序读取，如果超过起始时间5分钟了，认为上面的数据基本不会再有有效的数据
                                    //if (occureDate.before(terminalDate)) {
                                    if (occureDateLong < beginLong - 5) {
                                        break;
                                    }

                                    if (occureDateLong < beginLong || occureDateLong > endLong) {
                                        continue;
                                    }

                                    LineComputeData ld = new LineComputeData(array, TimeGroupEnum
                                        .getByCode(timeGroup), mf.getComputeList());

                                    String key = ld.getDate();
                                    for (Integer i : mf.getShowFields()) {
                                        key = key + ld.getFieldValue().get(i);
                                    }

                                    LineComputeData exist = result.get(key);

                                    if (exist == null) {
                                        result.put(key, ld);
                                    } else {
                                        Set<String> keySet = exist.getComputeMap().keySet();
                                        for (String computeId : keySet) {
                                            //通过条件判断才计算
                                            BigDecimal nowData = exist.getComputeMap().get(
                                                computeId);
                                            BigDecimal newData = ld.getComputeMap().get(computeId);
                                            BigDecimal sumData = nowData.add(newData);
                                            exist.getComputeMap().put(computeId, sumData);

                                        }
                                    }
                                } catch (Exception e) {
                                    logger.error("line=" + line, e);
                                }
                            }
                        } finally {
                            if (reader != null) {
                                reader.close();
                            }
                        }
                        logger.info("单个文件处理完成time=" + (System.currentTimeMillis() - start)
                                    + ",count=" + count);
                        return result;
                    }

                });

            fl.add(f);
        }

        Map<String, LineComputeData> finResultMap = new HashMap<String, LineComputeData>();
        for (Future<Map<String, LineComputeData>> f : fl) {
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

        List<Object[]> finResultList = new ArrayList<Object[]>();

        List<LineComputeData> list = new ArrayList<LineComputeData>(finResultMap.values());

        Collections.sort(list);

        List<Integer> positionList = mf.getShowFields();

        for (LineComputeData ld : list) {
            List<Object> temp = new ArrayList<Object>();
            temp.add(ld.getDate());
            for (Integer position : positionList) {
                temp.add(ld.getFieldValue().get(position));
            }
            temp.addAll(ld.getComputeMap().values());
            temp.add(computeFormulaHelper.compute(mf.getFormula(), ld));
            finResultList.add(temp.toArray());
        }

        List<String> colNames = new ArrayList<String>();
        colNames.add("时间");
        for (Integer position : positionList) {
            colNames.add(gatherFile.getGroupField().get(position));
        }
        for (ComputeFormula cf : mf.getComputeList()) {
            colNames.add(cf.getComputeName());
        }
        colNames.add(mf.getMonitorName());

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("colNames", colNames);
        result.put("records", finResultList);

        return newSuccessResult(result);
    }
}
