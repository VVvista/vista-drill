package com.vista.drill.queue;

/**
 * 自定义实现循环队列方法
 * https://juejin.im/post/5dfb1fc4e51d4557f26e601b
 * 底层使用数组实现
 * <p>
 * 注意点：
 * 1.入队时，索引位置的变化
 * 2.出队时，元素索引位置的确认
 * 3.入队时，容量扩容，队头指针从front开始
 *
 * @author WenTingTing by 2020/3/27
 */
public class CircleQueue<E> implements CircleQueueInterface<E> {
    private E[] elements;
    private int front;
    private int size;
    private int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public CircleQueue(int size) {
        elements = (E[]) new Object[size];
    }

    /**
     * 数组容量判断及扩容
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (capacity < elements.length) {
            return;
        }
        // 容量扩大为原来的1.5倍
        int newCapacity = oldCapacity + oldCapacity >> 1;
        if (capacity > newCapacity) {
            newCapacity = capacity;
        }
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % oldCapacity];
        }
        front = 0;
        elements = newElements;
    }

    /**
     * 索引计算：根据传入的index 计算出其在E[]中的真实索引位置
     *
     * @param index
     * @return
     */
    private int index(int index) {
        return (front + index) % elements.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }

    /**
     * 注意点：入队索引位置、队列是否需要扩容
     *
     * @param element
     */
    @Override
    public void enQueue(E element) {
        ensureCapacity(size + 1);
        //  elements[(front + size) % elements.length] = element;
        elements[index(size)] = element;
        size++;
    }

    /**
     * 注意出队后更改头指针位置
     *
     * @return
     */
    @Override
    public E deQueue() {
        E element = elements[front];
        elements[front] = null;
        //front = (front + 1) % elements.length;
        front = index(1);
        size--;
        return element;
    }

    @Override
    public E front() {
        return elements[front];
    }
}
