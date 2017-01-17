/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qiangungun.monitor.common.enums.BizCodeEnum;
import com.qiangungun.monitor.common.exceptions.MonitorException;
import com.qiangungun.monitor.dal.daointerface.MonitorFormulaDAO;
import com.qiangungun.monitor.dal.dataobject.MonitorFormulaDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MonitorFormulaRepositoryImpl.java, v0.1 2016年12月6日 下午5:11:56 czhou3306@gmail.com Exp $
 */
@Service("monitorFormulaRepository")
public class MonitorFormulaRepositoryImpl implements MonitorFormulaRepository {

    private static final Logger logger = LoggerFactory
                                           .getLogger(MonitorFormulaRepositoryImpl.class);

    @Resource
    private MonitorFormulaDAO   monitorFormulaDAO;

    /** 
     * @see com.qiangungun.monitor.repository.MonitorFormulaRepository#insert(com.qiangungun.monitor.dal.dataobject.MonitorFormulaDO)
     */
    @Override
    public void insert(MonitorFormulaDO monitorFormulaDO) {

        try {
            monitorFormulaDAO.insert(monitorFormulaDO);
        } catch (Exception e) {
            logger.error("insert异常", e);
            throw new MonitorException(BizCodeEnum.DB_ACCESS_ERROR, e);
        }

    }

    /** 
     * @see com.qiangungun.monitor.repository.MonitorFormulaRepository#update(com.qiangungun.monitor.dal.dataobject.MonitorFormulaDO)
     */
    @Override
    public void update(MonitorFormulaDO monitorFormulaDO) {

        try {
            int rows = monitorFormulaDAO.update(monitorFormulaDO.getMonitorName(),
                monitorFormulaDO.getShowField(), monitorFormulaDO.getFormula(),
                monitorFormulaDO.getMonitorId());
            if (rows < 1) {
                throw new MonitorException(BizCodeEnum.UPDATE_ROWS_ERROR);
            }
        } catch (Exception e) {
            logger.error("update异常", e);
            throw new MonitorException(BizCodeEnum.DB_ACCESS_ERROR, e);
        }

    }

}
