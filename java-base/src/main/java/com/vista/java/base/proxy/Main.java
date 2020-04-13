package com.vista.java.base.proxy;

import com.vista.java.base.proxy.human.Human;
import com.vista.java.base.proxy.human.SuperMan;
import com.vista.java.base.proxy.human.SuperWoman;
import com.vista.java.base.proxy.subject.Station;
import com.vista.java.base.proxy.subject.Subject;

/**
 * 动态代理：
 * 实现动态代理需要解决的问题：
 * 1.如何根据加载到内存中的被代理类，动态创建一个代理类及其对象
 * 2.当通过代理类的对象调用方法时，如何动态的去调用被代理类的同名方法
 *
 * 参考：https://blog.csdn.net/nihaomabmt/article/details/87864675?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2
 * https://blog.csdn.net/yaomingyang/article/details/80981004
 * @author WenTingTing by 2020/4/13
 */
public class Main {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        proxyInstance.eat("山东大煎饼");

        SuperWoman superWoman = new SuperWoman();
        Human proxyInstance1 = (Human) ProxyFactory.getProxyInstance(superWoman);
        proxyInstance1.eat("喝撒！");

        System.out.println(proxyInstance == proxyInstance1);// false

        // 每次创建的代理类对象都不一样
        Human proxyInstance2 = (Human) ProxyFactory.getProxyInstance(superWoman);
        System.out.println(proxyInstance2 == proxyInstance1);// false

        // 创建不同抽象接口的代理类
        Station station = new Station();
        Subject proxyInstance3 = (Subject) ProxyFactory.getProxyInstance(station);
        proxyInstance3.sell();
    }
}
