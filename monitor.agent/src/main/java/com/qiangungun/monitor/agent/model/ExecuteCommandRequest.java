/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ExecuteCommandRequest.java, v0.1 2016年11月20日 下午5:13:09 czhou3306@gmail.com Exp $
 */
public class ExecuteCommandRequest extends BaseMsg {

    private String            filePath;

    /**  */
    private static final long serialVersionUID = -8194668193424378092L;

    /**
     * @param msgType
     */
    public ExecuteCommandRequest() {
        super(MsgType.EXECUTE_COMMAND_REQUEST);
    }

    /**
     * Getter method for property <tt>filePath</tt>.
     * 
     * @return property value of filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Setter method for property <tt>filePath</tt>.
     * 
     * @param filePath value to be assigned to property filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
