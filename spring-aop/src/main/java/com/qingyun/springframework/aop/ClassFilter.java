package com.qingyun.springframework.aop;

/**
 * @description： 类过滤器，只有在与指定的targetClass匹配时才能进行增强
 * @author: 張青云
 * @create: 2021-08-23 21:38
 **/
public interface ClassFilter {
    /**
     * 当前类过滤器是否与指定的类型匹配
     */
    boolean matches(Class<?> clazz);
}
