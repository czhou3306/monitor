/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qiangungun.monitor.common.model.LineComputeData;
import com.qiangungun.monitor.repository.cache.ComputeFormulaCache;
import com.qiangungun.monitor.repository.cache.MonitorFormulaCache;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ComputeFormulaCache.java, v0.1 2016年11月30日 下午1:36:03 czhou3306@gmail.com Exp $
 */
@ContextConfiguration(locations = { "classpath:biz-test-bean.xml",
                                   "classpath:META-INF/spring/spring-monitor-dal-dao.xml",
                                   "classpath:test-ds.xml" })
public class ComputeFormulaCacheTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ComputeFormulaCache computeFormulaCache;

    @Autowired
    private MonitorFormulaCache monitorFormulaCache;

    @Test
    public void testNotNull() {
        Assert.assertNotNull(computeFormulaCache);
    }

    @Test
    public void testSplit() throws Exception {
        String[] array = StringUtils.split(
            "201612041205|6:BusinessActivityDAO.updateStatus|7:S|C:30|S-8:150|C-0:100|", "|");
        new LineComputeData(array, monitorFormulaCache.get("1").getComputeList());
    }

}
