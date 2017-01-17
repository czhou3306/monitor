/*
 * qiangungun.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */package com.qiangungun.monitor.dal.ibatis;
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
	 *  Query DB table <tt>gather_file</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, file_id, system_name, file_path, split_regex, group_field_position, instance, gather_type, gmt_create, gmt_modified from gather_file</tt>
	 *
	 *	@return List<GatherFileDO>
	 *	@throws DataAccessException
	 */	 
    public List<GatherFileDO> getAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-GATHER-FILE-GET-ALL", null);

    }		/**
	 *  Insert one <tt>GatherFileDO</tt> object to DB table <tt>gather_file</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into gather_file(file_id,system_name,file_path,split_regex,group_field_position,instance,gather_type,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param gatherFile
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int insert(GatherFileDO gatherFile) throws DataAccessException {
    	if (gatherFile == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-GATHER-FILE-INSERT", gatherFile);

        return gatherFile.getId();
    }		/**
	 *  Update DB table <tt>gather_file</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update gather_file set system_name=?, file_path=?, split_regex=?, group_field_position=?, instance=?, gather_type=? where (file_id = ?)</tt>
	 *
	 *	@param systemName
	 *	@param filePath
	 *	@param splitRegex
	 *	@param groupFieldPosition
	 *	@param instance
	 *	@param gatherType
	 *	@param fileId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(String systemName, String filePath, String splitRegex, String groupFieldPosition, String instance, String gatherType, String fileId) throws DataAccessException {
        Map<String,Object> param = new HashMap<String,Object>();

        param.put("systemName", systemName);
        param.put("filePath", filePath);
        param.put("splitRegex", splitRegex);
        param.put("groupFieldPosition", groupFieldPosition);
        param.put("instance", instance);
        param.put("gatherType", gatherType);
        param.put("fileId", fileId);

        return getSqlMapClientTemplate().update("MS-GATHER-FILE-UPDATE", param);
    }	}	