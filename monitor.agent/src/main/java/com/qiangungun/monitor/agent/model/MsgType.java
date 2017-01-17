/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: WeixinApiConstant.java, v0.1 2015年11月17日 下午5:42:37 czhou3306@gmail.com Exp $
 */
public enum MsgType {

    QUERY_COMMAND_REQUEST("QUERY_COMMAND_REQUEST", "查询命令"),
    
    QUERY_COMMAND_RESPONSE("QUERY_COMMAND_RESPONSE", "查询命令结果"),

    HEART("HEART", "心跳"),
    
    LOG_DATA("LOG_DATA", "log日志"),
    
    PACKAGE_DATA("PACKAGE_DATA", "log打包日志"),

    EXECUTE_COMMAND_REQUEST("EXECUTE_COMMAND_REQUEST", "执行命令请求"), 
    
    EXECUTE_COMMAND_RESPONSE("EXECUTE_COMMAND_RESPONSE", "执行命令请求响应"), ;
    ;

    private String code;

    private String desc;

    private MsgType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     * 
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

}
