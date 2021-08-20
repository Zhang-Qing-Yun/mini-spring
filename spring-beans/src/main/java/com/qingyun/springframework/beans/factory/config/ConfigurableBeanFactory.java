package com.qingyun.springframework.beans.factory.config;

import com.qingyun.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-20 11:27
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

}
