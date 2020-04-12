package com.vista.design.drill.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂类
 * 定义抽象享元缓冲池
 * 如果缓冲池中存在对象则直接从缓冲池中获取，如果没有则添加到缓冲池中
 *
 * @author Wen TingTing by 2020/4/12
 */
public class EmployeeFactory {
    private Map<Integer, Employee> employee = new HashMap<>();

    public EmployeeFactory() {
    }

    public Employee getManager(int number) {
        Manager manager = (Manager) employee.get(number);
        if (manager == null) {
            manager = new Manager(number);
            manager.setName(number + " 号管理者");
            employee.put(number, manager);
        }
        return manager;
    }

}
