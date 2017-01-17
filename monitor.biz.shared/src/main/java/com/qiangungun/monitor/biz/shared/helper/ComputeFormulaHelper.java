/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.shared.helper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qiangungun.monitor.common.model.LineComputeData;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ComputeFormulaHelper.java, v0.1 2016年12月6日 下午12:43:20 czhou3306@gmail.com Exp $
 */
@Service("computeFormulaHelper")
public class ComputeFormulaHelper {

    private static final Logger logger = LoggerFactory.getLogger(ComputeFormulaHelper.class);

    @Resource
    private QlExpressUtil       qlExpressUtil;

    /**
     * #3#/#4#
     * 
     * @param expression
     * @param lcd
     * @return
     */
    public BigDecimal compute(String expression, LineComputeData lcd) {
        try {
            Map<String, Object> temp = new HashMap<String, Object>();
            for (Entry<String, BigDecimal> entry : lcd.getComputeMap().entrySet()) {
                temp.put("#" + entry.getKey() + "#", entry.getValue());
            }
            return (BigDecimal) qlExpressUtil.execute(expression, temp);
        } catch (Exception e) {
            logger.error("计算异常", e);
            return new BigDecimal(0);
        }

    }

}
