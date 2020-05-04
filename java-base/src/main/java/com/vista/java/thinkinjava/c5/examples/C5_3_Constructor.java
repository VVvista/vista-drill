package com.vista.java.thinkinjava.c5.examples;

/**
 * 5.3 默认构造器
 * 重点：
 * 1.当类中没有定义构造函数时，默认编译器自行创建了无参构造器
 * 2.如果类中定义了构造函数（不管无参或有参），只能调用自定义的函数，编译器不能自创建无参构造器
 *
 * @author WenTingTing by 2020/5/4
 */
public class C5_3_Constructor {
    public static void main(String[] args) {
        new Bird();// 调用成功
        new Bird2(1);
        new Bird2(1.0);
        // new Bird2(); 调用失败，编译器报错：cannot resolve constructor
    }
}

/**
 * 编译器默认创建了无参构造器
 */
class Bird {

}

/**
 * 已自定义的构造函数为准，编译器不再自行创建
 */
class Bird2 {
    Bird2(int i) {
    }

    Bird2(double d) {
    }
}