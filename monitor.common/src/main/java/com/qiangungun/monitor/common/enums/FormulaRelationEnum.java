/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.enums;


/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: FormulaRelation.java, v0.1 2016年12月1日 下午1:37:29 czhou3306@gmail.com Exp $
 */
public enum FormulaRelationEnum {

    ADD("+", "计算总数"),

    SUB("-", "计算次数"), 
    
    MUL("*", "计算次数"), 
    
    DIV("/", "计算次数"),

    ;

    private String code;

    private String desc;

    private FormulaRelationEnum(String code, String desc) {
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
