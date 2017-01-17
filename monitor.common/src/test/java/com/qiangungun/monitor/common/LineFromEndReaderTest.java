/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common;

import java.io.File;

import junit.framework.TestCase;

import com.qiangungun.monitor.common.util.LineFromEndReader;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: LineFromEndReaderTest.java, v0.1 2016年12月21日 下午2:02:22 czhou3306@gmail.com Exp $
 */
public class LineFromEndReaderTest extends TestCase{
    
    public void testReader(){
        LineFromEndReader reader = new LineFromEndReader(new File("D:/172.16.151.160"));
        String line = null;
        while((line = reader.readLineHaveContent())!=null){
            System.err.println(line);
        }
        System.err.println("aa");
    }

}
