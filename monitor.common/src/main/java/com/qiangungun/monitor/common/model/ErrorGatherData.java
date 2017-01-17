/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ErrorGatherData.java, v0.1 2016年12月3日 下午9:30:24 czhou3306@gmail.com Exp $
 */
public class ErrorGatherData extends GatherData {

    /**完整的错误信息*/
    private String     exceptionTrace;

    /**单位时间内上送的次数*/
    private AtomicLong count = new AtomicLong(0);

    public ErrorGatherData(String app, String fileId, Date occureDate, String hostName, String ip,
                           String exceptionTrace) {

        super(app, fileId, occureDate, hostName, ip);

        this.exceptionTrace = exceptionTrace;

    }

    /**
     * Getter method for property <tt>exceptionTrace</tt>.
     * 
     * @return property value of exceptionTrace
     */
    public String getExceptionTrace() {
        return exceptionTrace;
    }

    /**
     * Setter method for property <tt>exceptionTrace</tt>.
     * 
     * @param exceptionTrace value to be assigned to property exceptionTrace
     */
    public void setExceptionTrace(String exceptionTrace) {
        this.exceptionTrace = exceptionTrace;
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
     * @see com.qiangungun.monitor.common.model.GatherData#toFileData()
     */
    @Override
    public String toFileData() {

        StringBuilder buffer = new StringBuilder();
        buffer.append(this.getDateValue());
        buffer.append(KeyConstant.SPLIT_FLAG);
        buffer.append("C:" + count);
        buffer.append(KeyConstant.SPLIT_FLAG);
        buffer.append(exceptionTrace);
        buffer.append("\n");
        return buffer.toString();
    }
}
