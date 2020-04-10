package com.vista.design.drill.decorator.regulardecorator;

/**
 * 具体构建角色
 * 实现抽象构建角色，可以添加一些基本方法功能
 *
 * @author WenTingTing by 2020/4/10
 */
class Women implements Person {

    @Override
    public void dress() {
        System.out.println("女士穿着");
    }
}
