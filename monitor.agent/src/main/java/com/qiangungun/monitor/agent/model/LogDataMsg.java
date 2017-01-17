/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: LogDataMsg.java, v0.1 2016年11月20日 下午9:01:43 czhou3306@gmail.com Exp $
 */
public class LogDataMsg extends BaseMsg {

    /**  */
    private static final long serialVersionUID = 5622188524777960457L;

    private String            fileId;
    /**每行的内容*/
    private String            lineData;

    private String            filePath;

    /**
     * @param msgType
     */
    public LogDataMsg() {
        super(MsgType.LOG_DATA);
    }

    /**
     * Getter method for property <tt>lineData</tt>.
     * 
     * @return property value of lineData
     */
    public String getLineData() {
        return lineData;
    }

    /**
     * Setter method for property <tt>lineData</tt>.
     * 
     * @param lineData value to be assigned to property lineData
     */
    public void setLineData(String lineData) {
        this.lineData = lineData;
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

}
