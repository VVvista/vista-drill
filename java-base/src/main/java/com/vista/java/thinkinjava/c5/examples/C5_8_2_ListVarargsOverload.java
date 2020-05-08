package com.vista.java.thinkinjava.c5.examples;

import com.vista.java.thinkinjava.c5.exercises.C5_1_ConstructorTest;

import java.util.Arrays;

/**
 * 5.8.1 可变参数列表-方法重载
 * 方法重载时，编译器调用参数匹配最精确的一个，
 * 当可变参数为空时会出现不能确定匹配哪个方法的问题
 *
 * @author WenTingTing by 2020/5/7
 */
public class C5_8_2_ListVarargsOverload extends C5_1_ConstructorTest {
    public static void main(String[] args) {
        OverloadVarargs varargs = new OverloadVarargs();
        varargs.f(1, 2, 3);
        varargs.f('a', 'b', 'c');
        varargs.f(1F, 2F, 3F);
        //  varargs.f(); //编译报错，因为不能确定调用哪个重载方法
        varargs.f(1f, 'a');
        //    varargs.f(1f); //编译报错，因为不能确定调用哪个重载方法
    }
}

class OverloadVarargs {

    void f(Character... args) {
        System.out.println("arrays.list:" + Arrays.asList(args));
    }

    void f(Integer... args) {
        System.out.println("arrays.list:" + Arrays.asList(args));
    }

    void f(Float... args) {
        System.out.println("arrays.list:" + Arrays.asList(args));
    }

    void f(Float f, Character... args) {
        System.out.println("arrays.list:" + Arrays.asList(args));
    }
}