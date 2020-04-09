package com.vista.design.drill.prototype.shallowclone;


/**原型模式：浅拷贝
 * 利用java自身的object.clone方法
 * 1.具体原型实现Cloneable接口，实现clone()方法处理逻辑：直接调用super.clone实现的是浅拷贝
 * @author WenTingTing by 2020/4/8
 */
public class Main {
    public static void main(String[] args) {
        //创建原型对象
        ConcretePrototype prototype = new ConcretePrototype("tom", 12, new Person("tom", 12));
        System.out.println(prototype);// ConcretePrototype(name=tom, age=12, person=Person(name=tom, age=12))

        //拷贝原型对象
        ConcretePrototype cloneType = prototype.clone();
        System.out.println(cloneType == prototype);//false
        System.out.println(cloneType.getPerson()==prototype.getPerson());//  引用类型属性浅拷贝，地址拷贝：true

        // 此处为浅拷贝，改变引用类型属性的值，原、新对象都会改变
        prototype.getPerson().setName("jack");
        System.out.println(prototype);// ConcretePrototype(name=tom, age=12, person=Person(name=jack, age=12))
        System.out.println(cloneType);// ConcretePrototype(name=tom, age=12, person=Person(name=jack, age=12))


    }
}
