/*
 * qiangungun.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */package com.qiangungun.monitor.dal.dataobject;

// auto generated imports
import java.util.Date;

/**
 * A data object class directly models database table <tt>gather_file</tt>.
 *
 * This file is generated by <tt>financenet-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/gather_file.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>financenet-dalgen</tt> 
 * to generate this file.
 *
 */
public class GatherFileDO {
    
    //========== properties ==========

	/**
	 * This property corresponds to db column <tt>id</tt>.
	 */
	private int id;

	/**
	 * This property corresponds to db column <tt>file_id</tt>.
	 */
	private String fileId;

	/**
	 * This property corresponds to db column <tt>system_name</tt>.
	 */
	private String systemName;

	/**
	 * This property corresponds to db column <tt>file_path</tt>.
	 */
	private String filePath;

	/**
	 * This property corresponds to db column <tt>split_regex</tt>.
	 */
	private String splitRegex;

	/**
	 * This property corresponds to db column <tt>group_field_position</tt>.
	 */
	private String groupFieldPosition;

	/**
	 * This property corresponds to db column <tt>instance</tt>.
	 */
	private String instance;

	/**
	 * This property corresponds to db column <tt>gather_type</tt>.
	 */
	private String gatherType;

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