package com.vista.design.drill.adapter.objectadapter;

import com.vista.design.drill.adapter.DBSocket;
import com.vista.design.drill.adapter.DBSocketInterface;
import com.vista.design.drill.adapter.Hotel;

/**
 * 对象适配器
 * <p>
 * 1.创建抽象类、具体类：提供并实现对外的公共方法
 * 2.创建客户端类：添加抽象类引用，提供对外访问方法：底层调用抽象类对象的方法
 * 3.创建适配者接口、适配者类：提供并实现对外的公共方法
 * 4.创建适配器类：实现抽象类，添加适配者类引用，实现抽象类方法：底层调用适配器类方法
 * <p>
 * 此方式与类适配器相同，只是在适配器中创建和添加适配者对象引用不同，前者是使用继承，现使用组合方法
 *
 * @author WenTingTing by 2020/4/16
 */
public class Main {
    public static void main(String[] args) {
        // hotel类常规的调用
        DBSocketInterface dbSocket = new DBSocket();
        Hotel hotel = new Hotel();
        hotel.setDbSocket(dbSocket);
        hotel.charge(); // 使用两项圆头的插孔供电

        // 使用适配器,调用非适配接口的方法
        GBSocket gbSocket = new GBSocket();
        DBSocketInterface dbSocketAdapter = new SocketAdapter(gbSocket);
        hotel.setDbSocket(dbSocketAdapter);
        hotel.charge();// 使用三项扁头插孔供电

    }
}
