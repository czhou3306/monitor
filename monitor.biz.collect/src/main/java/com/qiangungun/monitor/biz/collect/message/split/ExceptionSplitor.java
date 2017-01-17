/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message.split;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ExceptionSplitor.java, v0.1 2016年12月3日 下午9:38:34 czhou3306@gmail.com Exp $
 */
public class ExceptionSplitor implements Splitor {

    /** 
     * @see com.qiangungun.monitor.biz.collect.message.split.Splitor#split(java.lang.String, java.util.List)
     */
    @Override
    public String[] split(String line, List<String> regex) {

        List<String> list = new LinkedList<String>();

        //分割出来时间字段
        String date = line.substring(0, 23);

        list.add(date);
        list.add(line);

        return list.toArray(new String[list.size()]);

    }

}
