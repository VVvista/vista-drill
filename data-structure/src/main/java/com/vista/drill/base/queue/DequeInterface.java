package com.vista.drill.base.queue;

/**
 * @author WenTingTing by 2020/3/27
 */
interface DequeInterface<E> {
    // 元素的数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 从队头出队
    E deQueueFront();

    // 从队头入队
    void enQueueFront(E element);

    // 从队尾入队
    void enQueueRear(E element);

    // 从队尾出队
    E deQueueRear();

    // 获取队列的头元素
    E front();

    // 获取队列的尾元素
    E rear();

}
