package com.qingyun.springframework.beans.factory.config;

/**
 * @description： 单例注册表，管理和维护单例对象
 * @author: 張青云
 * @create: 2021-08-18 17:06
 **/
public interface SingletonBeanRegistry {
    /**
     * 根据提供的beanName获取单例对象
     * @param beanName 要查找的bean的名字
     * @return com.qingyun.springframework.aop.test.bean
     */
    Object getSingleton(String beanName);

    /**
     * 向单例注册表中注册一个bean对象
     * @param beanName 要注册的bean的名字
     * @param singletonObject bean单例对象
     */
    void registerSingleton(String beanName, Object singletonObject);
}
