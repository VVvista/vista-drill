package com.vista.design.drill.factory.simplefactory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.GFType;

/**
 * 简单工厂模式：
 * 专门定义一个类用来负责创建其他类的对象实例（例如此处的GFFactory）
 * 被创建的对象实例都具有共同的父类（例如此处的BaseGF）
 * <p>
 * 缺点：如果要添加BaseGF的子类，需要修改GFFactory中的createGF方法，比较麻烦
 * <p>
 * https://mp.weixin.qq.com/s/eGd-1gXcE_KY8mZBKd-S5g
 *
 * @author WenTingTing by 2020/4/7
 */
public class Main {
    public static void main(String[] a) {
        BaseGF heiBei = GFFactory.createGF(GFType.HEI_BEI);
        heiBei.eat();
        BaseGF shanDong = GFFactory.createGF(GFType.SHAN_DONG);
        shanDong.eat();
        BaseGF siChuan = GFFactory.createGF(GFType.SI_CHUAN);
        siChuan.eat();
    }
}
