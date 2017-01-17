/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: HookTest.java, v0.1 2016年11月20日 下午9:48:39 czhou3306@gmail.com Exp $
 */
public class HookTest {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("shutdownThread...");
            }
        });
    }

}
