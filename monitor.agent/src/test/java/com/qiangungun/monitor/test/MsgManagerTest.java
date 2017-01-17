/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import junit.framework.TestCase;

import com.qiangungun.monitor.agent.comm.MsgManager;
import com.qiangungun.monitor.agent.model.QueryCommandRequest;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MsgManagerTest.java, v0.1 2016年11月21日 上午9:47:02 czhou3306@gmail.com Exp $
 */
public class MsgManagerTest extends TestCase{

    public void testPrint(){
        MsgManager.instance().addSentMsg(new QueryCommandRequest());
        
        System.err.println("xx");
    }
}
