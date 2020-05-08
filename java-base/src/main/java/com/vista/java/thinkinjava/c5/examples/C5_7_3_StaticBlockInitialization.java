package com.vista.java.thinkinjava.c5.examples;

/**
 * 5.7.2 静态代码块数据的初始化
 * 静态代码块是用static连接的代码块，初始化规则同静态变量
 * 仅在类首次初始化或首次调用静态数据时加载，且加载一次
 * <p>
 * 注：
 * 1.虽然静态变量和代码块先初始化，但定义顺序决定了初始化顺序：代码块中赋值的变量，变量定义顺序必须优先于代码块
 * 2.静态代码块中不能对非静态变量赋值：若还未创建对象，此时非静态变量还未创建
 *
 * @author WenTingTing by 2020/5/7
 */
public class C5_7_3_StaticBlockInitialization {
    public static void main(String[] args) {
        Cups.cup1.f(99);//Cup(1)  Cup(2)  f(99)
    }

}

class Cup {
    Cup(int marker) {
        System.out.println("Cup(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Cups {

    Cup cup;
    static Cup cup1;// cup1先初始化为：null
    static Cup cup2;

    static {
        //   cup = new Cup(1); // 静态代码块中不能引入非静态变量
        cup1 = new Cup(1);// 根据静态变量的定义顺序，cup1先初始化为null，此处再赋值为 cup对象
        cup2 = new Cup(2);
        System.out.println("cup1(" + cup1 + ")");

    }


    Cups() {
        System.out.println("Cups()");
    }

}
