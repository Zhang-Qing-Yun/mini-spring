package com.qingyun.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @description： 访问者
 * @author: 張青云
 * @create: 2021-08-24 00:48
 **/
public interface Advisor {

    Advice getAdvice();
}
