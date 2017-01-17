/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.common.util;

import java.net.InetAddress;

import org.apache.commons.lang.StringUtils;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: HostUtil.java, v 0.1 2015-2-11 下午10:43:54 czhou3306@gmail.com Exp $
 */
public class HostUtil {

    private static String hostName;

    private static String ipAddress;

    static {
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            //FIXME 这里挂了怎么办？
            // TODO: handle exception
        }
    }

    public static String getHostName() {
        if (StringUtils.isBlank(hostName)) {
            try {
                hostName = InetAddress.getLocalHost().getCanonicalHostName();
            } catch (Exception e) {
                //FIXME 这里挂了怎么办？
                // TODO: handle exception
            }
        }
        return hostName;
    }

    public static String getIpAddress() {
        if (StringUtils.isBlank(ipAddress)) {
            try {
                ipAddress = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {
                //FIXME 这里挂了怎么办？
                // TODO: handle exception
            }
        }
        return ipAddress;
    }

}
