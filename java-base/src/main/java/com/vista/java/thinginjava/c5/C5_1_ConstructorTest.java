package com.vista.java.thinginjava.c5;

/**
 * Create a class containing an unitialized String reference. Demonstrate that
 * this reference is initialized by Java to null.
 *
 * @author WenTingTing by 2020/4/10
 */
public class C5_1_ConstructorTest {
    public static void main(String[] args) {
        Tester tester = new Tester();
        System.out.println(tester.s); // null
    }

}

class Tester {
    String s;

}
