/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import junit.framework.TestCase;

import com.qiangungun.monitor.agent.model.GatherFileConfig;
import com.qiangungun.monitor.agent.model.AgentConstant;
import com.qiangungun.monitor.agent.task.LineFileGatherTask;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: LineFileGatherTaskTest.java, v0.1 2016年12月2日 下午5:15:39 czhou3306@gmail.com Exp $
 */
public class LineFileGatherTaskTest extends TestCase {

    public void testaa() {
        GatherFileConfig fileConfig = new GatherFileConfig();
        fileConfig.setFileId("11");
        fileConfig.setFilePath("d:/11{yyyyMMdd}.txt");
        fileConfig.setGatherType(AgentConstant.GATHER_LINE);
        LineFileGatherTask task = new LineFileGatherTask(fileConfig);
    }
}
