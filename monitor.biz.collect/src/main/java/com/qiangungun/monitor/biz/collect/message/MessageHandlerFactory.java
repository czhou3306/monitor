/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.qiangungun.monitor.agent.model.BaseMsg;
import com.qiangungun.monitor.agent.model.MsgType;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MessageHandlerFactory.java, v0.1 2016年11月22日 下午1:16:23 czhou3306@gmail.com Exp $
 */
@Service("messageHandlerFactory")
public class MessageHandlerFactory implements InitializingBean {

    @Resource
    private MessageHandler              heartMessageHandler;

    @Resource
    private MessageHandler              logDataMsgHandler;

    @Resource
    private MessageHandler              queryCommandRequestHandler;

    @Resource
    private MessageHandler              packageLogDataMsgHandler;

    private Map<String, MessageHandler> handlerMap = new HashMap<String, MessageHandler>();

    public BaseMsg handle(BaseMsg baseMsg) {
        MessageHandler mh = handlerMap.get(baseMsg.getMsgType().getCode());
        return mh.handle(baseMsg);
    }

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        handlerMap.put(MsgType.HEART.getCode(), heartMessageHandler);
        handlerMap.put(MsgType.LOG_DATA.getCode(), logDataMsgHandler);
        handlerMap.put(MsgType.QUERY_COMMAND_REQUEST.getCode(), queryCommandRequestHandler);
        handlerMap.put(MsgType.PACKAGE_DATA.getCode(), packageLogDataMsgHandler);

    }

}
