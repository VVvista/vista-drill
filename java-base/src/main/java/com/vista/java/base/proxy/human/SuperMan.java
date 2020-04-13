package com.vista.java.base.proxy.human;

/**
 * @author WenTingTing by 2020/4/13
 */
public class SuperMan implements Human {
    @Override
    public String getBelief() {
        return "不以物喜不以己悲!";
    }

    @Override
    public void eat(String food) {
        System.out.println("超人喜欢吃:" + food);
    }
}
