package com.qingyun.springframework.aop.framework.autoproxy;

import com.qingyun.springframework.aop.*;
import com.qingyun.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.qingyun.springframework.aop.framework.ProxyFactory;
import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.BeanFactory;
import com.qingyun.springframework.beans.factory.BeanFactoryAware;
import com.qingyun.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.qingyun.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @description： 判断是否是与切点相匹配的类，如果是则通过代理的方式生成对象
 * @author: 張青云
 * @create: 2021-08-24 00:58
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        //  对于Advice、Advice、Advisor类型，不使用代理的方式来创建对象
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();

        }

        return null;
    }

    /**
     * 判断该类型是否为Advice、Advice、Advisor
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
