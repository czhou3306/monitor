/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
 * 按行采集的任务
 *
 * @author czhou3306@gmail.com
 * @version $Id: RegularFileGatherTask.java, v0.1 2016年12月2日 下午1:14:17 czhou3306@gmail.com Exp $
 */
public class LineFileGatherTask implements GatherTask {

    private static final Logger logger = LoggerFactory.getLogger(LineFileGatherTask.class);

    private Tailer              tailer;

    /**支持固定格式和带{}参数*/
    private GatherFileConfig    fileConfig;

    private String              dateFormat;

    private String              replaceStr;

    private Timer               timer;

    public LineFileGatherTask(GatherFileConfig fileConfig) {

        this.fileConfig = fileConfig;
        this.dateFormat = StringUtils.substringBetween(fileConfig.getFilePath(), "{", "}");
        int closeBegin = fileConfig.getFilePath().indexOf("{");
        int closeEnd = fileConfig.getFilePath().indexOf("}");

        replaceStr = StringUtils.substring(fileConfig.getFilePath(), closeBegin, closeEnd + 1);
    }

    /**
     * 
     * 
     * @param
     */
    public void start() {
        if (!StringUtils.isBlank(dateFormat)) {

            timer = new Timer(true);
            //带文件格式的，需要定时检查文件变化
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    String filePath = genGatherFilePath();

                    //文件要更新了。。。
                    if (tailer != null
                        && !StringUtils.equals(filePath, tailer.getFile().getAbsolutePath())) {
                        tailer.stop();
                        startTail(new File(filePath));
                    }
                }

            }, 1000, 1000);

            String filePath = genGatherFilePath();
            startTail(new File(filePath));
        } else {
            startTail(new File(fileConfig.getFilePath()));
        }
    }

    /**
     * 
     * @return
     */
    private String genGatherFilePath() {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String nowFormat = format.format(new Date());
        String filePath = StringUtils.replace(fileConfig.getFilePath(), replaceStr, nowFormat);
        return filePath;
    }

    /**
     * 
     */
    private void startTail(final File file) {

        tailer = new Tailer(file, new TailerListener() {

            @Override
            public void init(Tailer tailer) {
                logger.info("准备采集文件" + file.getAbsolutePath());
            }

            @Override
            public void handle(Exception ex) {
                logger.error("采集异常", ex);
            }

            @Override
            public void handle(String line) {
                line = StringConvert.convertString(line, "UTF-8");
                logger.info("lineData=" + line);
                if (StringUtils.isBlank(line)) {
                    return;
                }

                LogDataMsg msg = new LogDataMsg();
                msg.setFileId(fileConfig.getFileId());
                msg.setLineData(line);
                msg.setFilePath(file.getAbsolutePath());
                //添加到队列中，满了就不管了
                if (!AgentContext.logSendQueue.offer(msg)) {
                    logger.warn("队列满了,丢弃");
                }
            }

            @Override
            public void fileRotated() {
            }

            @Override
            public void fileNotFound() {
                logger.error("文件不存在，准备下次检查" + file.getAbsolutePath());
            }
        }, 5000, true);

        new Thread(tailer).start();
    }

    /** 
     * @see com.qiangungun.monitor.agent.GatherTask#stop()
     */
    @Override
    public void stop() {
        logger.warn("停止采集fileConfig=" + fileConfig);
        if (tailer != null) {
            tailer.stop();
        }
        if (timer != null) {
            timer.cancel();
        }
    }

}
