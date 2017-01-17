/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.common.util;


import org.apache.commons.collections.MapUtils;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author czhou3306@gmail.com
 * @version $Id: LockUtil.java, v0.1 2016年11月23日 下午6:56:58 czhou3306@gmail.com Exp $
 */
public class LockUtil {

    private static ConcurrentHashMap<String, InnerLock> lockMap = new ConcurrentHashMap<>();

    static {
        //NOTE:定时处理过期的锁
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (MapUtils.isEmpty(lockMap)) {
                    return;
                }

                for (Map.Entry<String, InnerLock> entry : lockMap.entrySet()) {
                    final InnerLock lock = entry.getValue();
                    if (!lock.isExpire()) {
                        continue;
                    }

                    if (lock.isLocked()) {
                        Thread owner = lock.getOwner();
                        owner.interrupt();
                    }

                    lockMap.remove(entry.getKey());
                }
            }
        }, 0, 5000);
    }

    private static class InnerLock extends ReentrantLock {
        private Date createDate = null;
        private int expireTime;

        private InnerLock() {
            super(true);
            this.createDate = new Date();
            this.expireTime = 5;
        }

        @Override
        public Thread getOwner() {
            return super.getOwner();
        }

        public boolean isExpire() {
            return CoreDateUtils.addSeconds(createDate, expireTime).after(new Date());
        }

        public void clear() {
            this.createDate = null;
            this.expireTime = 0;
        }
    }

    public static Lock getLock(String key) {
        if (!lockMap.containsKey(key)) {
            lockMap.putIfAbsent(key, new InnerLock());
        }

        return lockMap.get(key);
    }
}
