package com.vista.design.drill.strategy;

/**
 * 策略模式：
 * 1.定义抽象策略类Strategy，并定义对外算法计算方法
 * 2.定义具体策略类AddStrategy，并实现算法方法的具体实现逻辑
 * 3.定义环境类Context，添加抽象策略类的对象引用，并对外提供算法调用接口
 * <p>
 * 特点：
 * 1.不同的算法只需新建具体策略类，继承抽象策略类即可。
 * 2.客户端需要知道有哪些具体策略类，根据需求创建并指定策略类对象
 *优点：
 * 1.不同算法的扩展只需新建具体策略类即可。
 * 缺点：
 * 客户端需要知道有哪些具体策略类，才能正确的定义
 * @author Wen TingTing by 2020/4/11
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        AddStrategy addStrategy = new AddStrategy();
        context.setStrategy(addStrategy);
        int calculate = context.calculate(2, 3);
        System.out.println(calculate);// 5

        SubtractStrategy subtractStrategy = new SubtractStrategy();
        context.setStrategy(subtractStrategy);
        int calculate1 = context.calculate(10, 4);
        System.out.println(calculate1); // 6

    }
}
