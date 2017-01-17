/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.model.QueryCommandRequest;
import com.qiangungun.monitor.agent.model.QueryCommandResponse;
import com.qiangungun.monitor.agent.util.PropertiesUtil;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: Application.java, v0.1 2016年11月19日 下午9:50:33 czhou3306@gmail.com Exp $
 */
public class Starter {

    private static final Logger logger = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) {

        if (!checkArgs(args)) {
            return;
        }

        try {
            PropertiesUtil.load(args[0]);
            //处理服务器连接
            List<String> ipList = AgentContext.zkHelper.getMonitorServers();
            logger.info("monitor servers："
                        + ToStringBuilder.reflectionToString(ipList,
                            ToStringStyle.SHORT_PREFIX_STYLE));
            AgentContext.nettyClientBootstrap.addNewChannel(ipList);

            //查询采集的文件列表
            QueryCommandResponse response = (QueryCommandResponse) AgentContext.sendClient
                .sendWithResponse(new QueryCommandRequest());
            if (response != null && !response.getFileList().isEmpty()) {
                AgentContext.gatherManager.addGather(response.getFileList());
            }
            timerQueryCommand();

            shutHook();
            logger.info("agent启动成功");
        } catch (Exception e) {
            logger.error("agent启动异常", e);
            clearResource();
            Runtime.getRuntime().exit(0);
        }

    }

    private static void timerQueryCommand() {

        class FileTask extends TimerTask {

            /** 
             * @see java.util.TimerTask#run()
             */
            @Override
            public void run() {
                try {
                    logger.info("查询采集命令");
                    //启动的时候没有查询到命令,定时去查下
                    QueryCommandResponse response = (QueryCommandResponse) AgentContext.sendClient
                        .sendWithResponse(new QueryCommandRequest());

                    logger.info("采集命令查询结果response=" + response);
                    if (response != null && !response.getFileList().isEmpty()) {
                        AgentContext.gatherManager.addGather(response.getFileList());
                        // this.timer.cancel();
                    }
                } catch (Exception e) {
                    logger.error("定时查询命令异常", e);
                }
            }

        }

        Timer timer = new Timer();
        timer.schedule(new FileTask(), 60 * 1000, 5 * 60 * 1000);
    }

    /**
     * 
     * @param nettyClientBootstrap
     * @param zkHelper
     */
    private static void shutHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    clearResource();
                } catch (Exception e) {
                    logger.error("agent进程关闭善后异常", e);
                }
            }

        });
    }

    /**
     * 
     * @param nettyClientBootstrap
     * @param zkHelper
     */
    private static void clearResource() {
        if (AgentContext.nettyClientBootstrap != null) {
            AgentContext.nettyClientBootstrap.close();
        }
        if (AgentContext.zkHelper != null) {
            AgentContext.zkHelper.unregisterSelf();
            AgentContext.zkHelper.close();
        }
    }

    private static boolean checkArgs(String[] args) {
        if (args.length <= 0) {
            logger.warn("请输入配置文件地址");
            return false;
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            logger.warn("配置文件不存在");
            return false;
        }
        return true;
    }
}
