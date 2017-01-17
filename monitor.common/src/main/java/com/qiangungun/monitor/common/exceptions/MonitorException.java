/**
 * Qiangungun.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.common.exceptions;

import com.qiangungun.monitor.common.enums.BizCodeEnum;

/**
 *
 */
public class MonitorException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -3232783897443676932L;
    public BizCodeEnum  bizCode;

    private String            msg;

    public MonitorException(BizCodeEnum bizCode, Exception e) {
        super(e);
        this.bizCode = bizCode;
    }

    public MonitorException(BizCodeEnum bizCode, String msg) {
        super(msg);
        this.bizCode = bizCode;
        this.msg = msg;
    }

    public MonitorException(BizCodeEnum bizCode) {
        super(bizCode.getCode() + ":" + bizCode.getMessage());
        this.bizCode = bizCode;
    }

    /**
     * Getter method for property <tt>bizCode</tt>.
     * 
     * @return property value of bizCode
     */
    public BizCodeEnum getBizCode() {
        return bizCode;
    }

    /**
     * Setter method for property <tt>bizCode</tt>.
     * 
     * @param bizCode value to be assigned to property bizCode
     */
    public void setBizCode(BizCodeEnum bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * Getter method for property <tt>msg</tt>.
     * 
     * @return property value of msg
     */
    public String getMsg() {
        return msg;
    }

}
