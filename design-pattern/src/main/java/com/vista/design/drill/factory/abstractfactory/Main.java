package com.vista.design.drill.factory.abstractfactory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.abstractfactory.factory.HeBeiGFFactory;
import com.vista.design.drill.factory.abstractfactory.factory.ShanDongGFFactory;
import com.vista.design.drill.factory.abstractfactory.factory.SiChuanGFFactory;
import com.vista.design.drill.factory.abstractfactory.father.IGFFather;

/**
 * 抽象工厂模式
 * 此方式是在工厂方法模式的基础上进行改造的：
 * 1.提供一个创建一系列相关或相互依赖对象的接口（例如此处：IGFFactory）
 * 2.让子类决定实例化哪些类并创建对应的实例化方法（例如此处：HeBeiGFFactory...）
 * <p>
 * https://mp.weixin.qq.com/s/eGd-1gXcE_KY8mZBKd-S5g
 *
 * @author WenTingTing by 2020/4/7
 */
public class Main {
    public static void main(String[] a) {
        HeBeiGFFactory heBeiGFFactory = new HeBeiGFFactory();
        BaseGF heBeiGF = heBeiGFFactory.createGF();
        IGFFather heBeiGFFather = heBeiGFFactory.createGFFather();
        heBeiGF.eat();
        heBeiGFFather.suggestionToGirl();

        ShanDongGFFactory shanDongGFFactory = new ShanDongGFFactory();
        BaseGF shanDongGF = shanDongGFFactory.createGF();
        IGFFather shanDongGFFather = shanDongGFFactory.createGFFather();
        shanDongGF.eat();
        shanDongGFFather.suggestionToGirl();
    }
}
