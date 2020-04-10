package com.vista.design.drill.decorator.simpledecorator;

/**
 * @author WenTingTing by 2020/4/10
 */
public class Women implements Person {
    @Override
    public void dress() {
        makeUp();
    }


    public void makeUp() {
        System.out.println("女士化妆抹粉");
    }
}
