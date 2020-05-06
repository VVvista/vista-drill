package com.vista.java.thinkinjava.c5.examples;

/**
 * 5.7.2 静态数据的初始化
 * 该部分先仅静态变量的初始化
 * 注：
 * 1.静态变量的作用域是类，不存在局部局部静态变量的概念
 * 2.静态变量仅在类第一次初始化或第一次调用静态变量(或方法)时进行初始化，创建再多的对象也仅在初次时初始化
 * 3.静态变量的初始化优先于非静态的全局变量
 * <p>
 * 特殊说明：
 * 1.首次调用静态变量或方法时，静态变量进行初始化，非静态变量仅在对象初始化时进行初始化
 * 2.静态变量可以看成特殊的全局变量，仅初始化一次
 *
 * @author WenTingTing by 2020/5/6
 */
public class C5_7_2_StaticInitialization {
    public static void main(String[] args) {
   /*     Bowl bowl4 = Cupboard.bowl4;
        System.out.println();
        new Cupboard();*/
        System.out.println("Creating new Cupboard() in main ");
        new Cupboard();
        System.out.println("Creating new Cupboard() in main ");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
        /*
        输出结果：
        Bowl(1)
        Bowl(2)
        Table()
        Bowl(4)
        Bowl(5)
        Bowl(3)
        Cupboard()
        f1(2)
        Creating new Cupboard() in main
        Bowl(3)
        Cupboard()
        f1(2)
        Creating new Cupboard() in main
        Bowl(3)
        Cupboard()
        f1(2)
        f2(1)
        f3(1)
         */

    }

    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();
}


class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");
    }

    void f1(int marker) {
        System.out.println("f1(" + marker + ")");
    }
}

class Table {
    static Bowl bowl1 = new Bowl(1);

    Table() {
        System.out.println("Table()");
    }

    void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }

    static Bowl bowl2 = new Bowl(2);
}

class Cupboard {
    Bowl bowl3 = new Bowl(3);
    static Bowl bowl4 = new Bowl(4);

    Cupboard() {
        System.out.println("Cupboard()");
        bowl4.f1(2);
    }

    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }

    static void f(int marker) {
        System.out.println(" static f(" + marker + ")");
    }

    static Bowl bowl5 = new Bowl(5);
}