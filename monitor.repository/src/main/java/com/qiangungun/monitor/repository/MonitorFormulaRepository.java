/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import com.qiangungun.monitor.dal.dataobject.MonitorFormulaDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MonitorFormulaRepository.java, v0.1 2016年12月6日 下午5:11:08 czhou3306@gmail.com Exp $
 */
public interface MonitorFormulaRepository {

    void insert(MonitorFormulaDO monitorFormulaDO);
    
    void update(MonitorFormulaDO monitorFormulaDO);
}
