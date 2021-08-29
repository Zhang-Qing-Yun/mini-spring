package com.qingyun.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.DisposableBean;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @description： 适配器类，Bean对象的各种销毁方法都在这里对Bean做统一的销毁
 * @author: 張青云
 * @create: 2021-08-21 23:16
 **/
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 2. 注解配置 destroy-method {判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destroyMethodName) &&
                !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on com.qingyun.springframework.aop.test.bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }

}
