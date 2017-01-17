/*
 * qiangungun.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */package com.qiangungun.monitor.dal.ibatis;
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/compute_formula.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>financenet-dalgen</tt> 
 * to generate this file.
 *
 */
	 *  Query DB table <tt>compute_formula</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, compute_id, compute_name, file_id, formula_type, field_position, filter_condition, gmt_create, gmt_modified from compute_formula</tt>
	 *
	 *	@return List<ComputeFormulaDO>
	 *	@throws DataAccessException
	 */	 
    public List<ComputeFormulaDO> getAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-COMPUTE-FORMULA-GET-ALL", null);

    }		/**
	 *  Query DB table <tt>compute_formula</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, compute_id, compute_name, file_id, formula_type, field_position, filter_condition, gmt_create, gmt_modified from compute_formula where (file_id = ?)</tt>
	 *
	 *	@param fileId
	 *	@return List<ComputeFormulaDO>
	 *	@throws DataAccessException
	 */	 
    public List<ComputeFormulaDO> getByFileId(String fileId) throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-COMPUTE-FORMULA-GET-BY-FILE-ID", fileId);

    }		/**
	 *  Insert one <tt>ComputeFormulaDO</tt> object to DB table <tt>compute_formula</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into compute_formula(compute_id,compute_name,file_id,formula_type,field_position,filter_condition,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param computeFormula
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int insert(ComputeFormulaDO computeFormula) throws DataAccessException {
    	if (computeFormula == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-COMPUTE-FORMULA-INSERT", computeFormula);

        return computeFormula.getId();
    }		/**
	 *  Update DB table <tt>compute_formula</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update compute_formula set compute_name=?, formula_type=?, field_position=?, filter_condition=? where (compute_id = ?)</tt>
	 *
	 *	@param computeName
	 *	@param formulaType
	 *	@param fieldPosition
	 *	@param filterCondition
	 *	@param computeId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(String computeName, String formulaType, String fieldPosition, String filterCondition, String computeId) throws DataAccessException {
        Map<String,Object> param = new HashMap<String,Object>();

        param.put("computeName", computeName);
        param.put("formulaType", formulaType);
        param.put("fieldPosition", fieldPosition);
        param.put("filterCondition", filterCondition);
        param.put("computeId", computeId);

        return getSqlMapClientTemplate().update("MS-COMPUTE-FORMULA-UPDATE", param);
    }	}	