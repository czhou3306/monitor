/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ExecuteCommandResponse.java, v0.1 2016年11月20日 下午5:13:18 czhou3306@gmail.com Exp $
 */
public class ExecuteCommandResponse extends BaseMsg {

    /**  */
    private static final long serialVersionUID = -3199460722703958050L;

    /**
     * @param msgType
     */
    public ExecuteCommandResponse() {
        super(MsgType.EXECUTE_COMMAND_RESPONSE);
    }
}
