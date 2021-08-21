package com.qingyun.springframework.context.test;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-21 21:16
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("北京(改)");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
