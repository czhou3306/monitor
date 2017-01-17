/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: PackageLogDataMsg.java, v0.1 2016年12月22日 下午2:34:11 czhou3306@gmail.com Exp $
 */
public class PackageLogDataMsg extends BaseMsg {

    /**  */
    private static final long serialVersionUID = -5021618146389201948L;

    private List<LogDataMsg>  msgList          = new ArrayList<LogDataMsg>();

    /**
     * @param msgType
     */
    public PackageLogDataMsg() {
        super(MsgType.PACKAGE_DATA);
    }

    /**
     * Getter method for property <tt>msgList</tt>.
     * 
     * @return property value of msgList
     */
    public List<LogDataMsg> getMsgList() {
        return msgList;
    }

    /**
     * Setter method for property <tt>msgList</tt>.
     * 
     * @param msgList value to be assigned to property msgList
     */
    public void setMsgList(List<LogDataMsg> msgList) {
        this.msgList = msgList;
    }

}
