/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.test;

import com.qiangungun.monitor.agent.model.LogDataMsg;
import com.qiangungun.monitor.agent.model.MsgType;
import com.qiangungun.monitor.biz.collect.message.MessageHandler;
import com.qiangungun.monitor.common.util.CoreSystemUtils;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: BusinessActivityServiceTest.java, v 0.1 2015年5月30日 下午3:44:31 czhou3306@gmail.com Exp $
 */
@ContextConfiguration(locations = { "classpath:biz-test-bean.xml",
                                   "classpath:META-INF/spring/spring-monitor-dal-dao.xml",
                                   "classpath:test-ds.xml",
                                   "classpath:META-INF/spring/spring-sequence.xml" })
public class LogDataMsgHandlerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MessageHandler logDataMsgHandler;

    @Test
    public void testNotNull() {
        Assert.assertNotNull(logDataMsgHandler);
    }

    @Test
    public void testSplit() throws Exception {
        LogDataMsg baseMsg = new LogDataMsg();
        baseMsg.setApp("mobile");
        baseMsg.setFilePath("/opt/logs/mobile/remote-digest.log");
        baseMsg.setFileId("1764");
        baseMsg.setHostName("mobile-sit.gungunqian.cn");
        baseMsg.setIp("172.16.150.51");
        baseMsg
            .setLineData("2016-12-13 14:44:36,836 INFO [(com.qiangungun.mobile.integration.facade.basic.impl.SmsDynamicCodeServiceClientImpl.verifyDynamicCode,S,10ms)]<uuid=76ecff3fad6b2b094600128d>");
        baseMsg.setMsgId(CoreSystemUtils.objectId());
        baseMsg.setMsgType(MsgType.LOG_DATA);
        logDataMsgHandler.handle(baseMsg);

        Thread.sleep(Integer.MAX_VALUE);
    }

}
