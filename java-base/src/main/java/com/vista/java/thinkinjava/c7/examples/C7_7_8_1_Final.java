package com.vista.java.thinkinjava.c7.examples;

/**
 * 空白final
 * Java允许生成空白null：即未赋值的final变量
 * 注意：与普通变量不同
 * 1.final全局变量，类对象初始化时不会对final变量赋予初始值，
 * 必须在构造器中赋予其初始化，从而提现不同对象的final值不同
 * 2.final变量在未赋值之前不能进行任何的调用，即被调用前必须被初始化
 * <p>
 * 重点：
 * final全局变量的初始化：定义时、每个构造器中
 * 未初始化值前不能被调用
 *
 * @author Wen TingTing by 2020/5/8
 */
public class C7_7_8_1_Final {
    public static void main(String[] args) {
        new BlankFinal();
        new BlankFinal(22);
    }
}

class Poppet {
    private int i;

    Poppet(int i) {
        this.i = i;
    }
}

class BlankFinal {
    private final int i = 0;
    private final int j;
    private final Poppet p;

    BlankFinal() {
        // System.out.println("j=" + j); final变量没有初始化之前，编译器不会赋默认值
        j = 1;
        p = new Poppet(1);
        System.out.println("j=" + j);
    }


    BlankFinal(int i) {
        j = i;
        p = new Poppet(i);
    }
}