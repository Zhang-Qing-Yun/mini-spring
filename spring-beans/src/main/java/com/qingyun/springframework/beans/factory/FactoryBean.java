package com.qingyun.springframework.beans.factory;

/**
 * @description： 一个能生产或修饰对象生成的工厂Bean，可以按照用户希望的方式来生成Bean，并且交给Spring管理
 *                  实现这个接口的FactoryBean实质上也是一个Bean，所有的Bean都是由BeanFactory来管理的，这个也不例外
 *                  但是这个Bean特殊的一点是：它被BeanFactory创建后，我们就可以通过它来按照自己的意愿创建对象，为IOC提供了灵活的对象创建方式
 * @author: 張青云
 * @create: 2021-08-22 16:21
 **/
public interface FactoryBean<T> {

    /**
     * 获取对象
     */
    T getObject() throws Exception;

    /**
     * 获取对象的类型
     * @return 对象类型
     */
    Class<?> getObjectType();

    /**
     * 是否是单例的，单例对象会被放到内存中
     */
    boolean isSingleton();
}
