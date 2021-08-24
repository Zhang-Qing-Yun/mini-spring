package com.qingyun.springframework.aop.framework;

import com.qingyun.springframework.aop.AdvisedSupport;

/**
 * @description： 代理工厂，解决的是关于 JDK 和 Cglib 两种代理的选择问题
 * @author: 張青云
 * @create: 2021-08-24 00:54
 **/
public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new CglibAopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}
