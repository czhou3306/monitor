/**
 * qiangungun.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiangungun.monitor.agent.model;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 *
 * @author czhou3306@gmail.com
 * @version $Id: FixedHashMap.java, v0.1 2016年11月19日 下午11:23:27 czhou3306@gmail.com Exp $
 */
public class FixedConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

    /**  */
    private static final long serialVersionUID = 9168487066149270631L;

    private int               capacity;

    public FixedConcurrentHashMap(int initialCapacity) {
        super(initialCapacity);
        this.capacity = initialCapacity;
    }

    public synchronized V put(K key, V value) {
        if (super.size() >= capacity) {
            super.remove(super.keySet().toArray()[0]);
            throw new IllegalArgumentException("capacity full,key=" + key);
        }
        return super.put(key, value);
    }

}
