/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import com.qiangungun.monitor.biz.collect.message.split.AccessLogSplitor;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: AccessLogSplitorTest.java, v0.1 2016年12月4日 下午6:22:02 czhou3306@gmail.com Exp $
 */
public class AccessLogSplitorTest extends TestCase{

    public void testaa(){
        AccessLogSplitor splitor = new AccessLogSplitor();
        String[] arr = splitor.split("172.16.160.60 - - [12/Dec/2016:14:57:47 +0800] \"POST /v1/product/detail HTTP/1.0\" 200 2846", null);
        for(String s:arr){
            System.err.println(s);
        }
    }
}
