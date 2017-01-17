/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.shared.helper.operator;

import java.math.BigDecimal;

import com.ql.util.express.Operator;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MulOperator.java, v0.1 2016年12月13日 上午10:19:40 czhou3306@gmail.com Exp $
 */
public class MulOperator extends Operator{

    /**  */
    private static final long serialVersionUID = -4676624570560075689L;

    /** 
     * @see com.ql.util.express.Operator#executeInner(java.lang.Object[])
     */
    @Override
    public Object executeInner(Object[] list) throws Exception {
        BigDecimal a = (BigDecimal) list[0];
        BigDecimal b = (BigDecimal) list[1];
        return a.multiply(b);
    }

}
