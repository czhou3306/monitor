/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiangungun.monitor.common.enums.BizCodeEnum;
import com.qiangungun.monitor.common.exceptions.MonitorException;
import com.qiangungun.monitor.dal.daointerface.GatherFileDAO;
import com.qiangungun.monitor.dal.dataobject.GatherFileDO;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherFileRepositoryImpl.java, v0.1 2016年11月30日 下午2:14:36 czhou3306@gmail.com Exp $
 */
@Service("gatherFileRepository")
public class GatherFileRepositoryImpl implements GatherFileRepository {

    @Resource
    private GatherFileDAO gatherFileDAO;

    /** 
     * @see com.qiangungun.monitor.repository.GatherFileRepository#insert(com.qiangungun.monitor.dal.dataobject.GatherFileDO)
     */
    @Override
    public void insert(GatherFileDO gatherFileDO) {
        try {
            gatherFileDAO.insert(gatherFileDO);
        } catch (Exception e) {
            throw new MonitorException(BizCodeEnum.DB_ACCESS_ERROR, e);
        }
    }

    /** 
     * @see com.qiangungun.monitor.repository.GatherFileRepository#update(com.qiangungun.monitor.dal.dataobject.GatherFileDO)
     */
    @Override
    public void update(GatherFileDO gatherFileDO) {

        try {
            int rows = gatherFileDAO.update(gatherFileDO.getSystemName(),
                gatherFileDO.getFilePath(), gatherFileDO.getSplitRegex(),
                gatherFileDO.getGroupFieldPosition(), gatherFileDO.getInstance(),
                gatherFileDO.getGatherType(), gatherFileDO.getFileId());
            if (rows < 1) {
                throw new MonitorException(BizCodeEnum.UPDATE_ROWS_ERROR);
            }
        } catch (Exception e) {
            throw new MonitorException(BizCodeEnum.DB_ACCESS_ERROR, e);
        }

    }
}
