/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.repository.cache;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: CacheRefresh.java, v0.1 2016年12月3日 下午10:28:24 czhou3306@gmail.com Exp $
 */
@Service("cacheRefresh")
public class CacheRefresh implements InitializingBean {

    @Resource
    private GatherFileCache     gatherFileCache;

    @Resource
    private MonitorFormulaCache monitorFormulaCache;

    @Resource
    private ComputeFormulaCache computeFormulaCache;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        gatherFileCache.init();
        computeFormulaCache.init();
        monitorFormulaCache.init();

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                gatherFileCache.refresh();
                computeFormulaCache.refresh();
                monitorFormulaCache.refresh();
            }
        }, 5 * 60 * 1000, 5 * 60 * 1000);
    }

}
