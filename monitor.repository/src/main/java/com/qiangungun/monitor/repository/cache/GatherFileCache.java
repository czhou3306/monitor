/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.repository.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qiangungun.monitor.agent.model.GatherFileConfig;
import com.qiangungun.monitor.common.model.GatherFile;
import com.qiangungun.monitor.convert.GatherFileConvert;
import com.qiangungun.monitor.dal.daointerface.GatherFileDAO;
import com.qiangungun.monitor.dal.dataobject.GatherFileDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: AuthorizerAppConfigCache.java, v0.1 2015年11月22日 下午7:38:37 czhou3306@gmail.com Exp $
 */
@Service("gatherFileCache")
public class GatherFileCache implements ICache {

    private static final Logger                 logger            = LoggerFactory
                                                                      .getLogger(GatherFileCache.class);
    @Resource
    private GatherFileDAO                       gatherFileDAO;

    /**以filePath为key*/
    private Map<String, GatherFile>             gatherFileMap     = new HashMap<String, GatherFile>();

    /**以fileId为key*/
    private Map<String, GatherFile>             gatherFileMapById = new HashMap<String, GatherFile>();

    /**以appname为key*/
    private Map<String, List<GatherFileConfig>> appMap       = new HashMap<String, List<GatherFileConfig>>();

    public void init() {
        logger.info("开始初始化任务缓存");
        loadData();
        logger.info("任务加载完成gatherFileMap=" + gatherFileMap);
        logger.info("任务加载完成gatherFileMapById=" + gatherFileMapById);

    }

    public void refresh() {

        logger.info("开始刷新任务缓存");
        loadData();
        logger.info("任务刷新完成gatherFileMap="+ gatherFileMap);

    }

    public GatherFile getByFileId(String fileId) {
        return gatherFileMapById.get(fileId);
    }

    public List<GatherFileConfig> getByApp(String app) {
        return appMap.get(app);
    }

    /**
     * 
     */
    private void loadData() {
        List<GatherFileDO> configList = gatherFileDAO.getAll();
        Map<String, GatherFile> temp = new HashMap<String, GatherFile>();
        Map<String, GatherFile> tempGatherFileMapById = new HashMap<String, GatherFile>();

        Map<String, List<GatherFileConfig>> tempAppMap = new HashMap<String, List<GatherFileConfig>>();

        for (GatherFileDO gatherFileDO : configList) {
            GatherFile gatherFile = GatherFileConvert.convert(gatherFileDO);
            temp.put(gatherFileDO.getFilePath(), gatherFile);
            tempGatherFileMapById.put(gatherFileDO.getFileId(), gatherFile);

            List<GatherFileConfig> exist = tempAppMap.get(gatherFileDO.getSystemName());
            if (exist == null) {
                exist = new ArrayList<GatherFileConfig>();
                tempAppMap.put(gatherFileDO.getSystemName(), exist);
            }
            GatherFileConfig config = new GatherFileConfig();
            exist.add(config);
            config.setFileId(gatherFileDO.getFileId());
            config.setFilePath(gatherFileDO.getFilePath());
            config.setGatherType(gatherFileDO.getGatherType());
        }
        gatherFileMap = temp;
        appMap = tempAppMap;
        gatherFileMapById = tempGatherFileMapById;
    }

   
}
