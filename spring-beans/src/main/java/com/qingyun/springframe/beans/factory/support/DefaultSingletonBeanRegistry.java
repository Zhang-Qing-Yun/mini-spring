package com.qingyun.springframe.beans.factory.support;

import com.qingyun.springframe.beans.BeansException;
import com.qingyun.springframe.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 单例注册表的实现类
 * @author: 張青云
 * @create: 2021-08-18 17:17
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    //  用来保存单例对象的实例
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        //  加锁是因为要保证get和set这两个操作整体的线程安全
        synchronized (singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new BeansException(beanName + "已存在");
            }
            singletonObjects.put(beanName, singletonObject);
        }
    }
}
