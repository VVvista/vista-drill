package com.vista.drill.base.queue;


import java.util.LinkedList;
import java.util.List;

/**
 * 自定义实现双端队列的方法
 * https://juejin.im/post/5dfb1fc4e51d4557f26e601b
 * 两端 删除和添加
 * java.util.Deque接口
 * <p>
 * 此类使用链表实现队列
 *
 * @author WenTingTing by 2020/3/25
 */
public class Deque<E> implements DequeInterface<E> {

    private List<E> list = new LinkedList<E>();


    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // 出队
    @Override
    public E deQueueFront() {
        return list.remove(0);
    }

    // 入队
    @Override
    public void enQueueFront(E element) {
        list.add(0, element);

    }

    @Override
    public void enQueueRear(E element) {
        list.add(element);
    }

    @Override
    public E deQueueRear() {
        return list.remove(size() - 1);
    }

    @Override
    public E front() {
        return list.get(0);
    }

    @Override
    public E rear() {
        return list.get(size()-1);
    }
}
