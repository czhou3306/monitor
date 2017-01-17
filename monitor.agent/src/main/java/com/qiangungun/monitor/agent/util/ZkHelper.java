/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.util;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.AgentContext;
import com.qiangungun.monitor.agent.model.AgentConstant;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ZookeeperHelper.java, v0.1 2015年6月23日 下午4:39:01 czhou3306@gmail.com Exp $
 */
public class ZkHelper {

    private static final Logger logger     = LoggerFactory.getLogger(ZkHelper.class);

    private CuratorFramework    client;

    private String              agentPath  = "/monitor/agent/" + HostUtil.getIpAddress();

    private String              serverPath = "/monitor/server";

    private ExecutorService     pool       = Executors.newFixedThreadPool(2);

    private PathChildrenCache   childrenCache;

    public ZkHelper() {/*

                       try {
                       client = CuratorFrameworkFactory.builder()
                       .connectString(PropertiesUtil.get(AgentConstant.ZK_ADDRESS)).sessionTimeoutMs(5000)
                       .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000)).connectionTimeoutMs(5000)
                       .build();
                       client.start();
                       client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                       .forPath(agentPath, PropertiesUtil.get(AgentConstant.APP).getBytes());
                       registerServerListen();
                       } catch (NodeExistsException e) {
                       //节点已经存在，不作处理
                       } catch (Exception e) {
                       throw new RuntimeException("zk注册失败", e);
                       }

                       */
    }

    public List<String> getMonitorServers() throws Exception {
        //  List<String> servers = client.getChildren().forPath(serverPath);
        //FIXME 先从配置 文件获取，
        String servers = PropertiesUtil.get(AgentConstant.MONITOR_CENTER);

        return Arrays.asList(servers.split(","));
    }

    public void close() {
        try {
            if (childrenCache != null) {
                childrenCache.close();
            }
            if (client != null) {
                client.close();
            }
            if (pool != null) {
                pool.shutdown();
            }
        } catch (Exception e) {
            logger.error("关闭zk异常", e);
        }
    }

    public void unregisterSelf() {
        try {
            client.delete().forPath(agentPath);
        } catch (Exception e) {
            logger.error("注销异常", e);
        }
    }

    /**
     * 注册服务器列表变动的监听
     */
    private void registerServerListen() throws Exception {

        childrenCache = new PathChildrenCache(client, serverPath, true);

        childrenCache.start(StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
                                                                                         throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        logger.info("增加一个监控服务器端" + event.getData().getPath());
                        String ip = getIpFromServerPath(event.getData().getPath());
                        AgentContext.nettyClientBootstrap.addNewChannel(ip);
                        break;
                    case CHILD_REMOVED:
                        logger.info("删除一个监控服务器端" + event.getData().getPath());
                        AgentContext.nettyClientBootstrap.removeChannel(getIpFromServerPath(event
                            .getData().getPath()));
                        break;
                    default:
                        break;
                }
            }

        }, pool);
    }

    private String getIpFromServerPath(String path) {
        return path.substring(serverPath.length() + 1, path.length());
    }

}
