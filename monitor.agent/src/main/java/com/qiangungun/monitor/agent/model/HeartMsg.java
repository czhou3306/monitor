/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: HeartMsg.java, v0.1 2016年11月20日 下午5:00:18 czhou3306@gmail.com Exp $
 */
public class HeartMsg extends BaseMsg {

    /**  */
    private static final long serialVersionUID = 3766560085386655774L;

    public HeartMsg() {
        super(MsgType.HEART);
    }
}
