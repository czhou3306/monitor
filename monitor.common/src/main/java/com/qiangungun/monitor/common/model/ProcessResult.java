/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qiangungun.monitor.common.enums.BizCodeEnum;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ResultObject.java, v0.1 2015年11月17日 上午9:41:54 czhou3306@gmail.com Exp $
 */
public class ProcessResult {

    private BizCodeEnum   code;

    private String              message;

    private Map<String, Object> resultObject = new HashMap<String, Object>();

    public Object get(String key) {
        return resultObject.get(key);
    }

    public boolean isSuccess() {
        return code == BizCodeEnum.SUCCESS;
    }

    public void put(String key, Object value) {
        this.resultObject.put(key, value);
    }

    public void putAll(Map<String, Object> data) {
        this.resultObject.putAll(data);
    }

    /**
     * Getter method for property <tt>resultObject</tt>.
     * 
     * @return property value of resultObject
     */
    public Map<String, Object> getResultObject() {
        return resultObject;
    }

    /**
     * Setter method for property <tt>resultObject</tt>.
     * 
     * @param resultObject value to be assigned to property resultObject
     */
    public void setResultObject(Map<String, Object> resultObject) {
        this.resultObject = resultObject;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public BizCodeEnum getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     * 
     * @param code value to be assigned to property code
     */
    public void setCode(BizCodeEnum code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>message</tt>.
     * 
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     * 
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
