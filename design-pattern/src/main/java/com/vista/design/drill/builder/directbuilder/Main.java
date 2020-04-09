package com.vista.design.drill.builder.directbuilder;


import com.vista.design.drill.builder.Product;

/**
 * 建造者模式：客户端直接创建对象，并制定创建过程
 * <p>
 * 缺点：
 * 1.客户端必须知道product类的构造函数以及类结构（方法），类对外暴露太多细节
 * 2.如果product类参数过多或构造过程太复杂，构造多个不同对象实例时，客户端代码过多，任务繁重
 *
 * @author WenTingTing by 2020/4/9
 */
public class Main {

    public static void main(String[] args) {
        //创建原型对象
        Product product = new Product();
        product.setId(12);
        product.setName("椅子");
        product.setDesc("宜家-莫塔利索系列");
        System.out.println(product);
    }
}
