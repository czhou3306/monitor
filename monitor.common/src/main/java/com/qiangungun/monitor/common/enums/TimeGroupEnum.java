/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: TimeGroupEnum.java, v0.1 2016年12月23日 下午4:19:10 czhou3306@gmail.com Exp $
 */
public enum TimeGroupEnum {

    MIN("M", "分钟", 12),

    TEN_MIN("TM", "十分钟", 11),

    HOUR("H", "小时", 10),

    ;

    private String code;

    private String desc;

    private int    length;

    private TimeGroupEnum(String code, String desc, int timeFormat) {
        this.code = code;
        this.desc = desc;
        this.length = timeFormat;
    }

    public static TimeGroupEnum getByCode(String code) {
        for (TimeGroupEnum e : TimeGroupEnum.values()) {
            if (StringUtils.equals(code, e.getCode())) {
                return e;
            }
        }
        return TimeGroupEnum.MIN;
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
     * Getter method for property <tt>length</tt>.
     * 
     * @return property value of length
     */
    public int getLength() {
        return length;
    }

}
