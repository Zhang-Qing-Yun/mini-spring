package com.qingyun.springframework.aop.test.threeCache;

import com.qingyun.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-26 20:52
 **/
public class HusbandMother implements FactoryBean<IMother> {

    @Override
    public IMother getObject() throws Exception {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IMother.class}, (proxy, method, args) -> "婚后媳妇妈妈的职责被婆婆代理了！" + method.getName());
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
