/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import java.util.Map;
import java.util.TreeMap;

import com.qiangungun.monitor.common.enums.FormulaTypeEnum;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ComputeFormula.java, v0.1 2016年11月22日 下午10:25:35 czhou3306@gmail.com Exp $
 */
public class ComputeFormula extends BaseModel {

    private String               fileId;

    private String               computeId;

    private String               computeName;

    private FormulaTypeEnum      formulaType;

    private int                  computeFieldPosition;

    private Map<Integer, String> filter = new TreeMap<Integer, String>();

    //   private List<Integer>        groupBy = new ArrayList<Integer>();

    /**
     * Getter method for property <tt>computeId</tt>.
     * 
     * @return property value of computeId
     */
    public String getComputeId() {
        return computeId;
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
     * Setter method for property <tt>computeId</tt>.
     * 
     * @param computeId value to be assigned to property computeId
     */
    public void setComputeId(String computeId) {
        this.computeId = computeId;
    }

    /**
     * Getter method for property <tt>formulaType</tt>.
     * 
     * @return property value of formulaType
     */
    public FormulaTypeEnum getFormulaType() {
        return formulaType;
    }

    /**
     * Setter method for property <tt>formulaType</tt>.
     * 
     * @param formulaType value to be assigned to property formulaType
     */
    public void setFormulaType(FormulaTypeEnum formulaType) {
        this.formulaType = formulaType;
    }

    /**
     * Getter method for property <tt>computeFieldPosition</tt>.
     * 
     * @return property value of computeFieldPosition
     */
    public int getComputeFieldPosition() {
        return computeFieldPosition;
    }

    /**
     * Setter method for property <tt>computeFieldPosition</tt>.
     * 
     * @param computeFieldPosition value to be assigned to property computeFieldPosition
     */
    public void setComputeFieldPosition(int computeFieldPosition) {
        this.computeFieldPosition = computeFieldPosition;
    }

    /**
     * Getter method for property <tt>computeKey</tt>.
     * 
     * @return property value of computeKey
     */
    public String getComputeKey() {
        return this.formulaType.getKey() + "-" + this.computeFieldPosition;
    }

    /**
     * Getter method for property <tt>filter</tt>.
     * 
     * @return property value of filter
     */
    public Map<Integer, String> getFilter() {
        return filter;
    }

    /**
     * Setter method for property <tt>filter</tt>.
     * 
     * @param filter value to be assigned to property filter
     */
    public void setFilter(Map<Integer, String> filter) {
        this.filter = filter;
    }

    /**
     * Getter method for property <tt>groupBy</tt>.
     * 
     * @return property value of groupBy
     */
    /*  public List<Integer> getGroupBy() {
          return groupBy;
      }*/

    /**
     * Setter method for property <tt>groupBy</tt>.
     * 
     * @param groupBy value to be assigned to property groupBy
     */
    /*   public void setGroupBy(List<Integer> groupBy) {
           this.groupBy = groupBy;
       }*/

}
