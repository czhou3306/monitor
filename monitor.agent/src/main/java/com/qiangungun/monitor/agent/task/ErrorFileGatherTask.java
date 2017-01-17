/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.task;

import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiangungun.monitor.agent.AgentContext;
import com.qiangungun.monitor.agent.GatherTask;
import com.qiangungun.monitor.agent.model.GatherFileConfig;
import com.qiangungun.monitor.agent.model.LogDataMsg;
import com.qiangungun.monitor.agent.util.StringConvert;

/**
 * 错误日志文件采集
 *
 * @author czhou3306@gmail.com
 * @version $Id: ErrorFileGatherTask.java, v0.1 2016年12月2日 下午1:33:58 czhou3306@gmail.com Exp $
 */
public class ErrorFileGatherTask implements GatherTask {

    private static final Logger logger      = LoggerFactory.getLogger(LineFileGatherTask.class);

    private Tailer              tailer;

    private GatherFileConfig    fileConfig;

    private StringBuilder       builder     = new StringBuilder();

    private final static String LEVEL_ERROR = "ERROR";

    public ErrorFileGatherTask(GatherFileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    /**
     * 
     * 
     * @param
     */
    public void start() {

        tailer = new Tailer(new File(fileConfig.getFilePath()), new TailerListener() {

            @Override
            public void init(Tailer tailer) {
                logger.info("准备采集文件" + fileConfig);
            }

            @Override
            public void handle(Exception ex) {
                logger.error("采集异常", ex);
            }

            @Override
            public void handle(String line) {

                line = StringConvert.convertString(line, "UTF-8");

                if (StringUtils.isBlank(line)) {
                    return;
                }

                if (line.contains(LEVEL_ERROR)) {
                    String data = builder.toString();

                    if (StringUtils.isBlank(data)) {
                        return;
                    }
                    logger.info("lineData=" + data);

                    LogDataMsg msg = new LogDataMsg();
                    msg.setFileId(fileConfig.getFileId());
                    msg.setFilePath(fileConfig.getFilePath());
                    msg.setLineData(data);
                    //添加到队列中，满了就不管了
                    if (!AgentContext.logSendQueue.offer(msg)) {
                        logger.warn("队列满了,丢弃");
                    }

                    builder = new StringBuilder();
                }
                builder.append(line + "\n");

            }

            @Override
            public void fileRotated() {
            }

            @Override
            public void fileNotFound() {
                logger.error("文件不存在，准备下次检查" + fileConfig);
            }
        }, 1000, true);

        new Thread(tailer).start();
    }

    /** 
     * @see com.qiangungun.monitor.agent.GatherTask#stop()
     */
    @Override
    public void stop() {

        if (tailer != null) {
            tailer.stop();
        }
    }

}
