package com.qingyun.springframework.beans.factory.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.DisposableBean;
import com.qingyun.springframework.beans.factory.ObjectFactory;
import com.qingyun.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 单例注册表的实现类
 * @author: 張青云
 * @create: 2021-08-18 17:17
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    //  一级缓存，用来保存单例对象的实例（完整对象）
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    //  二级缓存，用来缓存没有完成属性填充等操作的半成品对象
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    //  三级缓存
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

    //  保存实现了销毁方法的Bean实例
    private final Map<String, Object> disposableBeans = new LinkedHashMap<>();

    //  用来代表null
    protected static final Object NULL_OBJECT = new Object();

    @Override
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            // 判断二级缓存中是否有对象，这个对象就是代理对象，因为只有代理对象才会放到二级缓存中
            if (singletonObject == null && allowEarlyReference) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            singletonObjects.put(beanName, singletonObject);
            earlySingletonObjects.remove(beanName);
            singletonFactories.remove(beanName);
        }
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        String[] disposableBeanNames = keySet.toArray(new String[0]);

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            String beanName = disposableBeanNames[i];
            DisposableBean disposableBean = (DisposableBean) disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on com.qingyun.springframework.aop.test.bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
