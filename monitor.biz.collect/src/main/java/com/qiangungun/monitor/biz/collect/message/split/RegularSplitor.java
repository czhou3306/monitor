/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message.split;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: RegularSplit.java, v0.1 2016年11月22日 下午1:35:55 czhou3306@gmail.com Exp $
 */
public class RegularSplitor implements Splitor {

    private final static String DEFAULT_SPLIT_STR = "^";

    /** 
     * @see com.qiangungun.monitor.biz.collect.message.split.Splitor#split(java.lang.String)
     */
    @Override
    public String[] split(String line, List<String> regex) {

        List<String> list = new LinkedList<String>();

        //分割出来时间字段
        String date = line.substring(0, 23);
        String data = line.substring(23);
        data = StringUtils.replace(data, "\"", "");

        //用空格做分割
        for (String splitChar : regex) {
            data = StringUtils.replace(data, splitChar, DEFAULT_SPLIT_STR);
        }

        String[] result = split(data);

        list.add(date);
        for (String filed : result) {
            if (StringUtils.isBlank(filed)) {
                list.add(filed);
            } else {
                list.addAll(Arrays.asList(StringUtils.split(filed, " ")));
            }
        }

        return list.toArray(new String[list.size()]);
    }

    private String[] split(String ori) {

        List<String> list = new ArrayList<String>();
        int start = 0;
        for (int i = 0; i < ori.length(); i++) {
            if (String.valueOf(ori.charAt(i)).equals(DEFAULT_SPLIT_STR)) {
                list.add(StringUtils.trim(StringUtils.substring(ori, start, i)));
                start = i + 1;
            }
            if (i == ori.length() - 1) {
                list.add(StringUtils.trim(StringUtils.substring(ori, start, ori.length())));
            }
        }

        return list.toArray(new String[list.size()]);
    }

}
