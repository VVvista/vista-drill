package com.vista.design.drill.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**代理类
 * @author WenTingTing by 2020/4/13
 */
public class StationProxy implements InvocationHandler {
    private Object object;

    public StationProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法调用前-处理逻辑");
        method.invoke(object, args);
        System.out.println("方法调用后-处理逻辑");


        return null;
    }
}
