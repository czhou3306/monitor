package com.qiangungun.monitor.agent.comm.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.comm.MsgManager;
import com.qiangungun.monitor.agent.model.BaseMsg;
import com.qiangungun.monitor.agent.model.ExecuteCommandRequest;
import com.qiangungun.monitor.agent.model.ExecuteCommandResponse;
import com.qiangungun.monitor.agent.model.HeartMsg;
import com.qiangungun.monitor.agent.model.MsgType;

/**
 * Created by yaozb on 15-4-11.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {

    private static final Logger  logger = LoggerFactory.getLogger(NettyClientHandler.class);

    private NettyClientBootstrap nettyClientBootstrap;

    public NettyClientHandler(NettyClientBootstrap nettyClientBootstrap) {
        this.nettyClientBootstrap = nettyClientBootstrap;

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        InetSocketAddress inetSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = inetSocket.getAddress().getHostAddress();
        int port = inetSocket.getPort();
        logger.error("连接断了" + ip + ":" + port);
        this.nettyClientBootstrap.addReconnectAddress(ip);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("发送异常", cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case ALL_IDLE:
                    InetSocketAddress inetSocket = (InetSocketAddress) ctx.channel()
                        .remoteAddress();
                    String ip = inetSocket.getAddress().getHostAddress();
                    int port = inetSocket.getPort();
                    HeartMsg heart = new HeartMsg();
                    ctx.writeAndFlush(heart);
                    logger.info("send heart to server.ip:" + ip + ",port=" + port);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BaseMsg baseMsg) throws Exception {
        MsgType msgType = baseMsg.getMsgType();
        switch (msgType) {
            case QUERY_COMMAND_RESPONSE:
                MsgManager.instance().addReturnMsg(baseMsg);
                BaseMsg sentMsg = MsgManager.instance().getSentMsg(baseMsg.getMsgId());
                if (sentMsg != null) {
                    synchronized (sentMsg) {
                        sentMsg.notifyAll();
                    }
                }
                break;
            case EXECUTE_COMMAND_REQUEST: {
                logger.info("接受到服务端的命令请求msg=" + baseMsg);
                ExecuteCommandRequest request = (ExecuteCommandRequest) baseMsg;
                List<String> files = new ArrayList<String>();
                files.add(request.getFilePath());
                //FIXME 暂不支持主动推       AgentContext.gatherManager.addGather(files);
                ctx.writeAndFlush(new ExecuteCommandResponse());
            }
                break;
            default:
                break;
        }

    }
}
