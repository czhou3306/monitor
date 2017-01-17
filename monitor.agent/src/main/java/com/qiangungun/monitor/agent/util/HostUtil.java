/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: HostUtil.java, v 0.1 2015-2-11 下午10:43:54 czhou3306@gmail.com Exp $
 */
public class HostUtil {

    private static final Logger logger = LoggerFactory.getLogger(HostUtil.class);

    private static String       hostName;

    private static String       ipAddress;

    static {
        try {
            getIpAndHostName();
        } catch (Exception e) {
            //FIXME 这里挂了怎么办？
            // TODO: handle exception
        }
    }

    public static String getHostName() {
        if (hostName == null || hostName.trim() == "") {
            try {
                getIpAndHostName();
                return hostName;
            } catch (Exception e) {
                //FIXME 这里挂了怎么办？
                // TODO: handle exception
            }
        }
        return hostName;
    }

    public static String getIpAddress() {
        if (ipAddress == null || ipAddress.trim() == "") {
            try {
                getIpAndHostName();
                return ipAddress;
            } catch (Exception e) {
                //FIXME 这里挂了怎么办？
                logger.error("get ip error", e);
            }
        }
        return ipAddress;
    }

    private static void getIpAndHostName() {

        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
            if (!StringUtils.equals(ipAddress, "127.0.0.1")) {
                hostName = InetAddress.getLocalHost().getCanonicalHostName();
                return;
            }

            Enumeration<NetworkInterface> e1 = (Enumeration<NetworkInterface>) NetworkInterface
                .getNetworkInterfaces();

            while (e1.hasMoreElements()) {
                NetworkInterface ni = e1.nextElement();

                //单网卡或者绑定双网卡  
                if (("eth0".equals(ni.getName()))) {
                    Enumeration<InetAddress> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = e2.nextElement();
                        if (ia instanceof Inet6Address) {
                            continue;
                        }
                        ipAddress = ia.getHostAddress();
                        hostName = ia.getHostName();
                    }
                    break;
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            logger.error("获取本地地址异常", e);
        }

    }

}
