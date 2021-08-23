package com.qingyun.springframework.aop;

import java.lang.reflect.Method;

/**
 * @description： 方法匹配器(检查目标方法是否符合通知条件)
 * @author: 張青云
 * @create: 2021-08-23 21:38
 **/
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
