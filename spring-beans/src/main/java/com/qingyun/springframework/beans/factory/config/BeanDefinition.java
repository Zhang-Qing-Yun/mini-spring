package com.qingyun.springframework.beans.factory.config;

import com.qingyun.springframework.beans.factory.PropertyValues;

/**
 * @description： Bean的定义信息，对应于spring源码中解析xml文件后获取到的Bean定义信息
 * @author: 張青云
 * @create: 2021-08-18 16:52
 **/
public class BeanDefinition {
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    //  bean的类型
    private Class beanClass;

    //  bean的属性信息
    private PropertyValues propertyValues;

    //  初始化方法的名字
    private String initMethodName;

    //  销毁方法的名字
    private String destroyMethodName;

    //  单例还是多例(原型)
    //  单例模式和原型模式的区别就在于是否存放到内存中，如果是原型模式那么就不会存放到内存中缓存，每次获取都需要重新创建对象，
    //  另外非 Singleton 类型的 Bean 不需要执行销毁方法
    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }
}
