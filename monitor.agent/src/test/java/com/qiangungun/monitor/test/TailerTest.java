/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import java.io.File;

import junit.framework.TestCase;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: TailerTest.java, v0.1 2016年11月29日 上午10:42:13 czhou3306@gmail.com Exp $
 */
public class TailerTest extends TestCase {
    StringBuilder builder = new StringBuilder(); ;

    public void testT() throws Exception {
        File file = new File("D:/mobile/remote-digest.log");

        Tailer t = new Tailer(file, new TailerListener() {

            @Override
            public void init(Tailer tailer) {
                System.err.println("init");
            }

            @Override
            public void handle(Exception ex) {
                System.err.println("handle");
            }

            @Override
            public void handle(String line) {

                if (line.contains("ERROR")) {
                    System.err.println("11" + builder.toString());
                    builder = new StringBuilder();
                    return;
                }
                builder.append(line + "\n");
            }

            @Override
            public void fileRotated() {
                System.err.println("fileRotated");
            }

            @Override
            public void fileNotFound() {
                System.err.println("fileNotFound");
            }
        }, 1000, true);

        new Thread(t).start();

        Thread.sleep(Integer.MAX_VALUE);
    }
}
