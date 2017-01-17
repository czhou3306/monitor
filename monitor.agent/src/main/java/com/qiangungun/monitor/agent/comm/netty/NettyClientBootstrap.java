package com.qiangungun.monitor.agent.comm.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.model.AgentConstant;
import com.qiangungun.monitor.agent.model.RemoteAddress;

/**
 * 
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: NettyClientBootstrap.java, v0.1 2016年11月19日 下午11:51:10 czhou3306@gmail.com Exp $
 */
public class NettyClientBootstrap {

    private static final Logger         logger           = LoggerFactory
                                                             .getLogger(NettyClientBootstrap.class);

    private Map<String, SocketChannel>  channelMap       = new ConcurrentHashMap<String, SocketChannel>();
    private EventLoopGroup              eventLoopGroup;
    private Bootstrap                   bootstrap;

    private LinkedBlockingQueue<String> reconnectIpQueue = new LinkedBlockingQueue<String>();

    public void close() {
        try {
            Future<?> f = eventLoopGroup.shutdownGracefully();

            f.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NettyClientBootstrap() {

        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        bootstrap.group(eventLoopGroup);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {

                ChannelPipeline p = socketChannel.pipeline();

                p.addLast(new IdleStateHandler(60, 60, 60));

                p.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0,
                    AgentConstant.MAX_MSG_LENGTH));
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                p.addLast(new LengthFieldPrepender(AgentConstant.MAX_MSG_LENGTH, false));
                p.addLast(new NettyClientHandler(NettyClientBootstrap.this));
            }
        });

        fireReconnect();

    }

    public void addNewChannel(List<String> ipList) {
        if (ipList == null) {
            return;
        }
        for (String ip : ipList) {
            addNewChannel(ip);
        }
    }

    public void addNewChannel(String ip) {
        if (channelMap.get(ip) != null) {
            //
            return;
        }
        initSocketChannel(ip);
    }

    public void removeChannel(String ip) {
        SocketChannel channel = channelMap.get(ip);

        if (channel != null) {
            channel.close();
            channelMap.remove(ip);
        }
    }

    private void initSocketChannel(String ip) {
        RemoteAddress address = new RemoteAddress(ip, AgentConstant.SERVER_PORT);
        SocketChannel socketChannel = null;
        try {
            ChannelFuture future = bootstrap.connect(address.getIp(), address.getPort()).sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                logger.info("连接建立成功address=" + address);
                channelMap.put(address.getIp(), socketChannel);
            }
        } catch (Exception e) {
            logger.error("建立连接异常address=" + address, e);
            reconnectIpQueue.offer(ip);
            if (socketChannel != null) {
                socketChannel.close();
            }
        }
    }

    public SocketChannel getChannel() {
        if (channelMap.size() <= 0) {
            return null;
        }
        Object[] channelArray = channelMap.keySet().toArray();

        int index = (int) Math.random() * channelArray.length;

        return channelMap.get(channelArray[index]);
    }

    /**
     * 开始重连
     */
    private void fireReconnect() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (!reconnectIpQueue.isEmpty()) {
                    int length = reconnectIpQueue.size();
                    for (int i = 0; i < length; i++) {
                        try {
                            String ip = reconnectIpQueue.take();
                            initSocketChannel(ip);
                        } catch (InterruptedException e) {
                            //这个时候还没从队列中拿取数据，啥也不用干 
                        }
                    }
                }
            }
        }, 5000, 5000);
    }

    public void addReconnectAddress(String ip) {
        channelMap.remove(ip);
        reconnectIpQueue.offer(ip);
    }
}
