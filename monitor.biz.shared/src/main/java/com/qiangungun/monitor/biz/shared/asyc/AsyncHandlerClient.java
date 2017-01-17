/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.shared.asyc;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异步调用客户端
 *
 * @author czhou3306@gmail.com
 * @version $Id: EventClient.java, v0.1 2015年11月27日 上午11:06:00 czhou3306@gmail.com Exp $
 */
public class AsyncHandlerClient {

    private static final Logger logger   = LoggerFactory.getLogger(AsyncHandlerClient.class);

    private Executor            executor = Executors.newFixedThreadPool(100);

    /**
     * 异步执行
     * 
     * @param handler
     * @param payload
     */
    public void asyncFire(final AsyncHandler handler, final Map<String, Object> payload) {
        try {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    handler.handle(payload);
                }
            });
        } catch (Exception e) {
            logger.error("事件异常payload=" + payload, e);
        }

    }

}
