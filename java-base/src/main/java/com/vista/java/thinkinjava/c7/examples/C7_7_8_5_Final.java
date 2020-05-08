package com.vista.java.thinkinjava.c7.examples;

/**
 * final类
 * final修饰类：仅表明该类不能被任何子类继承，其他性质与普通类相同
 * 同时final类中的方法都隐式的为final型，即不能被任何子类修改
 * 但final中的属性仍然可以修改。
 *
 * @author Wen TingTing by 2020/5/8
 */
public class C7_7_8_5_Final {
    public static void main(String[] args) {
        Dinosaur dinosaur = new Dinosaur();
        dinosaur.f();
        dinosaur.i = 40;
        dinosaur.j++;
        System.out.println(dinosaur.i);
        System.out.println(dinosaur.j);

    }
}

class SmallFinal {

}

final class Dinosaur extends SmallFinal {
    int i = 7;
    int j = 40;
    SmallFinal smallFinal = new SmallFinal();

    void f() {
    }
}