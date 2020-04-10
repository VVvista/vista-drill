package com.vista.java.thinginjava.c5;

/**
 * Create a class without a constructor, and then create an object of that
 * class in main() to verify that the default constructor is automatically
 * synthesized.
 *
 * @author WenTingTing by 2020/4/10
 */
public class C5_7_AutomaticConstructor {
    public static void main(String[] args) {
        final Aso aso = new Aso();
        aso.bark();
    }

}

class Aso {
    void bark() {
        System.out.println("woof");
    }
}