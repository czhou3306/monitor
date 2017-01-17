/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message.split;

import org.apache.commons.lang3.StringUtils;

import com.qiangungun.monitor.agent.model.AgentConstant;
import com.qiangungun.monitor.common.model.GatherFile;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: SplitorFactory.java, v0.1 2016年12月4日 下午6:27:20 czhou3306@gmail.com Exp $
 */
public class SplitorFactory {

    private RegularSplitor   regularSplitor   = new RegularSplitor();

    private ExceptionSplitor exceptionSplitor = new ExceptionSplitor();

    private AccessLogSplitor accessLogSplitor = new AccessLogSplitor();

    public String[] split(GatherFile gatherFile, String data) {
        if (gatherFile.getGatherType().equals(AgentConstant.GATHER_ERROR)) {
            return exceptionSplitor.split(data, null);
        } else if (StringUtils.contains(gatherFile.getFilePath(), "access")
                   && StringUtils.contains(gatherFile.getFilePath(), "log")) {
            return accessLogSplitor.split(data, null);
        } else {
            return regularSplitor.split(data, gatherFile.getSplitRegex());
        }
    }

}
