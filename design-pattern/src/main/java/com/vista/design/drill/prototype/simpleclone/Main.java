package com.vista.design.drill.prototype.simpleclone;


/**
 * 原型模式：简单拷贝
 * 1.抽象原型接口定义拷贝clone方法(即prototype.clone())
 * 2.具体原型实现抽象原型，并重写clone实现逻辑：使用new关键字创建新对象，并对新对象属性赋值（即ConcretePrototype.clone()）
 * @author WenTingTing by 2020/4/8
 */
public class Main {

    public static void main(String[] args) {
        //创建原型对象
        ConcretePrototype prototype = new ConcretePrototype("tom", 12);
        System.out.println(prototype);// ConcretePrototype(name=tom, age=12)

        //拷贝原型对象
        ConcretePrototype cloneType = prototype.clone();
        System.out.println(cloneType==prototype);// false

        System.out.println(cloneType);// ConcretePrototype(name=tom, age=12)

        // 改变原对象的属性对新对象无影响，此处为自定义拷贝
        prototype.setName("jack");
        System.out.println(prototype);// ConcretePrototype(name=jack, age=12)
        System.out.println(cloneType);// ConcretePrototype(name=tom, age=12)


    }
}
