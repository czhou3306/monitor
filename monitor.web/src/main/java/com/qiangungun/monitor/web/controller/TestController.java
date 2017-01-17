/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author czhou3306@gmail.com
 * @version $Id: WeixinController.java, v0.1 2015年11月18日 上午9:23:47 czhou3306@gmail.com Exp $
 */
@Controller
public class TestController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "/test.html", method = RequestMethod.GET)
    public String authevent(HttpServletRequest request, HttpServletResponse response)
                                                                                     throws Exception {
        
        return "helloworld";
    }

}
