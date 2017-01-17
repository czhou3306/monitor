/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import com.qiangungun.monitor.dal.dataobject.ComputeFormulaDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ComputeFormulaRepository.java, v0.1 2016年12月6日 下午5:10:12 czhou3306@gmail.com Exp $
 */
public interface ComputeFormulaRepository {

    public void insert(ComputeFormulaDO computeFormulaDO);
    
    public void update(ComputeFormulaDO computeFormulaDO);
}
