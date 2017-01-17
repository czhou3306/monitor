/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import com.qiangungun.monitor.dal.dataobject.GatherFileDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherFileRepository.java, v0.1 2016年11月30日 下午2:14:25 czhou3306@gmail.com Exp $
 */
public interface GatherFileRepository {

    void insert(GatherFileDO gatherFileDO);
    
    void  update(GatherFileDO gatherFileDO);
}
