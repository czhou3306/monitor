/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.form;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: SplitForm.java, v0.1 2016年11月30日 下午1:56:24 czhou3306@gmail.com Exp $
 */
public class SplitForm extends BaseForm {

    private String regex;

    private String instance;

    /**
     * Getter method for property <tt>regex</tt>.
     * 
     * @return property value of regex
     */
    public String getRegex() {
        return regex;
    }

    /**
     * Setter method for property <tt>regex</tt>.
     * 
     * @param regex value to be assigned to property regex
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * Getter method for property <tt>instance</tt>.
     * 
     * @return property value of instance
     */
    public String getInstance() {
        return instance;
    }

    /**
     * Setter method for property <tt>instance</tt>.
     * 
     * @param instance value to be assigned to property instance
     */
    public void setInstance(String instance) {
        this.instance = instance;
    }

}
