/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: TailTest.java, v0.1 2016年11月26日 上午12:14:14 czhou3306@gmail.com Exp $
 */
public class TailTest extends TestCase {

    public void testa() throws Exception {
        Process process = Runtime.getRuntime().exec("tail -f D:/mobile/remote-digest.log");

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.err.println(new Date() + line);
        }
    }
}
