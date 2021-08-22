package com.qingyun.springframework.beans.factory;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-22 12:03
 **/
public interface BeanClassLoaderAware extends Aware{

    /**
     * 获取类加载器
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
