/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.controller.collect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiangungun.monitor.biz.collect.message.split.RegularSplitor;
import com.qiangungun.monitor.convert.GatherFileConvert;
import com.qiangungun.monitor.web.controller.BaseController;
import com.qiangungun.monitor.web.form.SplitForm;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: SplitorController.java, v0.1 2016年11月30日 下午1:53:59 czhou3306@gmail.com Exp $
 */
@Controller
public class SplitorController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "/splitor/split.html", method = RequestMethod.POST)
    public String split(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody SplitForm splitForm) throws Exception {
        RegularSplitor splitor = new RegularSplitor();

        String[] array = splitor.split(splitForm.getInstance(),
            GatherFileConvert.getSplitChars(splitForm.getRegex()));

        return newSuccessResult(array);
    }
}
