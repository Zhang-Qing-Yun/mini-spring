package com.qingyun.springframework.beans.factory.config;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.BeanFactory;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-20 11:29
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization方法
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization方法
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
