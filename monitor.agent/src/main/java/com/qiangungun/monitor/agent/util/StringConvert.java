/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: StringConvert.java, v0.1 2016年12月7日 下午2:10:29 czhou3306@gmail.com Exp $
 */
public class StringConvert {

    private static final Logger logger = LoggerFactory.getLogger(StringConvert.class);

    /**
     * 用了tailer,读取的字符串编码有问题,在这里处理下
     * 
     * @param origin
     * @param charsetName
     * @return
     */
    public static String convertString(String origin, String charsetName) {
        try {
            char[] carray = origin.toCharArray();
            byte[] barray = new byte[carray.length];
            for (int i = 0; i < carray.length; i++) {
                barray[i] = (byte) carray[i];
            }
            return new String(barray, charsetName);
        } catch (Exception e) {
            logger.error("字符串转换出错", e);
            return null;
        }
    }
}
