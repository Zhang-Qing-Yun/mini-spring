package com.qingyun.springframework.beans.factory.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.FactoryBean;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.factory.config.BeanPostProcessor;
import com.qingyun.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.qingyun.springframework.core.convert.ConversionService;
import com.qingyun.springframework.util.ClassUtils;
import com.qingyun.springframework.util.StringValueResolver;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description： BeanFactory的抽象实现类，实现了获取bean的逻辑过程
 * @author: 張青云
 * @create: 2021-08-18 18:09
 **/
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
    //  用来存储BeanPostProcessor
    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    //  用来存储字符串解析器
    private final List<StringValueResolver> embeddedValueResolvers = new CopyOnWriteArrayList<>();

    //  类加载器
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    //  类型转换器
    private ConversionService conversionService;

    @Override
    public Object getBean(String name) throws BeansException {  // 此处应用模板方法模式
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public boolean containsBean(String name) {
        return containsBeanDefinition(name);
    }

    protected abstract boolean containsBeanDefinition(String beanName);

    protected Object doGetBean(final String name, final Object[] args) {
        //  先去查单例注册表
        Object singleton = getSingleton(name);
        if (singleton != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            //  对于FactoryBean来说，第一次是将其当作Bean来实例化然后放到了单例注册表中，此后的获取都是将其当作工厂
            return getObjectForBeanInstance(singleton, name);
        }

        //  当单例注册表中不存在单例对象时，需要去获取bean的定义信息并且去创建bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        //  FactoryBean是否生产过对象
        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    /**
     * 获取bean的定义信息
     * @param beanName bean的名字
     * @return 定义信息
     * @throws BeansException 获取出错时的异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 根据定义信息去创建bean对象
     * @param beanName bean的名字
     * @param beanDefinition bean的定义信息
     * @param args 创建bean对象时的构造器参数
     * @return bean对象
     * @throws BeansException 出错时的异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
