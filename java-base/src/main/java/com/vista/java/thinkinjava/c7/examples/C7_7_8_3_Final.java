package com.vista.java.thinkinjava.c7.examples;

/**
 * final方法-普通方法
 * final修饰方法：方法锁定，任何子类不能重写该方法；
 * 子类仍然继承该方法，对外也能调用 子类.method()
 * 子类可以重载该方法，但不能重写该方法
 *
 * @author Wen TingTing by 2020/5/8
 */
public class C7_7_8_3_Final {
    public static void main(String[] args) {
        Final aFinal = new Final();
        aFinal.f();
        aFinal.f(1);
    }
}

class WithFinal {
    final void f() {
        System.out.println("withFinal.f");
    }
}

class Final extends WithFinal {
   /* void f() {
        System.out.println("Final.f" );
    }*/

    void f(int i) {
        System.out.println("Final.f" + i);
    }
}
