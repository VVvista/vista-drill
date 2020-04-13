package com.vista.java.base.proxy;

import java.lang.reflect.Proxy;

/**
 * 代理工厂类
 * 根据传递的具体类对象obj，创建代理类对象
 * 解决：如果根据加载到内存中的具体类对象，动态创建一个代理类及其对象
 *
 * @author WenTingTing by 2020/4/13
 */
public class ProxyFactory {
    /**
     * 根据具体类对象创建代理类及其对象
     *
     * @param obj 具体类对象
     * @return 代理类对象
     */
    public static Object getProxyInstance(Object obj) {
        MyInvocationHandler handler = new MyInvocationHandler(obj);

        final Object proxyInstance = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
        return proxyInstance;
    }

}
