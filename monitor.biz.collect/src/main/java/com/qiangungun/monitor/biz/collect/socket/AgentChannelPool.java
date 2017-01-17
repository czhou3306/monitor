/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.socket;

import io.netty.channel.socket.SocketChannel;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: SocketChannelPool.java, v0.1 2016年11月23日 下午11:17:43 czhou3306@gmail.com Exp $
 */
@Service("agentChannelPool")
public class AgentChannelPool {

    /**以appname为key*/
    private static ConcurrentHashMap<String, List<String>>  agentMap   = new ConcurrentHashMap<String, List<String>>();
    
    /**以ip地址为key*/
    private static ConcurrentHashMap<String, SocketChannel> channelMap = new ConcurrentHashMap<String, SocketChannel>();

    public static void put(String ip, SocketChannel sc) {
        channelMap.put(ip, sc);
    }

    public static void remove(String ip) {
        channelMap.remove(ip);
    }
}
