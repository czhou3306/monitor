/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.controller.collect;

import com.qiangungun.monitor.biz.collect.query.MonitorDataQueryManager;
import com.qiangungun.monitor.common.model.LineComputeData;
import com.qiangungun.monitor.common.util.CoreDateUtils;
import com.qiangungun.monitor.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: DapanController.java, v0.1 2016年12月19日 下午5:36:06 czhou3306@gmail.com Exp $
 */
@Controller
public class DapanController extends BaseController {

    @Resource
    private MonitorDataQueryManager monitorDataQueryManager;

    @ResponseBody
    @RequestMapping(value = "/dapan.html", method = RequestMethod.GET)
    public String dapan(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam String beginDate, @RequestParam String endDate)
                                                                                     throws Exception {
        String fileId = "1864";

        List<LineComputeData> dataList = monitorDataQueryManager.query(fileId,
            CoreDateUtils.yyyymmddhh24miss(beginDate), CoreDateUtils.yyyymmddhh24miss(endDate));

        Map<String, LineComputeData> loginMap = new TreeMap<String, LineComputeData>();
        Map<String, LineComputeData> orderMap = new TreeMap<String, LineComputeData>();
        Map<String, LineComputeData> payMap = new TreeMap<String, LineComputeData>();
        Map<String, LineComputeData> depositMap = new TreeMap<String, LineComputeData>();

        for (LineComputeData data : dataList) {
            String url = data.getFieldValue().get(new Integer(7));

            if (StringUtils.equals(url, "/v1/user/gesture/login")
                || StringUtils.equals(url, "/v1/user/webAppLogin")
                || StringUtils.equals(url, "/v1/user/login")) {
                compute(loginMap, data);
            } else if (StringUtils.equals(url, "/v1/funds/topup")) {
                compute(depositMap, data);
            } else {
                if (StringUtils.equals(url, "/v1/funds/order")
                    || StringUtils.equals(url, "/v1/funds/buy")
                    || StringUtils.equals(url, "/v1/funds/purchase")) {
                    compute(orderMap, data);
                }
                if (StringUtils.equals(url, "/v1/funds/buy")
                    || StringUtils.equals(url, "/v1/funds/purchase")
                    || StringUtils.equals(url, "/v1/funds/bindCardPay")
                    || StringUtils.equals(url, "/v1/funds/finishPay")) {
                    compute(payMap, data);
                }
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("login", loginMap.values());
        result.put("order", orderMap.values());
        result.put("pay", payMap.values());
        result.put("deposit", depositMap.values());

        return newSuccessResult(result);
    }

    /**
     * 
     * @param dataMap
     * @param data
     */
    private void compute(Map<String, LineComputeData> dataMap, LineComputeData data) {
        LineComputeData exist = dataMap.get(data.getDate());
        if (exist == null) {
            dataMap.put(data.getDate(), data);
        } else {
            Set<String> keySet = exist.getComputeMap().keySet();
            for (String computeKey : keySet) {
                BigDecimal nowData = exist.getComputeMap().get(computeKey);
                BigDecimal newData = data.getComputeMap().get(computeKey);
                BigDecimal sumData = nowData.add(newData);
                exist.getComputeMap().put(computeKey, sumData);
            }
        }
    }
}
