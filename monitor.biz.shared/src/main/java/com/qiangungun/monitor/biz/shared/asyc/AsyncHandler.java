/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.shared.asyc;

import java.util.Map;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: EventListener.java, v0.1 2015年11月27日 上午11:08:37 czhou3306@gmail.com Exp $
 */
public interface AsyncHandler {

    void handle(Map<String, Object> payload);

}
