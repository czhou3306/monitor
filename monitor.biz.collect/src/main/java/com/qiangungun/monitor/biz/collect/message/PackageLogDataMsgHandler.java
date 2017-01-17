/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiangungun.monitor.agent.model.BaseMsg;
import com.qiangungun.monitor.agent.model.LogDataMsg;
import com.qiangungun.monitor.agent.model.PackageLogDataMsg;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: PackageLogDataMsgHandler.java, v0.1 2016年12月22日 下午4:01:14 czhou3306@gmail.com Exp $
 */
@Service("packageLogDataMsgHandler")
public class PackageLogDataMsgHandler implements MessageHandler {

    @Resource
    private LogDataMsgHandler logDataMsgHandler;

    /** 
     * @see com.qiangungun.monitor.biz.collect.message.MessageHandler#handle(com.qiangungun.monitor.agent.model.BaseMsg)
     */
    @Override
    public BaseMsg handle(BaseMsg baseMsg) {

        PackageLogDataMsg packageLog = (PackageLogDataMsg) baseMsg;
        for (LogDataMsg ldm : packageLog.getMsgList()) {
            logDataMsgHandler.handle(ldm);
        }
        return null;
    }

}
