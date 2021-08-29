package com.qingyun.springframework.aop;

import com.qingyun.springframework.util.ClassUtils;

/**
 * @description： 被代理的目标对象
 * @author: 張青云
 * @create: 2021-08-23 21:49
 **/
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 获取目标类的类型
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    /**
     * 获取目标类
     */
    public Object getTarget(){
        return this.target;
    }
}
