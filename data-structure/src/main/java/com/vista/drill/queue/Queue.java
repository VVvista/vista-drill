package com.vista.drill.queue;


import java.util.LinkedList;

/**
 * 自定义实现队列的方法
 * https://juejin.im/post/5dfb1fc4e51d4557f26e601b
 * 队列具有先进先出FIFO的原则
 * java.util.Queue接口
 * 底层可以使用动态数组、链表实现，优先采用双向链表实现（因为含有first、last指针便于添加、删除操作）
 * <p>
 * 此类使用双向链表实现队列
 *
 * @author WenTingTing by 2020/3/25
 */
public class Queue<E> implements QueueInterface<E> {

    LinkedList<E> list = new LinkedList<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enQueue(E element) {
        list.add(element);
    }

    @Override
    public E deQueue() {
        return list.remove(0);
    }

    @Override
    public E front() {
        return list.get(0);
    }
}

