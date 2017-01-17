/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.util;

import java.math.BigDecimal;

/**
 * 中奖概率计算工具类
 *
 * @author czhou3306@gmail.com
 * @version $Id: AwardRateUtil.java, v0.1 2016年5月7日 下午3:52:09 czhou3306@gmail.com Exp $
 */
public class AwardRateUtil {

    /**
     * 计算本次中奖
     * 采取左闭右开的规则<br>
     * 如果有3个选项，A:20,B:30,C:10，这样会产生3个区间[0,20),[20,50),[50,60)，有3个游标，20,50,60，然后通过比较游标即可获得落在哪个区间上，
     * <br>
     * 
     * 
     * @param probabilities
     * @param settingId
     * @return
     */
    public static String winningCal(BigDecimal[] probabilities, String[] settingId) {

        //计算比较游标
        for (int i = 1; i < probabilities.length; i++) {
            probabilities[i] = probabilities[i].add(probabilities[i - 1]);
        }

        //用%比，所以要*100
        BigDecimal random = new BigDecimal(Math.random()).multiply(new BigDecimal(100));

        int index = 0;
        for (int i = 0; i < probabilities.length; i++) {
            if (random.compareTo(probabilities[i]) < 0) {
                index = i;
                break;
            } else {
                index++;
            }
        }

        if (index > (settingId.length - 1)) {
            //已经越界，说明没中奖
            return null;
        }

        return settingId[index];

    }

}
