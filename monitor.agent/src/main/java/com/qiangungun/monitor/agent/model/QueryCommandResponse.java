/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: QueryCommandResponse.java, v0.1 2016年11月20日 下午9:34:45 czhou3306@gmail.com Exp $
 */
public class QueryCommandResponse extends BaseMsg {

    /**  */
    private static final long serialVersionUID = 4937413920908922279L;

    /**
     * @param msgType
     */
    public QueryCommandResponse() {
        super(MsgType.QUERY_COMMAND_RESPONSE);
    }

    private List<GatherFileConfig> fileList = new ArrayList<GatherFileConfig>();

    /**
     * Getter method for property <tt>fileList</tt>.
     * 
     * @return property value of fileList
     */
    public List<GatherFileConfig> getFileList() {
        return fileList;
    }

    /**
     * Setter method for property <tt>fileList</tt>.
     * 
     * @param fileList value to be assigned to property fileList
     */
    public void setFileList(List<GatherFileConfig> fileList) {
        this.fileList = fileList;
    }

}
