/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qiangungun.monitor.repository.cache.GatherFileCache;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: BusinessActivityServiceTest.java, v 0.1 2015年5月30日 下午3:44:31 czhou3306@gmail.com Exp $
 */
@ContextConfiguration(locations = { "classpath:biz-test-bean.xml",
                                   "classpath:META-INF/spring/spring-monitor-dal-dao.xml",
                                   "classpath:test-ds.xml" })
public class GatherFileTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private GatherFileCache gatherFileCache;

    @Test
    public void testNotNull() {
        Assert.assertNotNull(gatherFileCache);
    }

    @Test
    public void testSplit() throws Exception {
        System.err.println(gatherFileCache.getByFileId("3"));
    }

}
