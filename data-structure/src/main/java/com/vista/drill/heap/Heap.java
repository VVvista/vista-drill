package com.vista.drill.heap;

/**
 * 堆
 * 需求：获取最大值、删除最大值、添加元素
 * 堆是树状的数据结构，实现：二叉堆（完全二叉堆）、多叉堆、索引堆...
 * 性质：任意节点值都 >= (或 <=) 子节点的值 (元素必须具有可比较性，且不能为null)
 * 1.若节点值总是 >= 子节点的值，称为：最大堆、大根堆、大顶堆
 * 2.若节点值总是 <= 子节点的值，称为：最小堆、小根堆、小顶堆
 * <p>
 * 常见问题：TOP K 问题
 *
 * @author Wen TingTing by 2020/4/25
 */
public interface Heap<E> {
    int size();    // 元素的数量

    boolean isEmpty();    // 是否为空

    void clear();    // 清空

    void add(E element);     // 添加元素

    E get();    // 获得堆顶元素

    E remove(); // 删除堆顶元素

    E replace(E element); // 删除堆顶元素的同时插入一个新元素
}
