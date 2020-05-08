package com.vista.java.thinkinjava.c7;

/**
 * 继承-方法重载
 * <p>
 * 子类可以重载父类的方法，同时可以对外调用子类+父类定义的所有父类方法，与重写不同
 *
 * @author WenTingTing by 2020/5/8
 */
public class C7_4_1_Overloading {
    public static void main(String[] args) {
        Bart bart = new Bart();
        bart.doh(new MilHouse());
        bart.doh('a');
        bart.doh(2.0f);
    }
}

class Homer {
    char doh(char c) {
        System.out.println("doh(char)");
        return 'd';
    }

    float doh(float f) {
        System.out.println("doh(float)");
        return 1.0f;
    }
}

class MilHouse {
}

class Bart extends Homer {
    void doh(MilHouse m) {
        System.out.println("doh(MilHouse)");
    }
}