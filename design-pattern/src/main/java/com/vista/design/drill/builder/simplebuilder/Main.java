package com.vista.design.drill.builder.simplebuilder;

/**
 * 建造者模式：
 * 1.基本方式：
 * 1.1创建一个建造者类ConcreteBuilder，对象的创建及对对象属性的赋值及方法调用交由建造者处理，对外不暴露对象类的任意属性及结构，
 * 客户端只需要调用建造者对应的方法即可
 * 2.链式方式：
 * 2.1在方式1的基础上，建造者调用对象类方法后返回对象，便于后续的持续调用（类比于StringBuilder.append()方法）
 * <p>
 * 总结：创建不同的类对象，只需要创建建造者并指定方法调用顺序和参数，无需知道类对象的构造和方法
 * 缺点：虽然对外不暴露类对象的结构和构造，但客户端仍须知道建造者的方法调用顺序
 * 1.实现某个类对象产品时，仍需一步步指定建造者方法调用顺序。
 * 2.创建不同的类对象时，需要创建不同builder对象。
 * 3.客户端需要书写调用顺序，如果创建类对象过多，客户端仍会有大量代码。
 * 4.如果产品内部发生改变，建造者也需要修改，成本较大
 *
 * @author WenTingTing by 2020/4/9
 */
public class Main {
    public static void main(String[] args) {
        //基本方式
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        concreteBuilder.setId(12);
        concreteBuilder.setName("椅子");
        concreteBuilder.setDesc("宜家-莫塔利索系列");
        System.out.println(concreteBuilder.build());

        ConcreteBuilder concreteBuilder1 = new ConcreteBuilder();
        concreteBuilder1.setId(13);
        concreteBuilder1.setName("床");
        concreteBuilder1.setDesc("席梦思");
        System.out.println(concreteBuilder1.build());

        // 链式方式
        ConcreteLinkedBuilder linkedBuilder = new ConcreteLinkedBuilder();
        linkedBuilder.setId(13)
                .setName("台灯")
                .setDesc("飞利浦护眼台灯");
        System.out.println(linkedBuilder.build());

    }


}
