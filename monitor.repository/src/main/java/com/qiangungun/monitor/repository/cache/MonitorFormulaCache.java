/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qiangungun.monitor.common.model.MonitorFormula;
import com.qiangungun.monitor.dal.daointerface.MonitorFormulaDAO;
import com.qiangungun.monitor.dal.dataobject.MonitorFormulaDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MonitorFormulaCache.java, v0.1 2016年12月1日 下午2:18:23 czhou3306@gmail.com Exp $
 */
@Service("monitorFormulaCache")
public class MonitorFormulaCache implements ICache {

    private static final Logger         logger     = LoggerFactory.getLogger(GatherFileCache.class);
    @Resource
    private MonitorFormulaDAO           monitorFormulaDAO;

    @Resource
    private ComputeFormulaCache         computeFormulaCache;

    /**以monitorid为key*/
    private Map<String, MonitorFormula> monitorMap = new HashMap<String, MonitorFormula>();

    public void init() {
        logger.info("开始初始化任务缓存");
        loadData();
        logger.info("任务加载完成monitorMap=" + monitorMap);
    }

    public void refresh() {

        logger.info("开始刷新任务缓存");
        loadData();
        logger.info("任务刷新完成monitorMap=" + monitorMap);

    }

    public MonitorFormula get(String monitorId) {

        return monitorMap.get(monitorId);
    }

    /**
     * 
     */
    private void loadData() {
        List<MonitorFormulaDO> configList = monitorFormulaDAO.getAll();

        Map<String, MonitorFormula> tempMonitorMap = new HashMap<String, MonitorFormula>();

        for (MonitorFormulaDO formulaDO : configList) {
            MonitorFormula monitorFormula = parse(formulaDO);
            tempMonitorMap.put(monitorFormula.getMonitorId(), monitorFormula);
        }
        monitorMap = tempMonitorMap;
    }

    private MonitorFormula parse(MonitorFormulaDO formulaDO) {

        MonitorFormula mf = new MonitorFormula();
        mf.setFileId(formulaDO.getFileId());
        mf.setMonitorId(formulaDO.getMonitorId());
        mf.setMonitorName(formulaDO.getMonitorName());
        mf.setFormula(formulaDO.getFormula());

        List<Integer> list = new ArrayList<Integer>();
        String[] fieldArray = StringUtils.split(formulaDO.getShowField(), ",");
        for (String field : fieldArray) {
            list.add(Integer.parseInt(field.substring(1)));
        }
        mf.setShowFields(list);

        String[] computeIds = StringUtils.substringsBetween(formulaDO.getFormula(), "#", "#");

        for (String computeId : computeIds) {
            mf.getComputeList().add(computeFormulaCache.get(computeId));
        }

        return mf;
    }

}
