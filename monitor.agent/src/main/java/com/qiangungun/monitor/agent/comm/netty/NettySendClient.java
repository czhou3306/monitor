/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.comm.netty;

import io.netty.channel.socket.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.AgentContext;
import com.qiangungun.monitor.agent.comm.MsgManager;
import com.qiangungun.monitor.agent.comm.SendClient;
import com.qiangungun.monitor.agent.model.BaseMsg;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: NettyNetworkClient.java, v0.1 2016年11月19日 下午10:32:20 czhou3306@gmail.com Exp $
 */
public class NettySendClient implements SendClient {

    private static final Logger logger = LoggerFactory.getLogger(NettySendClient.class);

    public NettySendClient() {

    }

    /** 
     * @see com.qiangungun.monitor.agent.comm.SendClient#sendWithResponse(com.qiangungun.monitor.agent.model.BaseMsg)
     */
    @Override
    public BaseMsg sendWithResponse(BaseMsg baseMsg) {

        try {
            SocketChannel channel = AgentContext.nettyClientBootstrap.getChannel();
            if (channel == null) {
                //如果没有可用的发送渠道,直接返回null
                return null;
            }
            channel.writeAndFlush(baseMsg);
            MsgManager.instance().addSentMsg(baseMsg);
            //5s超时
            synchronized (baseMsg) {
                baseMsg.wait(5000);
            }
            return MsgManager.instance().getReturnMsg(baseMsg.getMsgId());
        } catch (InterruptedException e) {
            logger.error("消息发送异常baseMsg=" + baseMsg, e);
            return null;
        }

    }

    /** 
     * @see com.qiangungun.monitor.agent.comm.SendClient#sendWithoutResponse(com.qiangungun.monitor.agent.model.BaseMsg)
     */
    @Override
    public void sendWithoutResponse(BaseMsg baseMsg) {

        try {
            SocketChannel channel = AgentContext.nettyClientBootstrap.getChannel();
            if (channel == null) {
                logger.warn("没有可用连接，放弃发送msg=" + baseMsg);
                //没有连接,直接返回
                return;
            }
            channel.writeAndFlush(baseMsg);
        } catch (Exception e) {
            logger.error("消息发送异常baseMsg=" + baseMsg, e);
        }

    }

}
