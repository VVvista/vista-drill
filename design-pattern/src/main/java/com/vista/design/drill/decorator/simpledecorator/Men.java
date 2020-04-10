package com.vista.design.drill.decorator.simpledecorator;

/**
 * @author WenTingTing by 2020/4/10
 */
public class Men implements Person {
    @Override
    public void dress() {
        clothes();
    }


    public void clothes() {
        System.out.println("男士穿西装打领带");
    }

}
