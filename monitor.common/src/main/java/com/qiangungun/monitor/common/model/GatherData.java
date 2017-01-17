/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import com.qiangungun.monitor.common.util.CoreDateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherData.java, v0.1 2016年11月22日 下午3:14:28 czhou3306@gmail.com Exp $
 */
public abstract class GatherData {

    private String app;

    private String fileId;

    private Date   occureDate;

    private String hostName;

    private String ip;

    /**yyyyMMddHHmm*/
    private String dateValue;

    protected GatherData(String app, String fileId, Date occureDate, String hostName, String ip) {

        this.app = app;
        this.fileId = fileId;
        this.occureDate = occureDate;
        this.hostName = hostName;
        this.ip = ip;

        this.dateValue = CoreDateUtils.format(occureDate, KeyConstant.LINE_DATE_FORMAT);

        if (StringUtils.isBlank(dateValue)) {
            throw new IllegalArgumentException("日期格式错误");
        }

    }

    public abstract String toFileData();

    /**
     * Getter method for property <tt>app</tt>.
     * 
     * @return property value of app
     */
    public String getApp() {
        return app;
    }

    /**
     * Setter method for property <tt>app</tt>.
     * 
     * @param app value to be assigned to property app
     */
    public void setApp(String app) {
        this.app = app;
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
     * Getter method for property <tt>occureDate</tt>.
     * 
     * @return property value of occureDate
     */
    public Date getOccureDate() {
        return occureDate;
    }

    /**
     * Setter method for property <tt>occureDate</tt>.
     * 
     * @param occureDate value to be assigned to property occureDate
     */
    public void setOccureDate(Date occureDate) {
        this.occureDate = occureDate;
    }

    /**
     * Getter method for property <tt>hostName</tt>.
     * 
     * @return property value of hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Setter method for property <tt>hostName</tt>.
     * 
     * @param hostName value to be assigned to property hostName
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Getter method for property <tt>ip</tt>.
     * 
     * @return property value of ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Setter method for property <tt>ip</tt>.
     * 
     * @param ip value to be assigned to property ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Getter method for property <tt>dateValue</tt>.
     * 
     * @return property value of dateValue
     */
    public String getDateValue() {
        return dateValue;
    }

    /**
     * Setter method for property <tt>dateValue</tt>.
     * 
     * @param dateValue value to be assigned to property dateValue
     */
    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

}
