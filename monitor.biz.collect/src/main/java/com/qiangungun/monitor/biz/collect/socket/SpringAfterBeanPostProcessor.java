/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.socket;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: SpringAfterBeanPostProcessor.java, v0.1 2016年11月26日 上午11:50:59 czhou3306@gmail.com Exp $
 */
public class SpringAfterBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private NettyServerBootstrap nettyServerBootstrap;

    private static final Logger  logger = LoggerFactory
                                            .getLogger(SpringAfterBeanPostProcessor.class);

    /** 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (event.getApplicationContext().getParent() == null) {
            try {
                nettyServerBootstrap.bind();
            } catch (InterruptedException e) {
                logger.error("启动监听异常", e);
            }
            //root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
        }
    }

}
