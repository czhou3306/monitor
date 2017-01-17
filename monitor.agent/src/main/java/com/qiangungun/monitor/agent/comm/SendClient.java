/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.comm;

import com.qiangungun.monitor.agent.model.BaseMsg;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: NetworkClient.java, v0.1 2016年11月19日 下午10:25:31 czhou3306@gmail.com Exp $
 */
public interface SendClient {

    /**
     * 发送请求需要带回来响应结果
     * 
     * @param baseMsg
     * @return
     */
    BaseMsg sendWithResponse(BaseMsg baseMsg);

    /**
     * 发送请求不需要响应结果
     * 
     * @param baseMsg
     */
    void sendWithoutResponse(BaseMsg baseMsg);

}
