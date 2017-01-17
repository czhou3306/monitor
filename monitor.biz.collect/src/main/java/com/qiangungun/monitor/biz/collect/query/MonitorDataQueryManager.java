/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.query;

import java.util.Date;
import java.util.List;

import com.qiangungun.monitor.common.model.LineComputeData;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: QuotaQueryManager.java, v0.1 2016年12月15日 下午4:48:01 czhou3306@gmail.com Exp $
 */
public interface MonitorDataQueryManager {

    List<LineComputeData> query(String fileId, Date beginDate, Date endDate);
}
