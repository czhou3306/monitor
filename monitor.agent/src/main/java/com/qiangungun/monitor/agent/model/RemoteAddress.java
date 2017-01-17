/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: RemoteAddress.java, v0.1 2016年11月20日 下午4:54:05 czhou3306@gmail.com Exp $
 */
public class RemoteAddress {

    public RemoteAddress(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private String ip;

    private int    port;

    /**
     * Getter method for property <tt>ip</tt>.
     * 
     * @return property value of ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Setter method for property <tt>ip</tt>.
     * 
     * @param ip value to be assigned to property ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Getter method for property <tt>port</tt>.
     * 
     * @return property value of port
     */
    public int getPort() {
        return port;
    }

    /**
     * Setter method for property <tt>port</tt>.
     * 
     * @param port value to be assigned to property port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RemoteAddress [ip=" + ip + ", port=" + port + "]";
    }

}
