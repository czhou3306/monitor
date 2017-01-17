package com.qiangungun.monitor.biz.collect.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.model.AgentConstant;
import com.qiangungun.monitor.biz.collect.message.MessageHandlerFactory;

/**
 * 
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: NettyServerBootstrap.java, v0.1 2016年11月23日 下午11:07:15 czhou3306@gmail.com Exp $
 */
public class NettyServerBootstrap {

    private static final Logger   logger = LoggerFactory.getLogger(NettyServerBootstrap.class);

    private int                   port   = 20016;

    private EventLoopGroup        boss;
    private EventLoopGroup        worker;

    @Resource
    private MessageHandlerFactory messageHandlerFactory;

    public void bind() throws InterruptedException {
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline p = socketChannel.pipeline();
                p.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0,
                    AgentConstant.MAX_MSG_LENGTH));
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                p.addLast(new LengthFieldPrepender(AgentConstant.MAX_MSG_LENGTH, false));
                p.addLast(new NettyServerHandler(messageHandlerFactory));
            }
        });
        ChannelFuture f = bootstrap.bind(port).sync();
        if (f.isSuccess()) {
            logger.info("开始监听:port:" + port);
        }
    }

    public void close() {
        try {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * Getter method for property <tt>port</tt>.
     * 
     * @return property value of port
     */
    public int getPort() {
        return port;
    }

    /**
     * Setter method for property <tt>port</tt>.
     * 
     * @param port value to be assigned to property port
     */
    public void setPort(int port) {
        this.port = port;
    }

}
