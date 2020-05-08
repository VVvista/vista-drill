package com.vista.java.thinkinjava.c7.examples;

/**
 * final参数
 * 方法参数被final修饰：在方法中只能调用参数，无法对参数进行修改
 *
 * @author Wen TingTing by 2020/5/8
 */
public class C7_7_8_2_Final {
    public static void main(String[] args) {
        FinalArguments arguments = new FinalArguments();
        arguments.with(null);
        arguments.without(null);
    }
}

class Gizmo {
    void spin() {

    }
}

class FinalArguments {
    void with(final Gizmo g) {
        //  g=new Gizmo();// 编译报错： g is final,不能被修改
    }

    void without(Gizmo g) {
        g = new Gizmo();// 正常调用
        g.spin();
    }

    /*void f(final int i){
        // i++;// 编译报错：i不能被修改
    }*/
    int f(final int i) {
        return i + 1;
    }
}