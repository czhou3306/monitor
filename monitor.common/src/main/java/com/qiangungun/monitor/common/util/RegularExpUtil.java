/**
 * Qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: RegularExpUtil.java, v 0.1 2015-1-3 下午7:41:05 czhou3306@gmail.com Exp $
 */
public class RegularExpUtil {

    /**
     * 判断输入的字符串是否和传入的正则是否匹配
     * 
     * @param src
     *            待判断的输入字符串
     * @param reg
     *            正则表达式
     * @return True:输入的字符串是否和传入的正则匹配 False:输入的字符串是否和传入的正则不匹配
     */
    public static boolean isValidReg(String src, String reg) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(reg);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断输入的字符串是否是手机号码格式
     * 
     * @param src
     *            待判断的输入字符串
     * @return True:是合法的手机号码;False:不是合法的手机号码
     */
    public static boolean isValidMobile(String src) {
        // 无效的输入，直接返回false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Pattern p = Pattern.compile(RegularExpConstant.REGEX_MOBILE);
        Matcher pm = p.matcher(src);
        return pm.matches();
    }

    /**
     * 判断是否是有效的身份证号
     * 
     * @param certNo
     * @return
     */
    public static boolean isValidCertNo(String certNo) {

        // 无效的输入，直接返回false
        if (StringUtils.isBlank(certNo)) {
            return false;
        }
        Pattern p = Pattern.compile(RegularExpConstant.REGEX_CERT_NO);
        Matcher pm = p.matcher(certNo);
        return pm.matches();
    }

}
