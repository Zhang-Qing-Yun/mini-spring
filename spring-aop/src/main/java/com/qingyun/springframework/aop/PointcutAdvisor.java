package com.qingyun.springframework.aop;

/**
 * @description： 承担了Pointcut和Advice的组合，Pointcut用于获取JoinPoint，而Advice决定于JoinPoint执行什么操作。
 * @author: 張青云
 * @create: 2021-08-24 00:49
 **/
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
