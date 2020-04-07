package com.vista.design.drill.factory.factory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.factory.factory.HeBeiGFFactory;
import com.vista.design.drill.factory.factory.factory.ShanDongGFFactory;
import com.vista.design.drill.factory.factory.factory.SiChuanGFFactory;

/**工厂设计模式
 * 此方式是在简单工厂设计模式的基础上进行改造的：
 * 1.定义一个用于创建对象的接口（例如此处：IGFFactory）
 * 2.让子类决定实例化哪一个类，工厂方法使一个类的实例化延迟到其子类中（例如此处：HeBeiGFFactory...）
 *
 * 特点：
 * 新建实例类时，需要同时新建创建实例的工厂子类。从而使得新建实例时无需改动现有代码
 *
 *https://mp.weixin.qq.com/s/eGd-1gXcE_KY8mZBKd-S5g
 * @author WenTingTing by 2020/4/7
 */
public class Main {
    public static void main(String[] a) {
        BaseGF heiBei = new HeBeiGFFactory().createGF();
        heiBei.eat();
        BaseGF shanDong = new ShanDongGFFactory().createGF();
        shanDong.eat();
        BaseGF siChuan = new SiChuanGFFactory().createGF();
        siChuan.eat();
    }
}
