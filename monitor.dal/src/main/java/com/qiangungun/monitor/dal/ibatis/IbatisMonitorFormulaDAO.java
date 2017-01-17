/*
 * qiangungun.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */package com.qiangungun.monitor.dal.ibatis;
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
	 *  Query DB table <tt>monitor_formula</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, monitor_id, file_id, monitor_name, show_field, formula, gmt_create, gmt_modified from monitor_formula</tt>
	 *
	 *	@return List<MonitorFormulaDO>
	 *	@throws DataAccessException
	 */	 
    public List<MonitorFormulaDO> getAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-MONITOR-FORMULA-GET-ALL", null);

    }		/**
	 *  Insert one <tt>MonitorFormulaDO</tt> object to DB table <tt>monitor_formula</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into monitor_formula(monitor_id,file_id,monitor_name,show_field,formula,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param monitorFormula
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int insert(MonitorFormulaDO monitorFormula) throws DataAccessException {
    	if (monitorFormula == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-MONITOR-FORMULA-INSERT", monitorFormula);

        return monitorFormula.getId();
    }		/**
	 *  Update DB table <tt>monitor_formula</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update monitor_formula set monitor_name=?, show_field=?, formula=? where (monitor_id = ?)</tt>
	 *
	 *	@param monitorName
	 *	@param showField
	 *	@param formula
	 *	@param monitorId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(String monitorName, String showField, String formula, String monitorId) throws DataAccessException {
        Map<String,Object> param = new HashMap<String,Object>();

        param.put("monitorName", monitorName);
        param.put("showField", showField);
        param.put("formula", formula);
        param.put("monitorId", monitorId);

        return getSqlMapClientTemplate().update("MS-MONITOR-FORMULA-UPDATE", param);
    }	}	