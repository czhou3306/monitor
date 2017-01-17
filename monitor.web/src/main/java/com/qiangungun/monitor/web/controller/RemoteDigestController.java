/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiangungun.monitor.web.controller;

import com.qiangungun.monitor.common.util.CoreDateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author czhou3306@gmail.com
 * @version $Id: WeixinController.java, v0.1 2015年11月18日 上午9:23:47 czhou3306@gmail.com Exp $
 */
@Controller
public class RemoteDigestController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RemoteDigestController.class);

    class Record {
        public String date;
        public String service;
        public int    sumRt;
        public int    count;
    }

    @ResponseBody
    @RequestMapping(value = "/remotedigest/query.html", method = RequestMethod.GET)
    public String authevent(HttpServletRequest request, HttpServletResponse response)
                                                                                     throws Exception {

        String yyyymmdd = CoreDateUtils.yyyymmdd(new Date());
        File dir = new File("/opt/logs/data/" + "20161126/mobile.1");
        ExecutorService es = Executors.newFixedThreadPool(5);

        List<Future<Map<String, Record>>> fl = new ArrayList<Future<Map<String, Record>>>();

        for (final File file : dir.listFiles()) {
            Future<Map<String, Record>> f = es.submit(new Callable<Map<String, Record>>() {

                @Override
                public Map<String, Record> call() throws Exception {

                    Map<String, Record> result = new HashMap<String, Record>();
                    RandomAccessFile raf = new RandomAccessFile(file, "rw");
                    String line = null;
                    while ((line = raf.readLine()) != null) {
                        try{
                          
                        String[] array = line.split("|");
                        Record r = new Record();
                        r.date = array[0];
                        r.count = Integer.parseInt(array[3].split(":")[1]);
                        r.service = array[1];
                        r.sumRt = Integer.parseInt(array[4].split(":")[1]);

                        Record ex = result.get(r.date + r.service);
                        if (ex != null) {
                            ex.count = ex.count + r.count;
                            ex.sumRt = ex.sumRt + r.sumRt;
                        } else {
                            result.put(r.date + r.service, r);
                        }}catch(Exception e){
                            logger.error("line="+line,e);
                        }
                    }
                    raf.close();
                    return result;
                }
            });

            fl.add(f);
        }

        Map<String, Record> finResult = new HashMap<String, RemoteDigestController.Record>();
        for (Future<Map<String, Record>> f : fl) {
            Map<String, Record> result = f.get();

            Set<String> keys = result.keySet();
            for (String key : keys) {
                Record r = result.get(key);
                Record exist = finResult.get(key);
                if (exist != null) {
                    exist.count = exist.count + r.count;
                    exist.sumRt = exist.sumRt + exist.sumRt;
                } else {
                    finResult.put(key, r);
                }
            }
        }

        return newSuccessResult(finResult);
    }
}
