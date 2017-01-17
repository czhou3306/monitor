/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.common.enums;

/**
 * @author czhou3306@gmail.com
 * @version $Id: MarketBizCodeEnum.java, v0.1 2015年11月17日 上午10:28:41 czhou3306@gmail.com Exp $
 */
public enum BizCodeEnum {

    SUCCESS("000000", "提交成功，谢谢参与。"),

    DB_ACCESS_ERROR("100000", "访问db异常"),
    
    NO_DATA("100001","没有数据"),

    UNKNOWN("900000", "未知异常"),

    SERIAL_GEN_ERROR("900001", "流水号生成异常"),

    UPDATE_ROWS_ERROR("900002", "更新数据条数错误"),
    
    NO_PROCESS_MATCHED("900003", ""),

    SYSTEM_ERROR("999999", "系统异常"), ;

    private String code;

    private String message;

    private BizCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
