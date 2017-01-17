/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: ErrorGatherDataTest.java, v0.1 2016年12月3日 下午11:57:25 czhou3306@gmail.com Exp $
 */
public class ErrorGatherDataTest extends TestCase {

    public void testa() {
        
     //   29/Nov/2016:17:02:19
        
        SimpleDateFormat formatter = new SimpleDateFormat ("M");
       
        
        System.err.println(new Date().toGMTString());
        
    }
}
