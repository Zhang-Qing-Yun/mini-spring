package com.qingyun.springframework.aop;


/**
 * @description： 切点，由ClassFilter和MethodMatcher组成
 * @author: 張青云
 * @create: 2021-08-23 21:37
 **/
public interface Pointcut {
    /**
     * 获取类过滤器
     */
    ClassFilter getClassFilter();

    /**
     * 获取方法匹配器
     */
    MethodMatcher getMethodMatcher();
}
