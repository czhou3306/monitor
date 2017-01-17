/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qiangungun.monitor.agent.util.HostUtil;
import com.qiangungun.monitor.agent.util.MsgIdGenerator;
import com.qiangungun.monitor.agent.util.PropertiesUtil;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: BaseMsg.java, v0.1 2016年11月19日 下午10:25:46 czhou3306@gmail.com Exp $
 */
public abstract class BaseMsg implements Serializable {

    /**  */
    private static final long serialVersionUID = 8733526853988886391L;

    private String            msgId;

    private MsgType           msgType;

    /**应用名字*/
    private String            app;

    /**服务器名称*/
    private String            hostName;

    private String            ip;

    protected BaseMsg(MsgType msgType) {
        this.msgType = msgType;
        this.msgId = MsgIdGenerator.generate();
        this.hostName = HostUtil.getHostName();
        this.ip = HostUtil.getIpAddress();
        this.app = PropertiesUtil.get(AgentConstant.APP);
    }

    /**
     * Getter method for property <tt>msgId</tt>.
     * 
     * @return property value of msgId
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * Setter method for property <tt>msgId</tt>.
     * 
     * @param msgId value to be assigned to property msgId
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * Getter method for property <tt>msgType</tt>.
     * 
     * @return property value of msgType
     */
    public MsgType getMsgType() {
        return msgType;
    }

    /**
     * Setter method for property <tt>msgType</tt>.
     * 
     * @param msgType value to be assigned to property msgType
     */
    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    /**
     * Getter method for property <tt>app</tt>.
     * 
     * @return property value of app
     */
    public String getApp() {
        return app;
    }

    /**
     * Setter method for property <tt>app</tt>.
     * 
     * @param app value to be assigned to property app
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * Getter method for property <tt>hostName</tt>.
     * 
     * @return property value of hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Setter method for property <tt>hostName</tt>.
     * 
     * @param hostName value to be assigned to property hostName
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
