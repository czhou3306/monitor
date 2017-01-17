/**
 * Qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.common.util;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: RegularExpConstant.java, v 0.1 2015-1-3 下午7:38:19 czhou3306@gmail.com Exp $
 */
public class RegularExpConstant {

    public final static String TEST                              = "test";

    /** 手机格式的正则表达式 */
    public final static String REGEX_MOBILE                      = "^[1](3|4|5|8|7)[0-9]{9}$";

    /** 大陆或香港或澳门地区手机正则表达式，不包含区号 */
    public final static String REGEX_MOBILE_CNHKMO               = "^([1](3|4|5|8)[0-9]{9})|([0-9]{8})$";

    /** 手机区号正则表达式 */
    public final static String REGEX_MOBILE_AREA                 = "^(86|852|853)$";

    /** 国际手机号码 */
    public final static String REGEX_INTER_MOBILE                = "^\\+(852|853)\\-([0-9]{8})$";

    /** Email格式的正则表达式WebEnum*/
    public final static String REGEX_EMAIL                       = "^([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";

    /** 手机号或者email*/
    public final static String REGEX_LOGON_ID                    = "^(([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+)|([1](3|4|5|8)[0-9]{9})$";

    /** 手机6位数字校验码*/
    public final static String REGUX_VALIDATE_CODE               = "^\\d{6}$";

    /** 手机6位数字校验码,字母与数字混合*/
    public final static String REGUX_VALIDATE_CODE_EX            = "^[a-zA-Z0-9]{6}$";

    /** 16位纯数字流水号*/
    public final static String REGEX_VALIDATE_BIZ_NO             = "^\\d{16}$";

    /** 4位图片校验码*/
    public final static String REGEX_PIC_CODE                    = "^\\d{4}$";

    /** 新图片校验码，4位，支持全角字符，字母与数字混合：1111，aaaa，11aA，１１aｓ*/
    public final static String REGEX_PIC_CODE_NEW                = "^[a-zA-Z0-9ａ-ｚＡ-Ｚ０-９]{4}$";

    /** 电话号码，只能由数字和"-"组成,6到20位。*/
    public final static String REGEX_PHONE                       = "^[0-9,-]{6,20}$";

    /** 邮政编码,只能6位数字*/
    public final static String POST                              = "^\\d{1,6}$";

    /** 用户姓名,只能由中文汉字、大写英文字母或.构成*/
    public final static String REGEX_USER_NAME                   = "^([\u4e00-\u9fa5|A-Z|\\s]|\u3007)+([\\.\uff0e\u00b7\u30fb]?|\u3007?)+([\u4e00-\u9fa5|A-Z|\\s]|\u3007)+$";

    /** 银行卡全卡号必须是15到19位数字
    public final static String REGEX_BANK_CARD_NO                = "^\\d{15,19}$";*/

    /** 纯数字*/
    public final static String NUM                               = "^(\\d)+$";

    /** 金额，类似于-135,342,12.954*/
    public final static String REGEX_VALIDATE_MONEY              = "^[+-]?\\d+(,\\d{3})*(\\.\\d+)?$";

    /** 手机动态口令，可以不输入，输入长度只能数字长度6位*/
    public final static String MOBILE_PROTECT_ACK_CODE           = "^\\d{0}|\\d{6}$";

    /** 图片校验码表单校验bean名称*/
    public final static String PIC_CHECK_CODE_VALIDATE_CONDITION = "pictureCheckCodeCondition";

    /** 银行卡全卡号必须是15到19位数字 */
    public final static String REGEX_CREDIT_CARD_NO              = "^\\d{15,18}$";

    /**身份证号的正则*/
    public final static String REGEX_CERT_NO                     = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
}
