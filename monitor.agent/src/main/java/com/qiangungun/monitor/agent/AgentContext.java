/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.qiangungun.monitor.agent.comm.SendClient;
import com.qiangungun.monitor.agent.comm.netty.NettyClientBootstrap;
import com.qiangungun.monitor.agent.comm.netty.NettySendClient;
import com.qiangungun.monitor.agent.model.LogDataMsg;
import com.qiangungun.monitor.agent.util.ZkHelper;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: AgentContext.java, v0.1 2016年11月28日 上午9:33:53 czhou3306@gmail.com Exp $
 */
public class AgentContext {

    /**netty连接启动器*/
    public final static NettyClientBootstrap      nettyClientBootstrap = new NettyClientBootstrap();

    /**zk帮助类*/
    public final static ZkHelper                  zkHelper             = new ZkHelper();

    public final static SendClient                sendClient           = new NettySendClient();

    public final static GatherManager             gatherManager        = new GatherManager();

    public final static ExecutorService           threadPool           = Executors
                                                                           .newFixedThreadPool(5);

    /***/
    public final static BlockingQueue<LogDataMsg> logSendQueue         = new LinkedBlockingQueue<LogDataMsg>(
                                                                           5000);

}
