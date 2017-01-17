/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.shared;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.qiangungun.monitor.biz.shared.helper.QlExpressUtil;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: QlExpressUtilTest.java, v0.1 2016年12月6日 下午1:05:30 czhou3306@gmail.com Exp $
 */
public class QlExpressUtilTest extends TestCase {

    public void testa() throws Exception {

        QlExpressUtil q = new QlExpressUtil();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("#3#", new BigDecimal("50"));
        m.put("#4#", new BigDecimal("100"));
        Object o = q.execute("100 * #3#/#4#", m);
        System.err.println(o);
        System.err.println((BigDecimal) o);

    }
}
