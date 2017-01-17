/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.web.form;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: BaseForm.java, v0.1 2015年11月16日 下午8:06:23 czhou3306@gmail.com Exp $
 */
public class BaseForm {

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
