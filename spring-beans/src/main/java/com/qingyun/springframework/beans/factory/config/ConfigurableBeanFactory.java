package com.qingyun.springframework.beans.factory.config;

import com.qingyun.springframework.beans.factory.HierarchicalBeanFactory;
import com.qingyun.springframework.util.StringValueResolver;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-20 11:27
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加BeanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    /**
     * 增加一个StringValueResolver
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * 解决一个EmbeddedValue
     */
    String resolveEmbeddedValue(String value);

}
