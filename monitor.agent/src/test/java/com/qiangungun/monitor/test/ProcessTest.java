/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ProcessTest.java, v0.1 2016年11月29日 下午12:49:42 czhou3306@gmail.com Exp $
 */
public class ProcessTest extends TestCase {

    public void testP() throws Exception {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Runtime time = Runtime.getRuntime();
                    String[] array = new String[] {"cmd /c ping -t www.baidu.com"};
                    Process process1 = time.exec(array);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(process1
                        .getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.err.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000000);
    }
}
