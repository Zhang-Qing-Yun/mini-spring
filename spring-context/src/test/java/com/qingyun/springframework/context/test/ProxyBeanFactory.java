package com.qingyun.springframework.context.test;

import com.qingyun.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-22 17:43
 **/
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("10001", "青云先生");
                hashMap.put("10002", "張青云");
                hashMap.put("10003", "云鸽");

                return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
            }
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
