/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.qiangungun.monitor.common.enums.TimeGroupEnum;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: LineData.java, v0.1 2016年12月1日 下午4:26:41 czhou3306@gmail.com Exp $
 */
public class LineComputeData extends BaseModel implements Comparable<LineComputeData> {

    private String                  date;

    /**以字段索引为key*/
    private Map<Integer, String>    fieldValue = new HashMap<Integer, String>();

    /**以computeId为key*/
    private Map<String, BigDecimal> computeMap = new HashMap<String, BigDecimal>();

    public LineComputeData(String[] array, TimeGroupEnum timeGroup, List<ComputeFormula> computeList) {

        date = parseDate(array[0], timeGroup);

        Map<String, BigDecimal> temp = new HashMap<String, BigDecimal>();
        for (int i = 1; i < array.length; i++) {
            String[] keyValue = array[i].split(":");
            if (StringUtils.isNumeric(keyValue[0])) {
                if (keyValue.length < 2) {
                    fieldValue.put(Integer.parseInt(keyValue[0]), StringUtils.EMPTY);
                } else {
                    fieldValue.put(Integer.parseInt(keyValue[0]), keyValue[1]);
                }

            } else {
                temp.put(keyValue[0], new BigDecimal(keyValue[1]));
            }
        }

        for (ComputeFormula cf : computeList) {
            if (checkFilter(cf.getFilter())) {
                computeMap.put(cf.getComputeId(), temp.get(cf.getComputeKey()));
            } else {
                computeMap.put(cf.getComputeId(), new BigDecimal("0"));
            }
        }

    }

    public LineComputeData(String[] array, List<ComputeFormula> computeList) {

        this(array, TimeGroupEnum.MIN, computeList);

    }

    private String parseDate(String date, TimeGroupEnum timeGroup) {

        if (timeGroup == TimeGroupEnum.TEN_MIN || timeGroup == TimeGroupEnum.HOUR) {
            return StringUtils.substring(date, 0, timeGroup.getLength());
        }

        return date;
    }

    private boolean checkFilter(Map<Integer, String> filters) {
        for (Entry<Integer, String> entry : filters.entrySet()) {
            if (!fieldValue.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Getter method for property <tt>date</tt>.
     * 
     * @return property value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter method for property <tt>date</tt>.
     * 
     * @param date value to be assigned to property date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter method for property <tt>fieldValue</tt>.
     * 
     * @return property value of fieldValue
     */
    public Map<Integer, String> getFieldValue() {
        return fieldValue;
    }

    /**
     * Getter method for property <tt>computeMap</tt>.
     * 
     * @return property value of computeMap
     */
    public Map<String, BigDecimal> getComputeMap() {
        return computeMap;
    }

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(LineComputeData o) {
        if (StringUtils.equals(this.date, o.getDate())) {
            return 0;
        }
        Long thisDateLong = new Long(this.date);
        Long oDateLong = new Long(o.getDate());
        return thisDateLong < oDateLong ? 1 : -1;
    }

}
