/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.controller.collect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiangungun.monitor.common.exceptions.MonitorException;
import com.qiangungun.monitor.common.model.ViewFieldConstant;
import com.qiangungun.monitor.dal.daointerface.ComputeFormulaDAO;
import com.qiangungun.monitor.dal.daointerface.GatherFileDAO;
import com.qiangungun.monitor.dal.daointerface.MonitorFormulaDAO;
import com.qiangungun.monitor.dal.dataobject.ComputeFormulaDO;
import com.qiangungun.monitor.dal.dataobject.GatherFileDO;
import com.qiangungun.monitor.dal.dataobject.MonitorFormulaDO;
import com.qiangungun.monitor.web.controller.BaseController;
import com.qiangungun.monitor.web.form.ComputeFormulaForm;
import com.qiangungun.monitor.web.form.GatherFileForm;
import com.qiangungun.monitor.web.form.MonitorFormulaForm;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherConfigManagerController.java, v0.1 2016年11月30日 下午2:09:48 czhou3306@gmail.com Exp $
 */
@Controller
public class GatherConfigQueryController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(GatherConfigQueryController.class);

    @Resource
    private GatherFileDAO       gatherFileDAO;

    @Resource
    private ComputeFormulaDAO   computeFormulaDAO;

    @Resource
    private MonitorFormulaDAO   monitorFormulaDAO;

    @ResponseBody
    @RequestMapping(value = "/manager/queryGatherFile.html", method = RequestMethod.GET)
    public String queryGatherFile(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<GatherFileDO> list = gatherFileDAO.getAll();
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(ViewFieldConstant.GATHER_FILE_LIST, list);
            return newSuccessResult(resultMap);
        } catch (MonitorException e) {
            logger.error("查询异常",e);
            return newErrorResult(e.getBizCode());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manager/queryComputeFormula.html", method = RequestMethod.GET)
    public String queryComputeFormula(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<ComputeFormulaDO> list = computeFormulaDAO.getAll();
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(ViewFieldConstant.COMPUTE_FORMULA_LIST, list);
            return newSuccessResult(resultMap);
        } catch (MonitorException e) {
            logger.error("查询异常",e);
            return newErrorResult(e.getBizCode());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manager/queryMonitorFormula.html", method = RequestMethod.GET)
    public String queryMonitorFormula(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<MonitorFormulaDO> list = monitorFormulaDAO.getAll();
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(ViewFieldConstant.MONITOR_FORMULA_LIST, list);
            return newSuccessResult(resultMap);
        } catch (MonitorException e) {
            logger.error("查询异常",e);
            return newErrorResult(e.getBizCode());
        }
    }

}
