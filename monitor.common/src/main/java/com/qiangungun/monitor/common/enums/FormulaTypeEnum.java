/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: FormulaTypeEnum.java, v0.1 2015年11月17日 下午5:42:37 czhou3306@gmail.com Exp $
 */
public enum FormulaTypeEnum {

    SUM("SUM", "S", "计算总数"),

    COUNT("COUNT", "C", "计算次数"),

    ;

    private static Map<String, FormulaTypeEnum> map = new HashMap<String, FormulaTypeEnum>();
    static {
        for (FormulaTypeEnum e : FormulaTypeEnum.values()) {
            map.put(e.getCode().toLowerCase(), e);
        }
    }

    private String                              code;

    private String                              key;

    private String                              desc;

    private FormulaTypeEnum(String code,String key, String desc) {
        this.code = code;
        this.key = key;
        this.desc = desc;
    }

    public static FormulaTypeEnum getByCode(String code) {
        return map.get(code.toLowerCase());
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

    /**
     * Getter method for property <tt>key</tt>.
     * 
     * @return property value of key
     */
    public String getKey() {
        return key;
    }

}
