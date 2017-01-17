/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: CommandRequest.java, v0.1 2016年11月19日 下午10:28:08 czhou3306@gmail.com Exp $
 */
public class QueryCommandRequest extends BaseMsg {

    /**  */
    private static final long serialVersionUID = 8450637690892247697L;

    /**
     * @param msgType
     */
    public QueryCommandRequest() {
        super(MsgType.QUERY_COMMAND_REQUEST);
    }

}
