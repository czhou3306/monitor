/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: LineGatherData.java, v0.1 2016年12月3日 下午9:11:43 czhou3306@gmail.com Exp $
 */
public class LineGatherData extends GatherData {

    

    /**key为位置,如:2*/
    // private Map<Integer, String>                  groupFieldValueMap;

    private String                                groupFieldValueString;

    /**单位时间内上送的次数*/
    private AtomicLong                            count      = new AtomicLong(0);

    /**以compute_key为可用*/
    private ConcurrentHashMap<String, BigDecimal> computeMap = new ConcurrentHashMap<String, BigDecimal>();

    public LineGatherData(String app, String fileId, Date occureDate, String hostName, String ip,
                          Map<Integer, String> groupFieldValueMap,
                          Collection<ComputeFormula> computeKeyList) {

        super(app, fileId, occureDate, hostName, ip);

        // this.groupFieldValueMap = groupFieldValueMap;
        for (ComputeFormula cf : computeKeyList) {
            computeMap.put(cf.getComputeKey(), new BigDecimal("0"));
        }

        StringBuilder builder = new StringBuilder();

        for (Integer key : groupFieldValueMap.keySet()) {
            builder.append(key + ":" + groupFieldValueMap.get(key));
            builder.append(KeyConstant.SPLIT_FLAG);
        }

        groupFieldValueString = builder.toString();
    }

    /**
     * Getter method for property <tt>groupFieldValueString</tt>.
     * 
     * @return property value of groupFieldValueString
     */
    public String getGroupFieldValueString() {
        return groupFieldValueString;
    }

    /**
     * Setter method for property <tt>groupFieldValueString</tt>.
     * 
     * @param groupFieldValueString value to be assigned to property groupFieldValueString
     */
    public void setGroupFieldValueString(String groupFieldValueString) {
        this.groupFieldValueString = groupFieldValueString;
    }

    /**
     * Getter method for property <tt>count</tt>.
     * 
     * @return property value of count
     */
    public AtomicLong getCount() {
        return count;
    }

    /**
     * Setter method for property <tt>count</tt>.
     * 
     * @param count value to be assigned to property count
     */
    public void setCount(AtomicLong count) {
        this.count = count;
    }

    /**
     * Getter method for property <tt>computeMap</tt>.
     * 
     * @return property value of computeMap
     */
    public ConcurrentHashMap<String, BigDecimal> getComputeMap() {
        return computeMap;
    }

    public String toFileData() {

        StringBuilder buffer = new StringBuilder();
        buffer.append(this.getDateValue());
        buffer.append(KeyConstant.SPLIT_FLAG);
        buffer.append(groupFieldValueString);

        buffer.append("C:" + count);
        buffer.append(KeyConstant.SPLIT_FLAG);

        for (String key : computeMap.keySet()) {
            buffer.append(key + ":" + computeMap.get(key));
            buffer.append(KeyConstant.SPLIT_FLAG);
        }

        buffer.append("\n");
        return buffer.toString();
    }

}
