/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.biz.shared.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: AppIdHelper.java, v0.1 2016年3月2日 下午10:16:11 czhou3306@gmail.com Exp $
 */
@Service("appIdHelper")
public class AppIdHelper {

    @Value("${appId}")
    private String appId;

    public String getAppId() {
        return appId;
    }

}
