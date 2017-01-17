/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherFile.java, v0.1 2016年12月2日 下午4:43:35 czhou3306@gmail.com Exp $
 */
public class GatherFileConfig implements Serializable {

    /**  */
    private static final long serialVersionUID = -6587741487628120254L;

    private String            fileId;

    private String            filePath;

    private String            gatherType;

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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
