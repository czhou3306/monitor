/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: TimerTest.java, v0.1 2016年11月20日 下午4:38:17 czhou3306@gmail.com Exp $
 */
public class TimerTest extends TestCase {

    public void testTimer() throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.err.println(new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
