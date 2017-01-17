package com.qiangungun.monitor.web.controller;

import com.qiangungun.monitor.common.enums.BizCodeEnum;
import com.qiangungun.monitor.common.exceptions.MonitorException;
import com.qiangungun.monitor.common.model.KeyConstant;
import com.qiangungun.monitor.common.util.CoreObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected String newErrorResult(BizCodeEnum bizCode, String message, Object result) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KeyConstant.KEY_CODE, bizCode.getCode());
        if (StringUtils.isBlank(message)) {
            map.put(KeyConstant.KEY_MSG, bizCode.getMessage());
        } else {
            map.put(KeyConstant.KEY_MSG, message);
        }
        map.put(KeyConstant.KEY_DATA, result);
        return CoreObjectUtils.object2Json(map);
    }

    protected String newErrorResult(BizCodeEnum bizCode, String message) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KeyConstant.KEY_CODE, bizCode.getCode());
        if (StringUtils.isBlank(message)) {
            map.put(KeyConstant.KEY_MSG, bizCode.getMessage());
        } else {
            map.put(KeyConstant.KEY_MSG, message);
        }

        return CoreObjectUtils.object2Json(map);
    }

    protected String newErrorResult(BizCodeEnum bizCode) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KeyConstant.KEY_CODE, bizCode.getCode());
        map.put(KeyConstant.KEY_MSG, bizCode.getMessage());

        return CoreObjectUtils.object2Json(map);
    }

    protected String newSuccessResult(Object result) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KeyConstant.KEY_CODE, BizCodeEnum.SUCCESS.getCode());
        map.put(KeyConstant.KEY_MSG, BizCodeEnum.SUCCESS.getMessage());
        map.put(KeyConstant.KEY_DATA, result);
        return CoreObjectUtils.object2Json(map);
    }

    protected String newSuccessResult() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KeyConstant.KEY_CODE, BizCodeEnum.SUCCESS.getCode());
        map.put(KeyConstant.KEY_MSG, BizCodeEnum.SUCCESS.getMessage());
        return CoreObjectUtils.object2Json(map);
    }

    @ExceptionHandler
    @ResponseBody
    public String dealException(Throwable throwable) {
        logger.error("请求发生异常", throwable);
        if (throwable instanceof MonitorException) {
            MonitorException e = (MonitorException) throwable;
            return newErrorResult(e.getBizCode());
        }
        return newErrorResult(BizCodeEnum.UNKNOWN);
    }

    protected String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        ip = ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
        return ip;
    }

    protected String getAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }
}
