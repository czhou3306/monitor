/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.repository.seq;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiangungun.monitor.common.enums.BizCodeEnum;
import com.qiangungun.monitor.common.exceptions.MonitorException;
import com.qiangungun.qdal.sequence.impl.DefaultSequenceFactory;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: SequenceRepositoryImpl.java, v0.1 2015年11月22日 下午10:48:29 czhou3306@gmail.com Exp $
 */
@Service("sequenceRepository")
public class SequenceRepositoryImpl implements SequenceRepository {

    @Resource
    private DefaultSequenceFactory defaultSequenceFactory;

    /** 
     * @see com.prague.repository.seq.SequenceRepository#genOrderId()
     */
    @Override
    public String genConfigId() {
        try {
            long seq = defaultSequenceFactory.getNextValue("seq_config_id");

            return String.valueOf(seq);
        } catch (Exception e) {
            throw new MonitorException(BizCodeEnum.SERIAL_GEN_ERROR, e);
        }

    }

}
