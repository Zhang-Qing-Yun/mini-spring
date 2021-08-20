package com.qingyun.springframework.beans.factory.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.core.io.Resource;
import com.qingyun.springframework.core.io.ResourceLoader;

/**
 * @description： 从bean定义的资源resource中解析bean的定义并且将其注册
 * @author: 張青云
 * @create: 2021-08-20 10:29
 **/
public interface BeanDefinitionReader {

    /**
     * 获取实例注册表
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     */
    ResourceLoader getResourceLoader();

    /**
     * 从resource中解析并创建bean的定义信息，并且将其注册到注册表中
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
