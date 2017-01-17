/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import com.qiangungun.monitor.common.util.CoreDateUtils;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: StringTest.java, v0.1 2016年11月22日 下午1:48:32 czhou3306@gmail.com Exp $
 */
public class StringTest extends TestCase {

    public void testBl() {
        String s = "#3#/#4#";
        String[] ar = StringUtils.substringsBetween(s, "#", "#");
        for (String s2 : ar) {
            System.err.println(s);
        }
    }

    public void atestFormula() {

        String in = "\"ms\",\",\",\"[(\",\")]\"";

        List<Integer> position = new LinkedList<Integer>();

        int flag = 0;
        while (flag >= 0) {
            int now = in.indexOf("\"", flag);
            if (now < 0) {
                break;
            }
            position.add(new Integer(now));
            flag = (now + 1);
        }

        Integer[] positions = position.toArray(new Integer[position.size()]);

        List<String> splitChars = new ArrayList<String>();
        for (int i = 0; i < position.size() / 2; i++) {
            splitChars.add(StringUtils.substring(in, positions[2 * i] + 1, positions[2 * i + 1]));
        }

        System.err.println(position.size());

        System.err.println(in.indexOf("\"", 0));

        //String formula = "sum($2)[$3=S]/count()";
        String instance = "2016-11-22 13:32:30,183 [(BusinessActivityDAO.updateStatus,S,5ms)]<uuid=0a8559d87c135d649900a1cc>";
        String[] array = instance.split("ms|\\,|\\[\\(|\\)\\]");

        String[] seps = new String[] { "ms", ")]", "," };

        String re = instance;
        for (String sep : seps) {
            re = StringUtils.replace(re, sep, " ");

        }
        //  System.err.println(re);

        String[] temp = StringUtils.split(re, " ");

        //   String[] temp = instance.split(" |,");

        for (String s : temp) {
            //      System.err.println(s);
        }

    }

    public void stestBigDecimal() {
        AtomicReference<BigDecimal> bd = new AtomicReference<BigDecimal>(new BigDecimal("3.3"));

        //bd = bd.multiply(new BigDecimal("3.3"));
        System.err.println(bd);

    }

    public void atestP() {
        List<String> list = new LinkedList<String>();

        String string = "2016-11-22 13:32:30,183 [(BusinessActivityDAO.updateStatus,S,5ms)]<uuid=0a8559d87c135d649900a1cc>";
        String date = string.substring(0, 23);
        String data = string.substring(23);

        String[] result = data.split("ms|\\,|\\[\\(|\\)\\]");
        //  System.err.println(result.length);

        list.add(date);
        for (String s : result) {
            if (StringUtils.isBlank(s)) {
                continue;
            }
            list.add(s);
        }

        for (String s : list) {
            System.err.println(s);
        }
    }

    public void atestD() {
        String date = "2016-11-22 20:32:30,183";

        System.err.println(CoreDateUtils.format(
            CoreDateUtils.parseToDate(date, CoreDateUtils.STANDARD_FORMAT), "yyyyMMddHHmm"));

    }
}
