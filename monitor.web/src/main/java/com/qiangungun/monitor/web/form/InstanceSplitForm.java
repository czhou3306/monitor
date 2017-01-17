/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.form;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: InstanceSplitForm.java, v0.1 2016年12月6日 下午6:56:00 czhou3306@gmail.com Exp $
 */
public class InstanceSplitForm extends BaseForm {

    private String instance;

    private String regex;

    private String filePath;

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
