/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.model.AgentConstant;
import com.qiangungun.monitor.agent.model.GatherFileConfig;
import com.qiangungun.monitor.agent.model.LogDataMsg;
import com.qiangungun.monitor.agent.model.PackageLogDataMsg;
import com.qiangungun.monitor.agent.task.ErrorFileGatherTask;
import com.qiangungun.monitor.agent.task.LineFileGatherTask;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherManager.java, v0.1 2016年11月20日 下午9:03:51 czhou3306@gmail.com Exp $
 */
public class GatherManager {

    private static final Logger     logger    = LoggerFactory.getLogger(GatherManager.class);

    private Map<String, GatherTask> gatherMap = new ConcurrentHashMap<String, GatherTask>();

    public GatherManager() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                int length = AgentContext.logSendQueue.size();
                if (length <= 0) {
                    return;
                }
                PackageLogDataMsg packageData = new PackageLogDataMsg();
                for (int i = 0; i < length; i++) {
                    LogDataMsg msg = AgentContext.logSendQueue.poll();
                    if (msg != null) {
                        packageData.getMsgList().add(msg);
                    } else {
                        break;
                    }
                }

                logger.info("批量发送日志packageData=" + packageData);
                AgentContext.sendClient.sendWithoutResponse(packageData);
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    public void addGather(List<GatherFileConfig> configList) {

        Map<String, GatherTask> temp = new ConcurrentHashMap<String, GatherTask>();
        for (GatherFileConfig fileConfig : configList) {

            if (fileConfig.getFilePath() == null || fileConfig.getFilePath().trim() == "") {
                return;
            }

            GatherTask gatherTask = gatherMap.remove(fileConfig.getFileId());
            if (gatherTask != null) {
                temp.put(fileConfig.getFileId(), gatherTask);
                continue;
            }
            if (StringUtils.equals(fileConfig.getGatherType(), AgentConstant.GATHER_LINE)) {
                gatherTask = new LineFileGatherTask(fileConfig);
            } else if (StringUtils.equals(fileConfig.getGatherType(), AgentConstant.GATHER_ERROR)) {
                gatherTask = new ErrorFileGatherTask(fileConfig);
            } else {
                logger.warn("采集类型不支持");
                return;
            }
            gatherTask.start();
            temp.put(fileConfig.getFileId(), gatherTask);
        }

        for (GatherTask gt : gatherMap.values()) {
            gt.stop();
        }
        gatherMap = temp;

    }
}
