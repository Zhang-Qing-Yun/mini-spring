package com.qingyun.springframework.beans.factory.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.DisposableBean;
import com.qingyun.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 单例注册表的实现类
 * @author: 張青云
 * @create: 2021-08-18 17:17
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    //  用来保存单例对象的实例
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    //  保存实现了销毁方法的Bean实例
    private final Map<String, Object> disposableBeans = new LinkedHashMap<>();

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

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        String[] disposableBeanNames = keySet.toArray(new String[0]);

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            String beanName = disposableBeanNames[i];
            DisposableBean disposableBean = (DisposableBean) disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
