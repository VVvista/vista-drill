package com.vista.java.thinkinjava.c7.examples;

/**
 * final方法-final+private
 * final修饰方法：子类不能再重写该方法
 * 1.private方法默认指定为final，所以private+final时final可以省略，该方法只能父类调用，对外隐藏
 * 2.子类中可重新定义该方法，但视作子类中自定义的新方法，与父类方法无关
 *
 * @author Wen TingTing by 2020/5/8
 */
public class C7_7_8_4_Final {
    public static void main(String[] args) {
        OverridingPrivate2 private2 = new OverridingPrivate2();
        private2.f();
        private2.g();
        OverridingPrivate private21 = private2;
     /*   private21.f(); // 调用失败，因为f、g方法为private
        private21.g();*/

    }
}

class WithFinals {

    private final void f() { // final关键字可省略
        System.out.println("withFinals.f");
    }

    private void g() {
        System.out.println("withFinals.g");
    }
}

class OverridingPrivate extends WithFinals {
    private final void f() {
        System.out.println("OverridingPrivate.f");
    }

    private void g() {
        System.out.println("OverridingPrivate.g");
    }

}

class OverridingPrivate2 extends OverridingPrivate {
    public final void f() {
        System.out.println("OverridingPrivate2.f");
    }

    public void g() {
        System.out.println("OverridingPrivate2.g");
    }

}