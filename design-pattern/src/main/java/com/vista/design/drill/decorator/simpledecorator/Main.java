package com.vista.design.drill.decorator.simpledecorator;

/**
 * 修饰模式：基本方式
 * 修饰模式是在既有功能的前提下添加新的扩展功能
 * 基本方式：使用继承方式
 * 1.创建核心基类，并定义对外的接口
 * 2.创建具体修饰类继承核心基类，在基类的基础上扩展方法功能。
 * 若需要扩展不同的功能，则创建不同的子类
 * <p>
 * 特点：利用继承扩展基类的方法功能，不同的功能需创建不同的子类，客户端需要知道具体的扩展子类，创建并调用方法
 * 缺点：不能组合已创建的子类功能，只能创建子类实现组合功能。
 *
 * @author WenTingTing by 2020/4/10
 */
public class Main {
    public static void main(String[] args) {
        final Person women = new Women();
        women.dress();

        final Men men = new Men();
        men.dress();
    }

}
