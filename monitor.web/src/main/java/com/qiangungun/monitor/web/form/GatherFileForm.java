/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.form;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherFileForm.java, v0.1 2016年12月6日 下午5:17:50 czhou3306@gmail.com Exp $
 */
public class GatherFileForm extends BaseForm {

    private String fileId;

    private String systemName;

    private String filePath;

    private String splitRegex;

    private String groupFieldPosition;

    private String instance;

    private String gatherType;

    /**
     * Getter method for property <tt>systemName</tt>.
     * 
     * @return property value of systemName
     */
    public String getSystemName() {
        return systemName;
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
    public String getSplitRegex() {
        return splitRegex;
    }

    /**
     * Setter method for property <tt>splitRegex</tt>.
     * 
     * @param splitRegex value to be assigned to property splitRegex
     */
    public void setSplitRegex(String splitRegex) {
        this.splitRegex = splitRegex;
    }

    /**
     * Getter method for property <tt>groupFieldPosition</tt>.
     * 
     * @return property value of groupFieldPosition
     */
    public String getGroupFieldPosition() {
        return groupFieldPosition;
    }

    /**
     * Setter method for property <tt>groupFieldPosition</tt>.
     * 
     * @param groupFieldPosition value to be assigned to property groupFieldPosition
     */
    public void setGroupFieldPosition(String groupFieldPosition) {
        this.groupFieldPosition = groupFieldPosition;
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

}
