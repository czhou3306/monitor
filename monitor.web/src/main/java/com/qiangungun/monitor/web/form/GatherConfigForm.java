/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.form;

import java.util.List;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherConfigForm.java, v0.1 2016年11月30日 下午4:20:41 czhou3306@gmail.com Exp $
 */
public class GatherConfigForm extends BaseForm {

    private String        filePath;

    private String        regex;

    private String        groupFields;

    private List<FormulaForm> formulaList;

    private String        systemName;

    private String        instance;

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
     * Getter method for property <tt>groupFields</tt>.
     * 
     * @return property value of groupFields
     */
    public String getGroupFields() {
        return groupFields;
    }

    /**
     * Setter method for property <tt>groupFields</tt>.
     * 
     * @param groupFields value to be assigned to property groupFields
     */
    public void setGroupFields(String groupFields) {
        this.groupFields = groupFields;
    }

    /**
     * Getter method for property <tt>formulaList</tt>.
     * 
     * @return property value of formulaList
     */
    public List<FormulaForm> getFormulaList() {
        return formulaList;
    }

    /**
     * Setter method for property <tt>formulaList</tt>.
     * 
     * @param formulaList value to be assigned to property formulaList
     */
    public void setFormulaList(List<FormulaForm> formulaList) {
        this.formulaList = formulaList;
    }

    /**
     * Getter method for property <tt>systemName</tt>.
     * 
     * @return property value of systemName
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * Setter method for property <tt>systemName</tt>.
     * 
     * @param systemName value to be assigned to property systemName
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
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
