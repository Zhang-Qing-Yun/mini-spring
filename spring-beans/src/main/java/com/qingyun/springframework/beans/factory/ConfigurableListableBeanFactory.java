package com.qingyun.springframework.beans.factory;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-20 11:36
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 获取bean的定义信息
     * @param beanName bean的名字
     * @return bean的定义信息
     * @throws BeansException 获取失败时抛出异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化单例Bean对象
     */
    void preInstantiateSingletons() throws BeansException;
}
