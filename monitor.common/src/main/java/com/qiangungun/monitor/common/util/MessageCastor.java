package com.qiangungun.monitor.common.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: MessageCastor.java, v0.1 2015年8月14日 下午1:24:53 czhou3306@gmail.com Exp $
 */
public class MessageCastor {

    /** logger */
    private static final Logger logger  = LoggerFactory.getLogger(MessageCastor.class);

    /** 扩展工具类 */
    private Map<String, Object> toolMap = new HashMap<String, Object>();

    /**
     * 兼容velocity
     * 
     * 将一个对象通过指定velocity模板转换为String报文格式
     * 
     * @param keyObj
     *            报文对象和报文取值路径配对列表
     * @param velocity
     *            报文模板
     * @return 报文字符串
     */
    public String cast(Map<String, Object> keyObj, String velocity) {
        return cast(keyObj, (Object) velocity);
    }

    /**
     * 兼容velocity
     * 
     * 将一个对象通过指定velocity模板转换为String报文格式
     * 
     * @param keyObj
     *            报文对象和报文取值路径配对列表
     * @param velocity
     *            报文模板
     * @return 报文字符串
     */
    private String cast(Map<String, Object> keyObj, Object velocity) {
        // 判断object类型是否合法
        String strVelocity = "";
        Reader rdrVelocity = null;
        if (velocity instanceof String) {
            /** 入参velocity为String类型 */
            strVelocity = String.valueOf(velocity);
        } else if (velocity instanceof Reader) {
            /** 入参velocity为Reader类型 */
            rdrVelocity = (Reader) velocity;
        }
        checkParam(keyObj, velocity, strVelocity, rdrVelocity);
        // 根据报文模板转换报文
        final VelocityContext velocityContext = new VelocityContext();
        /* 遍历属性 转化填写为velocity对象 */
        for (Map.Entry<String, Object> iKeyObj : keyObj.entrySet()) {
            velocityContext.put(iKeyObj.getKey(), iKeyObj.getValue());
        }
        // 注入工具类
        this.putTools(velocityContext);
        // 根据模板导出报文字符串
        StringWriter swSuperMessage = new StringWriter();
        /* 生成超级网关过程日志 */
        String logString = "";
        String strResult = null;
        // 根据velocity模板生成超级网关报文字符串
        /* 是否生成成功 */
        boolean isSuccess = false;
        try {
            if (rdrVelocity == null) {
                isSuccess = Velocity.evaluate(velocityContext, swSuperMessage, logString,
                    strVelocity);
            } else {
                isSuccess = Velocity.evaluate(velocityContext, swSuperMessage, logString,
                    rdrVelocity);
            }
        } catch (VelocityException e) {
            logger.error("velocity运行异常", e);
        } catch (Exception e) {
            logger.error("velocity运行异常", e);
        }
        // 输出结果
        if (isSuccess) { /* 若生成成功 返回赋值 */
            strResult = swSuperMessage.toString();
        } else { /* 若生成失败 返回null */
            logger.warn("转换报文失败");
        }
        // 关闭流
        try {
            if (swSuperMessage != null) {
                swSuperMessage.close();
            }
        } catch (IOException e) {
            logger.error("velocity运行异常", e);
        }
        //
        // 输出超级网关报文字符串
        //
        return strResult;
    }

    /**
     * 检验入参是否合法
     * 
     * @param keyObj        报文对象和报文取值路径配对列表
     * @param velocity      报文模板
     * @param strVelocity   字符串类型   - 报文模板
     * @param rdrVelocity   流类型             - 报文模板
     * @return
     */
    private boolean checkParam(Map<String, Object> keyObj, Object velocity, String strVelocity,
                               Reader rdrVelocity) {
        if (velocity == null | keyObj == null
            | (StringUtils.isBlank(strVelocity) & rdrVelocity == null)) {
          //  logger.warn("入参不合法!,keyObj:" + keyObj);
            return false;
        }
        return true;
    }

    /**
     * 兼容velocity
     * 
     * 注入工具类到velocity中
     * 
     * @param velocityContext
     */
    private void putTools(VelocityContext velocityContext) {
        for (String key : toolMap.keySet()) {
            velocityContext.put(key, toolMap.get(key));
        }
    }

    /**
     * Setter method for property <tt>toolMap</tt>.
     * 
     * @param toolMap
     *            value to be assigned to property toolMap
     */
    public void setToolMap(Map<String, Object> toolMap) {
        this.toolMap = toolMap;
    }

}