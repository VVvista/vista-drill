package com.vista.drill.queue;

/**
 * @author WenTingTing by 2020/3/27
 */
interface CircleQueueInterface<E> {
    int DEFULT_bOUNDARY_VALUE = -1;
    // 当前队列存储的元素数量
    int size();

    // 当前队列是否为空
    boolean isEmpty();

    // 入队
    void enQueue(E element);

    // 出队
    E deQueue();

    // 查看索引为0的元素
    E front();


}
