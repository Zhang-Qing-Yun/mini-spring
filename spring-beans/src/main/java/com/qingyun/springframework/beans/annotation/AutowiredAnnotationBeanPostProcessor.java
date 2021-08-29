package com.qingyun.springframework.beans.annotation;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.BeanFactory;
import com.qingyun.springframework.beans.factory.BeanFactoryAware;
import com.qingyun.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.qingyun.springframework.beans.factory.PropertyValues;
import com.qingyun.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.qingyun.springframework.core.convert.ConversionService;
import com.qingyun.springframework.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * @description： 如果开启了包扫描，则会在包扫描结束后将该类的定义信息添加到BeanFactory中
 * @author: 張青云
 * @create: 2021-08-24 23:42
 **/
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 1. 处理注解 @Value
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                Object value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue((String) value);

                // 类型转换
                Class<?> sourceType = value.getClass();
                Class<?> targetType = (Class<?>) TypeUtil.getType(field);
                ConversionService conversionService = beanFactory.getConversionService();
                if (conversionService != null) {
                    if (conversionService.canConvert(sourceType, targetType)) {
                        value = conversionService.convert(value, targetType);
                    }
                }
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 2. 处理注解 @Autowired
        for (Field field : declaredFields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }

        return pvs;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
