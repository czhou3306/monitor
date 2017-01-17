/**
 * Qiangungun.com Inc.
 *
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.util;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huwenfei on 2016/1/13.
 */
public class JSONObjectUtil {

    private static final Logger logger = LoggerFactory.getLogger(JSONObjectUtil.class);

    public static String objectToJsonFilter(Object object) {
        JsonConfig jsonConfig = new JsonConfig();
        PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName, Object fieldValue) {
                return null == fieldValue;
            }
        };
        jsonConfig.setJsonPropertyFilter(filter);
        logger.info("Object To Json Filter:"+JSONObject.fromObject(object, jsonConfig).toString());
        System.out.println(JSONObject.fromObject(object, jsonConfig).toString());
        return JSONObject.fromObject(object, jsonConfig).toString();
    }

}
