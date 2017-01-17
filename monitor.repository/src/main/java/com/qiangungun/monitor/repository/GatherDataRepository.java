/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import java.util.List;

import com.qiangungun.monitor.common.model.GatherData;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherDataRepository.java, v0.1 2016年11月22日 下午3:03:24 czhou3306@gmail.com Exp $
 */
public interface GatherDataRepository {

    void batchSave(List<GatherData> gatherDataList);
    
    

}
