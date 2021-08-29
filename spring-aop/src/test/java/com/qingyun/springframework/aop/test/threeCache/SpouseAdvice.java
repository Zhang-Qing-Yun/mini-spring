package com.qingyun.springframework.aop.test.threeCache;

import com.qingyun.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-26 20:53
 **/
public class SpouseAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口(切面)：" + method);
    }

}
