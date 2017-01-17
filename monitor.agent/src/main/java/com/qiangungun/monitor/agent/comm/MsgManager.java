/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.comm;

import java.util.Map;

import com.qiangungun.monitor.agent.model.BaseMsg;
import com.qiangungun.monitor.agent.model.FixedConcurrentHashMap;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MsgManager.java, v0.1 2016年11月20日 下午2:55:22 czhou3306@gmail.com Exp $
 */
public class MsgManager {

    private static Map<String, BaseMsg> sentMap    = new FixedConcurrentHashMap<String, BaseMsg>(
                                                       100);

    private static Map<String, BaseMsg> returnMap  = new FixedConcurrentHashMap<String, BaseMsg>(
                                                       100);
    private static MsgManager           msgManager = new MsgManager();

    private MsgManager() {

    }

    public static MsgManager instance() {

        return msgManager;
    }

    public void addReturnMsg(BaseMsg baseMsg) {
        returnMap.put(baseMsg.getMsgId(), baseMsg);
    }

    public BaseMsg getReturnMsg(String msgId) {
        return returnMap.remove(msgId);
    }

    public void addSentMsg(BaseMsg baseMsg) {
        sentMap.put(baseMsg.getMsgId(), baseMsg);
    }

    public BaseMsg getSentMsg(String msgId) {
        return sentMap.remove(msgId);
    }
}
