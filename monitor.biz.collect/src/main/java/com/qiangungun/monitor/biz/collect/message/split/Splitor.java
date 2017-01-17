/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.collect.message.split;

import java.util.List;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: Splitor.java, v0.1 2016年11月22日 下午1:36:07 czhou3306@gmail.com Exp $
 */
public interface Splitor {

    String[] split(String line, List<String> regex);

}
