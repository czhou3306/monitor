package com.qiangungun.monitor.biz.collect.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.model.BaseMsg;
import com.qiangungun.monitor.biz.collect.message.MessageHandlerFactory;

/**
 * 
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: NettyServerHandler.java, v0.1 2016年11月23日 下午11:07:20 czhou3306@gmail.com Exp $
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {

    private static final Logger   logger = LoggerFactory.getLogger(NettyServerHandler.class);

    private MessageHandlerFactory messageHandlerFactory;

    NettyServerHandler(MessageHandlerFactory messageHandlerFactory) {

        this.messageHandlerFactory = messageHandlerFactory;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress inetSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = inetSocket.getAddress().getHostAddress();
        logger.info("连接建立" + ip);
        AgentChannelPool.put(ip, (SocketChannel) ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        InetSocketAddress inetSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = inetSocket.getAddress().getHostAddress();
        logger.info("连接销毁" + ip);
        AgentChannelPool.remove(ip);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("exceptionCaught", cause);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BaseMsg baseMsg) throws Exception {

        logger.info("收到消息" + ToStringBuilder.reflectionToString(baseMsg));

        BaseMsg result = messageHandlerFactory.handle(baseMsg);
        if (result != null) {
            result.setMsgId(baseMsg.getMsgId());
            ctx.writeAndFlush(result);
        }

    }
}
