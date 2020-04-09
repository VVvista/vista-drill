package com.vista.design.drill.prototype.deepclone;


/**
 * 原型模式：深拷贝
 * 方式1：具体原型类实现Cloneable接口、实现clone()方法处理逻辑：在super.clone拷贝对象后，再对引用属性进行深拷贝赋值
 * 此时引用属性对象类也需要实现Cloneable接口、重写clone()方法，否则编译无意义
 * 方式2：使用序列化方式将对象写入内存中，然后反序列化拿出来
 * 具体实现：具体原型类实现Serializable接口（引用属性对象类也需要实现Serializable接口），使用二进制流写入内存，然后再读取出来，得到一个新对象
 * <p>
 * 两种方式比较：
 * 方式1是硬编码，当引用属性比较多或属性嵌套比较深时，逐个深拷贝赋值比较麻烦
 * 方式2：利用序列化方式性能高
 *
 * @author WenTingTing by 2020/4/8
 */
public class Main {
    public static void main(String[] args) {
        //创建原型对象
        ConcretePrototype prototype = new ConcretePrototype("tom", 12, new Person("tom", 12));
        System.out.println(prototype);// ConcretePrototype(name=tom, age=12, person=Person(name=tom, age=12))

        // 调用clone()实现深拷贝
        //拷贝原型对象
        ConcretePrototype cloneType = prototype.clone();
        System.out.println(cloneType == prototype);//false
        System.out.println(cloneType.getPerson() == prototype.getPerson());//  引用类型属性深拷贝：false

        // 改变原对象引用类型属性的值，新对象不会改变
        prototype.getPerson().setName("jack");
        System.out.println(prototype);// ConcretePrototype(name=tom, age=12, person=Person(name=jack, age=12))
        System.out.println(cloneType);// ConcretePrototype(name=tom, age=12, person=Person(name=tom, age=12))


        //创建原型对象
        ConcretePrototype deepPrototype = new ConcretePrototype("tom", 12, new Person("tom", 12));
        // 调用deepClone()实现深拷贝
        ConcretePrototype deepClone = deepPrototype.deepClone();
        System.out.println(deepClone == deepPrototype);//false
        System.out.println(deepClone.getPerson() == deepPrototype.getPerson());//  引用类型属性深拷贝：false

        // 抵用clone进行深拷贝，改变原对象引用类型属性的值，新对象不会改变
        deepPrototype.getPerson().setName("jack");
        System.out.println(prototype);// ConcretePrototype(name=tom, age=12, person=Person(name=jack, age=12))
        System.out.println(cloneType);// ConcretePrototype(name=tom, age=12, person=Person(name=tom, age=12))


    }
}
