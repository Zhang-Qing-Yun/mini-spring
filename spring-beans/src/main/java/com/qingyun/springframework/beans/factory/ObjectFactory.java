package com.qingyun.springframework.beans.factory;

import com.qingyun.springframework.beans.BeansException;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-26 20:22
 **/
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
