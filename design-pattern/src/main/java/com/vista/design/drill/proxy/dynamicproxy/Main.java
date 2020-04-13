package com.vista.design.drill.proxy.dynamicproxy;

/**
 * 实现动态代理需要解决的问题：
 * 1.如何根据加载到内存中的被代理类，动态创建一个代理类及其对象
 * 2.当通过代理类的对象调用方法时，如何动态的去调用被代理类的同名方法
 *
 * @author WenTingTing by 2020/4/13
 */
public class Main {
    public static void main(String[] args) {
        Subject station = new Station();
        Subject proxyInstance =(Subject) ProxyFactory.getProxyInstance(station);
        proxyInstance.sell();
    }
}
