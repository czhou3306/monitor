/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.web.controller.collect;

import java.util.HashMap;
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

import com.qiangungun.monitor.biz.collect.message.split.AccessLogSplitor;
import com.qiangungun.monitor.biz.collect.message.split.RegularSplitor;
import com.qiangungun.monitor.common.exceptions.MonitorException;
import com.qiangungun.monitor.common.model.ViewFieldConstant;
import com.qiangungun.monitor.convert.GatherFileConvert;
import com.qiangungun.monitor.dal.dataobject.ComputeFormulaDO;
import com.qiangungun.monitor.dal.dataobject.GatherFileDO;
import com.qiangungun.monitor.dal.dataobject.MonitorFormulaDO;
import com.qiangungun.monitor.repository.ComputeFormulaRepository;
import com.qiangungun.monitor.repository.GatherFileRepository;
import com.qiangungun.monitor.repository.MonitorFormulaRepository;
import com.qiangungun.monitor.repository.seq.SequenceRepository;
import com.qiangungun.monitor.web.controller.BaseController;
import com.qiangungun.monitor.web.form.ComputeFormulaForm;
import com.qiangungun.monitor.web.form.GatherFileForm;
import com.qiangungun.monitor.web.form.InstanceSplitForm;
import com.qiangungun.monitor.web.form.MonitorFormulaForm;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: GatherConfigManagerController.java, v0.1 2016年11月30日 下午2:09:48 czhou3306@gmail.com Exp $
 */
@Controller
public class GatherConfigManagerController extends BaseController {

    private static final Logger      logger = LoggerFactory
                                                .getLogger(GatherConfigManagerController.class);

    @Resource
    private GatherFileRepository     gatherFileRepository;

    @Resource
    private ComputeFormulaRepository computeFormulaRepository;

    @Resource
    private MonitorFormulaRepository monitorFormulaRepository;

    @Resource
    private SequenceRepository       sequenceRepository;

    @ResponseBody
    @RequestMapping(value = "/manager/addGatherFile.html", method = RequestMethod.POST)
    public String addGatherFile(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody GatherFileForm gatherFileForm) {

        try {
            GatherFileDO gatherFileDO = buildGatherFileDO(gatherFileForm);
            String fileId = sequenceRepository.genConfigId();
            gatherFileDO.setFileId(fileId);
            gatherFileRepository.insert(gatherFileDO);

            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(ViewFieldConstant.FILE_ID, fileId);
            return newSuccessResult(resultMap);
        } catch (MonitorException e) {
            return newErrorResult(e.getBizCode());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manager/updateGatherFile.html", method = RequestMethod.POST)
    public String updateGatherFile(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody GatherFileForm gatherFileForm) {

        try {
            GatherFileDO gatherFileDO = buildGatherFileDO(gatherFileForm);
            gatherFileRepository.update(gatherFileDO);
            return newSuccessResult();
        } catch (MonitorException e) {
            return newErrorResult(e.getBizCode());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manager/addComputeFormula.html", method = RequestMethod.POST)
    public String addComputeFormula(HttpServletRequest request, HttpServletResponse response,
                                    @RequestBody ComputeFormulaForm computeFormulaForm) {

        try {
            ComputeFormulaDO computeFormulaDO = buildComputeFormulaDO(computeFormulaForm);
            String computeId = sequenceRepository.genConfigId();
            computeFormulaDO.setComputeId(computeId);
            computeFormulaRepository.insert(computeFormulaDO);

            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(ViewFieldConstant.COMPUTE_ID, computeId);
            return newSuccessResult(resultMap);
        } catch (MonitorException e) {
            return newErrorResult(e.getBizCode());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manager/updateComputeFormula.html", method = RequestMethod.POST)
    public String updateComputeFormula(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody ComputeFormulaForm computeFormulaForm) {

        try {
            ComputeFormulaDO computeFormulaDO = buildComputeFormulaDO(computeFormulaForm);
            computeFormulaRepository.update(computeFormulaDO);
            return newSuccessResult();
        } catch (MonitorException e) {
            return newErrorResult(e.getBizCode());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manager/addMonitorFormula.html", method = RequestMethod.POST)
    public String addMonitorFormula(HttpServletRequest request, HttpServletResponse response,
                                    @RequestBody MonitorFormulaForm monitorFormulaForm) {
        try {
            MonitorFormulaDO monitorFormulaDO = buildMonitorFormulaDO(monitorFormulaForm);

            String monitorId = sequenceRepository.genConfigId();
            monitorFormulaDO.setMonitorId(monitorId);
            monitorFormulaRepository.insert(monitorFormulaDO);

            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(ViewFieldConstant.MONITOR_ID, monitorId);
            return newSuccessResult(resultMap);
        } catch (MonitorException e) {
            return newErrorResult(e.getBizCode());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manager/updateMonitorFormula.html", method = RequestMethod.POST)
    public String updateMonitorFormula(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody MonitorFormulaForm monitorFormulaForm) {
        try {
            MonitorFormulaDO monitorFormulaDO = buildMonitorFormulaDO(monitorFormulaForm);
            monitorFormulaRepository.update(monitorFormulaDO);

            return newSuccessResult();
        } catch (MonitorException e) {
            return newErrorResult(e.getBizCode());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manager/instanceSplit.html", method = RequestMethod.POST)
    public String instanceSplit(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody InstanceSplitForm instanceSplitForm) {

        String[] resultArray;
        if (instanceSplitForm.getFilePath().contains("access")
            && instanceSplitForm.getFilePath().contains("log")) {
            AccessLogSplitor accessLogSplitor = new AccessLogSplitor();
            resultArray = accessLogSplitor.split(instanceSplitForm.getInstance(),
                GatherFileConvert.getSplitChars(instanceSplitForm.getRegex()));
        } else {
            RegularSplitor regularSplitor = new RegularSplitor();
            resultArray = regularSplitor.split(instanceSplitForm.getInstance(),
                GatherFileConvert.getSplitChars(instanceSplitForm.getRegex()));
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ViewFieldConstant.SPLIT_RESULT, resultArray);
        return newSuccessResult(resultMap);
    }

    /**
     * 
     * @param gatherFileForm
     * @return
     */
    private GatherFileDO buildGatherFileDO(GatherFileForm gatherFileForm) {
        GatherFileDO gatherFileDO = new GatherFileDO();
        gatherFileDO.setFilePath(gatherFileForm.getFilePath());
        gatherFileDO.setGatherType(gatherFileForm.getGatherType());
        gatherFileDO.setGroupFieldPosition(gatherFileForm.getGroupFieldPosition());
        gatherFileDO.setInstance(gatherFileForm.getInstance());
        gatherFileDO.setSplitRegex(gatherFileForm.getSplitRegex());
        gatherFileDO.setSystemName(gatherFileForm.getSystemName());
        gatherFileDO.setFileId(gatherFileForm.getFileId());
        return gatherFileDO;
    }

    /**
     * 
     * @param computeFormulaForm
     * @return
     */
    private ComputeFormulaDO buildComputeFormulaDO(ComputeFormulaForm computeFormulaForm) {
        ComputeFormulaDO computeFormulaDO = new ComputeFormulaDO();
        computeFormulaDO.setFieldPosition(computeFormulaForm.getFieldPosition());
        computeFormulaDO.setFileId(computeFormulaForm.getFileId());
        computeFormulaDO.setFilterCondition(computeFormulaForm.getFilterCondition());
        computeFormulaDO.setFormulaType(computeFormulaForm.getFormulaType());
        computeFormulaDO.setComputeName(computeFormulaForm.getComputeName());
        computeFormulaDO.setComputeId(computeFormulaForm.getComputeId());
        return computeFormulaDO;
    }

    /**
     * 
     * @param monitorFormulaForm
     * @return
     */
    private MonitorFormulaDO buildMonitorFormulaDO(MonitorFormulaForm monitorFormulaForm) {
        MonitorFormulaDO monitorFormulaDO = new MonitorFormulaDO();
        monitorFormulaDO.setFileId(monitorFormulaForm.getFileId());
        monitorFormulaDO.setFormula(monitorFormulaForm.getFormula());

        monitorFormulaDO.setMonitorName(monitorFormulaForm.getMonitorName());
        monitorFormulaDO.setShowField(monitorFormulaForm.getShowField());
        monitorFormulaDO.setMonitorId(monitorFormulaForm.getMonitorId());
        return monitorFormulaDO;
    }
}
