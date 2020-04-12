package com.vista.design.drill.flyweight;

/**
 * 享元模式：
 * 1.创建抽象享元类：Employee
 * 2.创建具体享元类：Manager
 * 3.创建享元工厂：添加享元类缓冲池，定义对外获取享元对象的方法：如果存在直接获取，如果不存在则添加
 * <p>
 * 特点：可以有效减少相同属性对象的创建，减少内存消耗
 * 缺点：缓冲池的使用需要考虑线程问题，对于不同的对象需要创建不同的缓冲池，工厂类的处理逻辑变复杂
 *
 * @author Wen TingTing by 2020/4/12
 */
public class Main {
    public static void main(String[] args) {
        EmployeeFactory factory = new EmployeeFactory();
        Employee employee1 = factory.getManager(1);
        Employee employee2 = factory.getManager(2);
        Employee employee3 = factory.getManager(3);
        Employee employee4 = factory.getManager(4);

        employee1.report();
        employee2.report();
        employee3.report();
        employee4.report();

        //验证是否为相同对象
        Employee employee5 = factory.getManager(1);
        System.out.println(employee1 == employee5);// true

    }
}
