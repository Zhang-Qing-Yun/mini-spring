package com.qingyun.springframework.context;

import com.qingyun.springframework.beans.factory.HierarchicalBeanFactory;
import com.qingyun.springframework.beans.factory.ListableBeanFactory;
import com.qingyun.springframework.core.io.ResourceLoader;

/**
 * @description： 应用上下文
 * @author: 張青云
 * @create: 2021-08-21 17:14
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory,
        ResourceLoader, ApplicationEventPublisher  {
}
