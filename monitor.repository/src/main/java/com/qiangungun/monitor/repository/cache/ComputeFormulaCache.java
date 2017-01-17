/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qiangungun.monitor.common.enums.FormulaTypeEnum;
import com.qiangungun.monitor.common.model.ComputeFormula;
import com.qiangungun.monitor.dal.daointerface.ComputeFormulaDAO;
import com.qiangungun.monitor.dal.dataobject.ComputeFormulaDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MonitorFormulaCache.java, v0.1 2016年12月1日 下午2:18:23 czhou3306@gmail.com Exp $
 */
@Service("computeFormulaCache")
public class ComputeFormulaCache implements ICache {

    private static final Logger               logger               = LoggerFactory
                                                                       .getLogger(GatherFileCache.class);
    @Resource
    private ComputeFormulaDAO                 computeFormulaDAO;

    /**以fileId为key*/
    private Map<String, List<ComputeFormula>> formulaByIdMap       = new HashMap<String, List<ComputeFormula>>();

    /**以computeId为key*/
    private Map<String, ComputeFormula>       formulaByComputIdMap = new HashMap<String, ComputeFormula>();

    public void init() {
        logger.info("开始初始化任务缓存");
        loadData();
        logger.info("任务加载完成formulaByComputIdMap=" + formulaByComputIdMap);
    }

    public void refresh() {

        logger.info("开始刷新任务缓存");
        loadData();
        logger.info("任务刷新完成formulaByComputIdMap=" + formulaByComputIdMap);

    }

    public ComputeFormula get(String computeId) {

        return formulaByComputIdMap.get(computeId);
    }

    public List<ComputeFormula> getByFileId(String fileId) {

        List<ComputeFormula> list = formulaByIdMap.get(fileId);
        return list == null ? new ArrayList<ComputeFormula>() : list;
    }

    public Collection<ComputeFormula> getDistinctKeyByFileId(String fileId) {

        List<ComputeFormula> list = formulaByIdMap.get(fileId);

        if (list == null) {
            return new ArrayList<ComputeFormula>();
        }

        Map<String, ComputeFormula> temp = new HashMap<String, ComputeFormula>();
        for (ComputeFormula cf : list) {
            temp.put(cf.getComputeKey(), cf);
        }

        return temp.values();

    }

    /**
     * 
     */
    private void loadData() {
        List<ComputeFormulaDO> configList = computeFormulaDAO.getAll();

        Map<String, List<ComputeFormula>> tempFormulaMap = new HashMap<String, List<ComputeFormula>>();

        Map<String, ComputeFormula> tempComputeMap = new HashMap<String, ComputeFormula>();

        for (ComputeFormulaDO formulaDO : configList) {
            ComputeFormula computeFormula = parse(formulaDO);
            tempComputeMap.put(computeFormula.getComputeId(), computeFormula);

            List<ComputeFormula> exist = tempFormulaMap.get(formulaDO.getFileId());
            if (exist == null) {
                exist = new ArrayList<ComputeFormula>();
                tempFormulaMap.put(formulaDO.getFileId(), exist);
            }
            exist.add(computeFormula);

        }
        formulaByIdMap = tempFormulaMap;
        formulaByComputIdMap = tempComputeMap;
    }

    private ComputeFormula parse(ComputeFormulaDO formulaDO) {

        ComputeFormula cf = new ComputeFormula();
        cf.setFileId(formulaDO.getFileId());
        cf.setComputeId(formulaDO.getComputeId());
        cf.setComputeName(formulaDO.getComputeName());
        cf.setFormulaType(FormulaTypeEnum.getByCode(formulaDO.getFormulaType()));
        if (cf.getFormulaType() == FormulaTypeEnum.SUM) {
            cf.setComputeFieldPosition(Integer.parseInt(formulaDO.getFieldPosition().substring(1)));
        }

        String[] fieldConditions = StringUtils.split(formulaDO.getFilterCondition(), ",");
        if (fieldConditions != null) {
            for (String fieldCondition : fieldConditions) {
                cf.getFilter().put(new Integer(fieldCondition.split("=")[0].substring(1)),
                    fieldCondition.split("=")[1]);
            }
        }

        return cf;
    }

}
