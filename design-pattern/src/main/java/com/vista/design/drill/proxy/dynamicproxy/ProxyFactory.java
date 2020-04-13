package com.vista.design.drill.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * 代理类创建工厂
 *
 * @author WenTingTing by 2020/4/13
 */
public class ProxyFactory {
    public static Object getProxyInstance(Object obj) {
        StationProxy proxy = new StationProxy(obj);
        Object proxyInstance = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), proxy);

        return proxyInstance;
    }
}
