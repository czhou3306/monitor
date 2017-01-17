/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiangungun.monitor.agent.model.BaseMsg;
import com.qiangungun.monitor.agent.model.GatherFileConfig;
import com.qiangungun.monitor.agent.model.QueryCommandRequest;
import com.qiangungun.monitor.agent.model.QueryCommandResponse;
import com.qiangungun.monitor.repository.cache.GatherFileCache;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: QueryCommandRequestHandler.java, v0.1 2016年11月22日 下午1:24:39 czhou3306@gmail.com Exp $
 */
@Service("queryCommandRequestHandler")
public class QueryCommandRequestHandler implements MessageHandler {

    @Resource
    private GatherFileCache gatherFileCache;

    /** 
     * @see com.qiangungun.monitor.biz.collect.message.MessageHandler#handle(com.qiangungun.monitor.agent.model.BaseMsg)
     */
    @Override
    public BaseMsg handle(BaseMsg baseMsg) {

        QueryCommandRequest request = (QueryCommandRequest) baseMsg;
        String appName = request.getApp();

        QueryCommandResponse response = new QueryCommandResponse();
        List<GatherFileConfig> fileList = gatherFileCache.getByApp(appName);
        response.setFileList(fileList);
        response.setApp(request.getApp());

        return response;
    }

}
