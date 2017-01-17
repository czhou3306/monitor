/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MonitorFormula.java, v0.1 2016年12月1日 下午1:32:30 czhou3306@gmail.com Exp $
 */
public class MonitorFormula extends BaseModel {

    private String               monitorId;

    private String               fileId;

    private String               monitorName;

    private List<Integer>        showFields;

    private String               formula;

    private List<ComputeFormula> computeList = new ArrayList<ComputeFormula>();

    /**
     * Getter method for property <tt>formula</tt>.
     * 
     * @return property value of formula
     */
    public String getFormula() {
        return formula;
    }

    /**
     * Getter method for property <tt>computeList</tt>.
     * 
     * @return property value of computeList
     */
    public List<ComputeFormula> getComputeList() {
        return computeList;
    }

    /**
     * Setter method for property <tt>formula</tt>.
     * 
     * @param formula value to be assigned to property formula
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }

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
     * Getter method for property <tt>showFields</tt>.
     * 
     * @return property value of showFields
     */
    public List<Integer> getShowFields() {
        return showFields;
    }

    /**
     * Setter method for property <tt>showFields</tt>.
     * 
     * @param showFields value to be assigned to property showFields
     */
    public void setShowFields(List<Integer> showFields) {
        this.showFields = showFields;
    }

}
