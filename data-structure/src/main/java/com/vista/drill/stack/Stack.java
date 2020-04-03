package com.vista.drill.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义实现stack 栈方法
 * https://juejin.im/post/5dfb12fd518825122e0a85bd#heading-0
 * <p>
 * 底层可以使用动态数组或链表实现，仅在栈顶进行出栈和入栈操作
 * java有具体的java.util.Stack类，底层使用Vector实现，stack中的push、pop等操作均是调用Vector中对应的add、remove方法
 * 现只用数组实现(可以使用java.util.List，也可以使用自定义的list)
 * <p>
 * <p>
 * 常用方法：
 * pop():出栈
 * push():入栈
 * top():查看栈顶元素
 *
 * @author WenTingTing by 2020/3/25
 */
public class Stack<E> implements StackInterface<E> {

    // 使用动态数组实现栈
    private List<E> list = new ArrayList<E>();

    @Override
    public int size() {
        // 栈大小
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        // 判断栈是否为空
        return list.isEmpty();
    }

    @Override
    public void push(E element) {
        // 添加栈顶元素
        list.add(element);
    }

    @Override
    public E pop() {
        // 移除栈顶元素
        return list.remove(size() - 1);
    }

    @Override
    public E top() {
        // 查看栈顶元素
        return list.get(0);
    }

    public void clear() {
        // 清空栈, 就是清空列表
        list.clear();
    }
}
