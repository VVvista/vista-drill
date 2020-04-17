package com.vista.design.drill.chainofresponsibility;

/**
 * 责任链模式
 * 1.创建基本信息类：定义基本信息属性和方法
 * 2.创建抽象处理类：定义处理类属性和方法，添加抽象处理类对象引用
 * 3.创建具体处理类：实现方法处理逻辑
 * <p>
 * 将相同类型的处理类对象连成一条链，调用首位对象方法时，让其沿着这条链传递处理该青丘，直到有对象处理完成。
 * <p>
 * 在处理类中添加next处理类对象的引用，在客户端创建多个处理类对象，并将它们按照规则设置成一条链，调用首位对象的方法，直到有对象处理完。
 * <p>
 * 特点：
 * 将请求信息体和处理者分开，请求信息无需知道到底是谁处理，处理者也无需知道请求信息的全貌，实现了开闭原则。提高系统的灵活性。
 * 客户端只需设置请求信息体和处理对象，以及处理对象之间的关系，并将请求信息体传递给首位对象的处理方法即可，无需知道请求信息体在对象之间的传递和处理方式。
 * <p>
 * 优点：
 * 请求信息体和处理时解耦，新添加处理者时，无需改动系统其它代码。
 * 缺点:
 * 1.当处理者对象很多，且关系复杂时，方法的处理逻辑变得复杂，程序的处理性能也下降。当链表过长时，从链头遍历到链尾，性能消耗过大
 * 2.不易于调试，该模式类似于递归，调试时逻辑比较复杂
 *
 * 责任链中的链节点个数不宜过长。
 *
 * @author WenTingTing by 2020/4/17
 */
public class Main {
    public static void main(String[] args) {
        LeaveRequest request = new LeaveRequest("小白", 24, "看医生");

        Director director = new Director("小朱");
        Manager manager = new Manager("小经");
        ViceGeneralManager viceGeneralManager = new ViceGeneralManager("小付");
        GeneralManager generalManager = new GeneralManager("小总");
        director.nextLeader = manager;
        manager.nextLeader = viceGeneralManager;
        viceGeneralManager.nextLeader = generalManager;

        director.handleRequest(request);

    }
}
