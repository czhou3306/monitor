/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiangungun.monitor.common.enums.BizCodeEnum;
import com.qiangungun.monitor.common.exceptions.MonitorException;
import com.qiangungun.monitor.dal.daointerface.ComputeFormulaDAO;
import com.qiangungun.monitor.dal.dataobject.ComputeFormulaDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ComputeFormulaRepositoryImpl.java, v0.1 2016年12月6日 下午5:12:20 czhou3306@gmail.com Exp $
 */
@Service("computeFormulaRepository")
public class ComputeFormulaRepositoryImpl implements ComputeFormulaRepository {

    @Resource
    private ComputeFormulaDAO computeFormulaDAO;

    /** 
     * @see com.qiangungun.monitor.repository.ComputeFormulaRepository#insert(com.qiangungun.monitor.dal.dataobject.ComputeFormulaDO)
     */
    @Override
    public void insert(ComputeFormulaDO computeFormulaDO) {

        try {
            computeFormulaDAO.insert(computeFormulaDO);
        } catch (Exception e) {
            throw new MonitorException(BizCodeEnum.DB_ACCESS_ERROR, e);
        }

    }

    /** 
     * @see com.qiangungun.monitor.repository.ComputeFormulaRepository#update(com.qiangungun.monitor.dal.dataobject.ComputeFormulaDO)
     */
    @Override
    public void update(ComputeFormulaDO computeFormulaDO) {

        try {
            int rows = computeFormulaDAO.update(computeFormulaDO.getComputeName(),
                computeFormulaDO.getFormulaType(), computeFormulaDO.getFieldPosition(),
                computeFormulaDO.getFilterCondition(), computeFormulaDO.getComputeId());
            if (rows < 1) {
                throw new MonitorException(BizCodeEnum.UPDATE_ROWS_ERROR);
            }
        } catch (Exception e) {
            throw new MonitorException(BizCodeEnum.DB_ACCESS_ERROR, e);
        }

    }

}
