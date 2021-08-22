package com.qingyun.springframework.beans.factory.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description： 处理关于 FactoryBean 此类对象的注册操作
 * @author: 張青云
 * @create: 2021-08-22 17:29
 **/
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    //  保存FactoryBean类型的对象实例
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();


    /**
     * 通过FactoryBean来获取Bean对象
     */
    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    /**
     * 获取已缓存的FactoryBean对象实例
     * @param beanName bean的名字
     * @return FactoryBean对象实例
     */
    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    /**
     * 获取或创建FactoryBean对象实例
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }

}
