package com.vista.drill.stack;

/**
 * @author WenTingTing by 2020/3/25
 */
public interface StackInterface<E> {

    // 元素的数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 入栈
    void push(E element);

    // 出栈
    E pop();

    // 获取栈顶元素
    E top();
}
