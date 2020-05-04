package com.vista.java.thinkinjava.c5.exercises;

/**
 * Modify the previous exercise so that two of the overloaded methods have two
 * arguments (of two different types), but in reverse order to each other.
 * Verify that this works.
 *
 * @author WenTingTing by 2020/4/10
 */
public class C5_6_DogTalk2 {
    public static void main(String[] args) {
        final Dog2 dog2 = new Dog2();
        char c = 'c';
        byte b = 0;
        short s = 0;
        int i = 0;
        dog2.bark(c, i);
        dog2.bark(b, i);// byte 直接自动提升为int
        dog2.bark(s, i);
        dog2.bark(c, i);

    }

}

class Dog2 {

    void bark(char c, int i) {
        System.out.println("char int ");
    }

    void bark(int i, char c) {
        System.out.println("int char ");
    }
    void bark(int i, int in) {
        System.out.println("int int ");
    }
}