/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.form;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ComputeFormulaForm.java, v0.1 2016年12月6日 下午5:18:04 czhou3306@gmail.com Exp $
 */
public class ComputeFormulaForm extends BaseForm {

    private String fileId;

    private String computeId;

    private String computeName;

    private String formulaType;

    private String fieldPosition;

    private String filterCondition;

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
     * Getter method for property <tt>computeId</tt>.
     * 
     * @return property value of computeId
     */
    public String getComputeId() {
        return computeId;
    }

    /**
     * Setter method for property <tt>computeId</tt>.
     * 
     * @param computeId value to be assigned to property computeId
     */
    public void setComputeId(String computeId) {
        this.computeId = computeId;
    }

    /**
     * Getter method for property <tt>fieldPosition</tt>.
     * 
     * @return property value of fieldPosition
     */
    public String getFieldPosition() {
        return fieldPosition;
    }

    /**
     * Setter method for property <tt>fieldPosition</tt>.
     * 
     * @param fieldPosition value to be assigned to property fieldPosition
     */
    public void setFieldPosition(String fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    /**
     * Getter method for property <tt>filterCondition</tt>.
     * 
     * @return property value of filterCondition
     */
    public String getFilterCondition() {
        return filterCondition;
    }

    /**
     * Getter method for property <tt>computeName</tt>.
     * 
     * @return property value of computeName
     */
    public String getComputeName() {
        return computeName;
    }

    /**
     * Setter method for property <tt>computeName</tt>.
     * 
     * @param computeName value to be assigned to property computeName
     */
    public void setComputeName(String computeName) {
        this.computeName = computeName;
    }

    /**
     * Setter method for property <tt>filterCondition</tt>.
     * 
     * @param filterCondition value to be assigned to property filterCondition
     */
    public void setFilterCondition(String filterCondition) {
        this.filterCondition = filterCondition;
    }

    /**
     * Getter method for property <tt>formulaType</tt>.
     * 
     * @return property value of formulaType
     */
    public String getFormulaType() {
        return formulaType;
    }

    /**
     * Setter method for property <tt>formulaType</tt>.
     * 
     * @param formulaType value to be assigned to property formulaType
     */
    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

}
