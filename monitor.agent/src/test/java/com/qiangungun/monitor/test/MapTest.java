/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import junit.framework.TestCase;

import com.qiangungun.monitor.agent.model.RemoteAddress;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MapTest.java, v0.1 2016年11月19日 下午11:06:56 czhou3306@gmail.com Exp $
 */
public class MapTest extends TestCase {

    public void testS() throws Exception {
        Map<Integer, Object> map2 = new HashMap<Integer, Object>();
        map2.put(new Integer(1), "11");
        
        System.err.println(map2.get(new Integer(1)));

        String s = "/monitor/server/172.10";
        System.err.println(s.substring("/monitor/server".length() + 1, s.length()));

        RemoteAddress address = new RemoteAddress("1", 1);
        RemoteAddress address2 = new RemoteAddress("1", 1);

        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<Object, Object>();

        map.put(address, "1");
        map.put(address2, "2");
        System.err.println(map.size());

    }
}
