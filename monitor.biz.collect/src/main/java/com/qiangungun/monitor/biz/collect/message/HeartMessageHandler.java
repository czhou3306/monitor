/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message;

import org.springframework.stereotype.Service;

import com.qiangungun.monitor.agent.model.BaseMsg;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: HeartMessageHandler.java, v0.1 2016年11月22日 下午1:24:21 czhou3306@gmail.com Exp $
 */
@Service("heartMessageHandler")
public class HeartMessageHandler implements MessageHandler {

    /** 
     * @see com.qiangungun.monitor.biz.collect.message.MessageHandler#handle(com.qiangungun.monitor.agent.model.BaseMsg)
     */
    @Override
    public BaseMsg handle(BaseMsg baseMsg) {
        //心跳,啥也不用干,直接返回
        return null;
    }

}
