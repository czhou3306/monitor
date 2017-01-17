/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message.split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: AccessLogSplitor.java, v0.1 2016年12月4日 下午6:05:02 czhou3306@gmail.com Exp $
 */
public class AccessLogSplitor implements Splitor {

    private static final Map<String, Integer> map = new HashMap<String, Integer>();

    static {
        map.put("Jan", 1);
        map.put("Feb", 2);
        map.put("Mar", 3);
        map.put("Apr", 4);
        map.put("May", 5);
        map.put("Jun", 6);
        map.put("Jul", 7);
        map.put("Aug", 8);
        map.put("Sep", 9);
        map.put("Oct", 10);
        map.put("Nov", 11);
        map.put("Dec", 12);
    }

    /** 
     * @see com.qiangungun.monitor.biz.collect.message.split.Splitor#split(java.lang.String, java.util.List)
     */
    @Override
    public String[] split(String line, List<String> regex) {
        //172.16.160.1 - - [29/Nov/2016:17:02:19 +0800] "POST /v2/plate/queryPlateDetail HTTP/1.1" 200 4192
        String dateStr = StringUtils.substringBetween(line, "[", "]");
        String year = StringUtils.substring(dateStr, 7, 11);
        String month = StringUtils.substring(dateStr, 3, 6);
        String day = StringUtils.substring(dateStr, 0, 2);

        String standardDate = year + "-" + map.get(month) + "-" + day + " "
                              + StringUtils.substring(dateStr, 12, 20);

        String data = StringUtils.substring(line, 0, line.indexOf("["))
                      + StringUtils.substring(line, line.indexOf("]")+1);

        data = StringUtils.replace(data, "\"", "");
        List<String> result = new ArrayList<String>();
        result.add(standardDate);

        String[] array = StringUtils.split(data, " ");
        for (String s : array) {
            if (!StringUtils.isBlank(s)) {
                result.add(s);
            }
        }

        return result.toArray(new String[result.size()]);

    }
}
