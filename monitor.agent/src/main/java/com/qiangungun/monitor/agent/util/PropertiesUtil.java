/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.qiangungun.monitor.agent.model.AgentConstant;
import com.qiangungun.monitor.agent.model.RemoteAddress;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: PropertiesUtil.java, v0.1 2016年11月20日 下午11:29:11 czhou3306@gmail.com Exp $
 */
public class PropertiesUtil {

    private static Properties properties = new Properties();

    public static void load(String filePath) throws Exception {
        properties.load(new FileInputStream(filePath));
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static List<RemoteAddress> getAddressList() {
        List<RemoteAddress> addressList = new ArrayList<RemoteAddress>();
        String monitorcenter = properties.getProperty(AgentConstant.MONITOR_CENTER);
        String[] addressArray = monitorcenter.split(",");
        for (String address : addressArray) {
            String[] ipAndPort = address.split(":");
            addressList.add(new RemoteAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1])));
        }
        return addressList;
    }
}
