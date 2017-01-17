/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MemoryCpuTest.java, v0.1 2016年12月22日 下午12:19:19 czhou3306@gmail.com Exp $
 */
public class MemoryCpuTest {
    public static void main(String[] args) throws InterruptedException {
        cpuFix();
    }

    /**
     * cpu 运行固定百分比
     * 
     * @throws InterruptedException
     */
    public static void cpuFix() throws InterruptedException {
        // 80%的占有率
        int busyTime = 1;
        // 20%的占有率
        int idelTime = 9;
        // 开始时间
        long startTime = 0;
        while (true) {
            // 开始时间
            startTime = System.currentTimeMillis();
            /*
             * 运行时间
             */
            while (System.currentTimeMillis() - startTime < busyTime) {
                ;
            }
            // 休息时间
            Thread.sleep(idelTime);
        }
    }
}
