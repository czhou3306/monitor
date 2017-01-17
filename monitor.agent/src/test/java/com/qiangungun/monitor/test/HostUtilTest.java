/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import junit.framework.TestCase;

import com.qiangungun.monitor.agent.util.HostUtil;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: HostUtilTest.java, v0.1 2016年11月28日 下午5:20:46 czhou3306@gmail.com Exp $
 */
public class HostUtilTest extends TestCase {

    public void testh() {
        long l = System.currentTimeMillis();
        System.err.println(HostUtil.getIpAddress());
        System.err.println(System.currentTimeMillis()-l);
        System.err.println(HostUtil.getIpAddress());
        System.err.println(System.currentTimeMillis()-l);
        System.err.println(HostUtil.getHostName());
        System.err.println(System.currentTimeMillis()-l);
    }

}
