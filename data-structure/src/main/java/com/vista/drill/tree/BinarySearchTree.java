package com.vista.drill.tree;

import java.util.Arrays;

/**
 * 二叉搜索树
 * 中序遍历时呈递增序列，结点元素必须具有可比性，元素不能为null
 *
 * @author WenTingTing by 2020/4/7
 */
public class BinarySearchTree {




}


interface BinarySearchTreeInterface<E> {
    // 元素的数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 清空所有元素
    void clear();

    // 添加元素
    void add(E element);

    // 删除元素
    void remove(E element);

    // 是否包含某元素
    boolean contains(E element);

}