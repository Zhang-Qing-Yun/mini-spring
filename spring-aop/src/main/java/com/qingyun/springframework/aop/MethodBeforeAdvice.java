package com.qingyun.springframework.aop;

import java.lang.reflect.Method;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 00:46
 **/
public interface MethodBeforeAdvice extends BeforeAdvice {
    /**
     * 一个方法被调用前的回调方法
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
