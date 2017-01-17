/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungu.monitor.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.SysexMessage;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: DirTest.java, v0.1 2016年11月29日 下午10:42:55 czhou3306@gmail.com Exp $
 */
public class DirTest extends TestCase {

    public void testa() throws Exception {

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                System.err.println(Runtime.getRuntime().freeMemory() + ":"
                                   + Runtime.getRuntime().maxMemory() + ":"
                                   + Runtime.getRuntime().totalMemory() + ":"
                                   + Runtime.getRuntime().availableProcessors());
            }
        }, 1000, 1000);

        Map<String, byte[]> con = new HashMap<String, byte[]>();
        while (true) {
            con.put(String.valueOf(System.currentTimeMillis()), new byte[1024]);
            Thread.sleep(1000);
        }

    }
}
