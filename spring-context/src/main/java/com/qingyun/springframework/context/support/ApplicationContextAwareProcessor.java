package com.qingyun.springframework.context.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.config.BeanPostProcessor;
import com.qingyun.springframework.context.ApplicationContext;
import com.qingyun.springframework.context.ApplicationContextAware;

/**
 * @description：    因为无法直接获取ApplicationContext，所以需要使用一个包装类来继承BeanPostProcessor，
 *                  从而在增强器处获取因为无法直接获取ApplicationContext
 * @author: 張青云
 * @create: 2021-08-22 12:08
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
