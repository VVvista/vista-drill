package com.vista.design.drill.proxy.staticproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 静态代理
 * 1.抽象类：定义对外的公共方法接口
 * 2.具体类：实现抽象类，并实现方法的具体处理逻辑
 * 3.代理类：实现抽象类，添加具体的使用，实现与抽象类一样的对外接口；客户端直接访问代理类调用具体对象的方法
 * <p>
 * 特点：
 * 代理类和具体类都实现同一个接口或父类，使用时只需要将具体类对象传递给代理角色，代理类调用真实类的方法。
 * 优点：
 * 具体类不对外暴露对象
 * 缺点：
 * 1.代理对象只能代理统一类型的对象，如果要对多个类型的具体类进行代理，必须创建多个对应类型的代理。
 * 2.代理类和具体类必须具有继承相同的接口或父类，实现相同的方法，代码冗余。
 * 3.如果抽象类添加方法，具体类和代理类都需要改动，代码不易维护。
 *
 * @author WenTingTing by 2020/4/13
 */
public class Main {
    public static void main(String[] args) {
        final Station station = new Station();
        final StationProxy stationProxy = new StationProxy(station);
        stationProxy.sell();
    }
}
