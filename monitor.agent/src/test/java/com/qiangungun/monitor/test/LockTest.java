/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: LockTest.java, v0.1 2016年11月25日 下午5:08:31 czhou3306@gmail.com Exp $
 */
public class LockTest extends TestCase {

    public void testl() throws Exception {
        final Object o = new Object();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (o) {
                        System.err.println("lock");
                        o.wait();
                        System.err.println("unlock");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (o) {
                        System.err.println("notify");
                        o.notifyAll();
                        System.err.println("notifyend");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

        Thread.sleep(Integer.MAX_VALUE);
    }
}
