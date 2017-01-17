/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.StringUtils;

import com.qiangungun.monitor.biz.collect.message.split.RegularSplitor;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: RegularSplitorTest.java, v0.1 2016年11月25日 下午6:18:43 czhou3306@gmail.com Exp $
 */
public class RegularSplitorTest extends TestCase {

    public void testp() {

        List<String> list = new ArrayList<String>();

        RegularSplitor splitor = new RegularSplitor();
        List<String> l = new ArrayList<String>();
        l.add(",");
        l.add("ms");
        l.add(">");

        String[] result = splitor
            .split(
                "2016-12-21 15:52:31,219 INFO [http-bio-8080-exec-5] [LogUtil.java : 31] <uuid=205e0a83ad6b2b109d000371>/v1/wxconfig,,,000000,110ms",
                l);

        for (String s : result) {
            System.err.println("1" + s);
        }
    }

}
