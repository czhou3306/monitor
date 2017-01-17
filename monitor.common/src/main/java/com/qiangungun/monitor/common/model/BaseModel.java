/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: DomainModel.java, v0.1 2015年11月18日 下午3:50:50 czhou3306@gmail.com Exp $
 */
public abstract class BaseModel {

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
