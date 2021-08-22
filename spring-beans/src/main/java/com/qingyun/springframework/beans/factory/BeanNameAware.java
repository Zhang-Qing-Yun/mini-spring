package com.qingyun.springframework.beans.factory;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-22 12:06
 **/
public interface BeanNameAware extends Aware {

    /**
     * 获取Bean的名字
     * @param name
     */
    void setBeanName(String name);
}
