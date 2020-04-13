package com.vista.java.base.proxy.human;

/**
 * @author WenTingTing by 2020/4/13
 */
public class SuperWoman implements Human {
    @Override
    public String getBelief() {
        return "享受人生，享受生活!";
    }

    @Override
    public void eat(String food) {
        System.out.println("女超人喜欢吃:" + food);
    }
}
