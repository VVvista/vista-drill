package com.vista.drill.queue;

/**
 * @author WenTingTing by 2020/3/25
 */
public interface QueueInterface<E> {
    // 元素的数量
    public int size();

    // 是否为空
    public boolean isEmpty();

    // 入队
    public void enQueue(E element);

    // 出队
    public E deQueue();

    // 获取队列的头元素
    public E front();

}
