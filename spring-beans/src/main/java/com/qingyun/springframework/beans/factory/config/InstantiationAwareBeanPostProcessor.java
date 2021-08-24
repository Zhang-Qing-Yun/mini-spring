package com.qingyun.springframework.beans.factory.config;

import com.qingyun.springframework.beans.BeansException;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 01:01
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
