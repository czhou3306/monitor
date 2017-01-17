package com.qiangungun.monitor.common.util;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chengkaijie on 2017/1/14.
 */
public abstract class CoreObjectUtils {

    private static final Logger LOG = LoggerFactory.getLogger(CoreObjectUtils.class);

    private CoreObjectUtils() {
        ; // nothing
    }

    /**
     * 将java实例转换为json对象，按field进行转换<br/>
     *
     * @param obj
     * @return
     */
    public static String object2Json(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, false);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOG.warn("Object to Json error: ", e);
        }

        return null;
    }
}
