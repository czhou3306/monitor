/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherFile.java, v0.1 2016年11月22日 下午10:25:16 czhou3306@gmail.com Exp $
 */
public class GatherFile extends BaseModel {

    private String               fileId;

    private String               systemName;

    private String               filePath;

    private List<String>         splitRegex;

    /**key是字段的位置,value是字段名字*/
    private Map<Integer, String> groupField = new TreeMap<Integer, String>();

    private String               gatherType;

    public void putField(Integer position, String fieldName) {
        groupField.put(position, fieldName);
    }

    /**
     * Getter method for property <tt>groupField</tt>.
     * 
     * @return property value of groupField
     */
    public Map<Integer, String> getGroupField() {
        return groupField;
    }

    /**
     * Getter method for property <tt>gatherType</tt>.
     * 
     * @return property value of gatherType
     */
    public String getGatherType() {
        return gatherType;
    }

    /**
     * Setter method for property <tt>gatherType</tt>.
     * 
     * @param gatherType value to be assigned to property gatherType
     */
    public void setGatherType(String gatherType) {
        this.gatherType = gatherType;
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
     * Getter method for property <tt>splitRegex</tt>.
     * 
     * @return property value of splitRegex
     */
    public List<String> getSplitRegex() {
        return splitRegex;
    }

    /**
     * Setter method for property <tt>splitRegex</tt>.
     * 
     * @param splitRegex value to be assigned to property splitRegex
     */
    public void setSplitRegex(List<String> splitRegex) {
        this.splitRegex = splitRegex;
    }

}
