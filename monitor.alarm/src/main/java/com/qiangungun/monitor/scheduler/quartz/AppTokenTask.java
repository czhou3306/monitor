/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.scheduler.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 授权的公共号access_token更新
 *
 * @author czhou3306@gmail.com
 * @version $Id: AuthorizerAppTokenTask.java, v0.1 2015年11月22日 下午7:28:49 czhou3306@gmail.com Exp $
 */
@Service("appTokenTask")
public class AppTokenTask {

    private static final Logger logger = LoggerFactory.getLogger(AppTokenTask.class);

    //  @Resource
    // private WxMessageService wxMessageService;

    @Value("${shedulerEnable}")
    private String              shedulerEnable;

    public void execute() {
    }

}
