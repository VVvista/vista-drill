package com.vista.design.drill.builder.abstractbuilder;

/**
 * 建造者模式：常规方式
 * <p>
 * 主要的四个角色：
 * 产品（Product）:要创建的产品类对象
 * 建造者抽象（Builder）：建造者的抽象类，规范产品对象的各个组成部分的建造，一般由子类实现具体的建造过程。
 * 建造者（ConcreteBuilder）:具体的 Builder 类，根据不同的业务逻辑，具体化对象的各个组成部分的创建。
 * 调用者（Director）：调用具体的建造者，来创建对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。
 * <p>
 * <p>
 * 特点：
 * 1.如果创建不同的类对象，直接创建不同AbstractBuilder子类即可
 * 2.如果类对象的调用顺序不同，直接在Director创建不同的调用顺序方法即可
 * 3.客户端无需知道类对象或建造者的结构以及调用顺序，只需调用调用者Director方法即可，让调用者去执行。
 *   即将原客户端对建造者的方法调用顺序操作，封装到Director中，客户端直接调用调用者方法。
 *
 *
 * @author WenTingTing by 2020/4/9
 */
public class Main {
    public static void main(String[] args) {
        //创建原型对象
        Director director = new Director();


        GongYuBuilder gongYuBuilder = new GongYuBuilder();
        director.makeProduct(gongYuBuilder);
        System.out.println(gongYuBuilder.build());


        PingFangBuilder pingFangBuilder = new PingFangBuilder();
        director.makeProduct(pingFangBuilder);
        System.out.println(pingFangBuilder.build());// Product(id=13, name=桌子, desc=办公桌)


        PingFangBuilder pingFangSimpleBuilder = new PingFangBuilder();
        director.makeSimpleProduct(pingFangSimpleBuilder);
        System.out.println(pingFangSimpleBuilder.build());// Product(id=13, name=桌子, desc=null)
    }
}
