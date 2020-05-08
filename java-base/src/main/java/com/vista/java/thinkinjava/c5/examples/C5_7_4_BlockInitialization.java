package com.vista.java.thinkinjava.c5.examples;

/**
 * 5.7.4 非静态代码块的初始化
 * 非静态代码块的定义与静态代码块相同，仅是缺少了static关键字，其初始化与非静态变量相同
 * 1.代码块随着类对象的初始化而初始化
 * 2.代码块中可以对非静态变量和静态变量进行赋值,且每创建一个对象，便进行一次赋值
 *
 * @author WenTingTing by 2020/5/7
 */
public class C5_7_4_BlockInitialization {
    public static void main(String[] args) {
        new Mugs();// Mug(2) Mug(1) Mug(22) Mugs()
        System.out.println();
        new Mugs(1);// Mug(1) Mug(22) Mugs(1)
    }

}

class Mug {
    Mug(int marker) {
        System.out.print("Mug(" + marker + ")");
    }

    void f(int marker) {
        System.out.print("f(" + marker + ")");
    }
}

class Mugs {

    Mug mug1;
    static Mug mug2 = new Mug(2);// 仅在首次调用时进行初始化

    {
        // 可以对静态变量或非静态变量赋值
        mug1 = new Mug(1);
        mug1 = new Mug(22);// 每创建一个对象，便对变量进行一次赋值
        System.out.print("Mug(" + mug1 + ")");
    }

    Mugs() {
        System.out.print("Mugs()");
    }

    Mugs(int marker) {
        System.out.print("Mugs(" + marker + ")");
    }

}