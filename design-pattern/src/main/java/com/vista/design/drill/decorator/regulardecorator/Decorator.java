package com.vista.design.drill.decorator.regulardecorator;

/**
 * 装饰角色：持有一个构建角色对象的引用，并且实现抽象构建角色。
 * 1.如果想添加功能之后还是该类型的构建，必须实现构建角色接口；
 * 2.如果想添加功能，必须持有要添加功能的对象引用，
 *
 * @author WenTingTing by 2020/4/10
 */
public class Decorator implements Person {
    private Person person;

    public Decorator(Person person) {
        this.person = person;
    }

    @Override
    public void dress() {
        person.dress();
    }
}
