package com.qingyun.springframework.util;

/**
 * @description： 解析字符串值的策略模式
 * @author: 張青云
 * @create: 2021-08-24 22:18
 **/
public interface StringValueResolver {
    String resolveStringValue(String strVal);
}
