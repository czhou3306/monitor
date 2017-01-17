/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common;

import com.qiangungun.monitor.common.enums.FormulaTypeEnum;
import com.qiangungun.monitor.common.util.CoreDateUtils;
import com.qiangungun.monitor.common.util.LockUtil;
import junit.framework.TestCase;

import java.util.Date;
import java.util.concurrent.locks.Lock;

/**
 * @author czhou3306@gmail.com
 * @version $Id: LockUtilTest.java, v0.1 2016年11月24日 上午9:42:06 czhou3306@gmail.com Exp $
 */
public class LockUtilTest extends TestCase {

    public static int count = 0;

    public void testP() throws Exception {
        FormulaTypeEnum e = FormulaTypeEnum.getByCode("SUM");
        System.err.println(e);
        Object o = new Object();
        synchronized (o) {
            o.wait(2000);
        }

        System.err.println("game over");
    }

    public void testThread() throws Exception {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        Lock lock = LockUtil.getLock("1");
                        try {
                            lock.lock();
                            count++;
                            System.err.println(count + ":" + CoreDateUtils.yyyymmddhh24miss(new Date()));
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }).start();
            System.err.println("start");
        }

        Thread.sleep(Integer.MAX_VALUE);
    }
}
