package com.vista.java.thinkinjava.c7.example;

/**
 * Prove that base-class constructors are (a) always called and (b) called before
 *
 * @author WenTingTing by 2020/5/8
 */
public class C7_4_Extends {

}

class A {
    A() {
        System.out.println("A()");
    }
}

class B extends A {
    B() {
        System.out.println("B()");
    }
}

class C extends B {
    C() {
        System.out.println("C()");
    }
}

class D extends C {
    D() {
        System.out.println("D()");
    }

    public static D makeD() {
        return new D();
    }

    public static void main(String[] args) {
        D d = new D();
        System.out.println();
        D d2 = makeD();
    }
}

class E extends D {
    E() {
        System.out.println("E()");
    }

    public static void main(String[] args) {
        E e = new E();
        System.out.println();
        // test D:
        D.main(args);
    }
}