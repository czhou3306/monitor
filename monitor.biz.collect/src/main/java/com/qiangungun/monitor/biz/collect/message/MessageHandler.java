/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message;

import com.qiangungun.monitor.agent.model.BaseMsg;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MessageHandler.java, v0.1 2016年11月22日 下午1:16:43 czhou3306@gmail.com Exp $
 */
public interface MessageHandler {

    BaseMsg handle(BaseMsg baseMsg);
}
