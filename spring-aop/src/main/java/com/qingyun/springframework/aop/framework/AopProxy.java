package com.qingyun.springframework.aop.framework;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-23 21:41
 **/
public interface AopProxy {

    /**
     * 获取代理类
     * @return 代理类
     */
    Object getProxy();
}
