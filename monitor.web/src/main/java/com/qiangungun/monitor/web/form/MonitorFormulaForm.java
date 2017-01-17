/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.form;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MonitorFormulaForm.java, v0.1 2016年12月6日 下午5:18:13 czhou3306@gmail.com Exp $
 */
public class MonitorFormulaForm extends BaseForm {

    private String monitorId;

    private String fileId;

    private String monitorName;

    private String showField;

    private String formula;

    /**
     * Getter method for property <tt>monitorId</tt>.
     * 
     * @return property value of monitorId
     */
    public String getMonitorId() {
        return monitorId;
    }

    /**
     * Setter method for property <tt>monitorId</tt>.
     * 
     * @param monitorId value to be assigned to property monitorId
     */
    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    /**
     * Getter method for property <tt>fileId</tt>.
     * 
     * @return property value of fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Setter method for property <tt>fileId</tt>.
     * 
     * @param fileId value to be assigned to property fileId
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * Getter method for property <tt>monitorName</tt>.
     * 
     * @return property value of monitorName
     */
    public String getMonitorName() {
        return monitorName;
    }

    /**
     * Setter method for property <tt>monitorName</tt>.
     * 
     * @param monitorName value to be assigned to property monitorName
     */
    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    /**
     * Getter method for property <tt>showField</tt>.
     * 
     * @return property value of showField
     */
    public String getShowField() {
        return showField;
    }

    /**
     * Setter method for property <tt>showField</tt>.
     * 
     * @param showField value to be assigned to property showField
     */
    public void setShowField(String showField) {
        this.showField = showField;
    }

    /**
     * Getter method for property <tt>formula</tt>.
     * 
     * @return property value of formula
     */
    public String getFormula() {
        return formula;
    }

    /**
     * Setter method for property <tt>formula</tt>.
     * 
     * @param formula value to be assigned to property formula
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }

}
