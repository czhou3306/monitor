/*
 * qiangungun.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */package com.qiangungun.monitor.dal.dataobject;

// auto generated imports
import java.util.Date;

/**
 * A data object class directly models database table <tt>monitor_formula</tt>.
 *
 * This file is generated by <tt>financenet-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/monitor_formula.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>financenet-dalgen</tt> 
 * to generate this file.
 *
 */
public class MonitorFormulaDO {
    
    //========== properties ==========

	/**
	 * This property corresponds to db column <tt>id</tt>.
	 */
	private int id;

	/**
	 * This property corresponds to db column <tt>monitor_id</tt>.
	 */
	private String monitorId;

	/**
	 * This property corresponds to db column <tt>file_id</tt>.
	 */
	private String fileId;

	/**
	 * This property corresponds to db column <tt>monitor_name</tt>.
	 */
	private String monitorName;

	/**
	 * This property corresponds to db column <tt>show_field</tt>.
	 */
	private String showField;

	/**
	 * This property corresponds to db column <tt>formula</tt>.
	 */
	private String formula;

	/**
	 * This property corresponds to db column <tt>gmt_create</tt>.
	 */
	private Date gmtCreate;

	/**
	 * This property corresponds to db column <tt>gmt_modified</tt>.
	 */
	private Date gmtModified;

    //========== getters and setters ==========

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter method for property <tt>id</tt>.
	 * 
	 * @param id value to be assigned to property id
     */
	public void setId(int id) {
		this.id = id;
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

    /**
     * Getter method for property <tt>gmtCreate</tt>.
     *
     * @return property value of gmtCreate
     */
	public Date getGmtCreate() {
		return gmtCreate;
	}
	
	/**
	 * Setter method for property <tt>gmtCreate</tt>.
	 * 
	 * @param gmtCreate value to be assigned to property gmtCreate
     */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

    /**
     * Getter method for property <tt>gmtModified</tt>.
     *
     * @return property value of gmtModified
     */
	public Date getGmtModified() {
		return gmtModified;
	}
	
	/**
	 * Setter method for property <tt>gmtModified</tt>.
	 * 
	 * @param gmtModified value to be assigned to property gmtModified
     */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}