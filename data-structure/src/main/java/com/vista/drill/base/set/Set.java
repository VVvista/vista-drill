package com.vista.drill.base.set;

/**
 * 集合set
 * 不存放重复元素
 * 内部可以使用数组 链表 二叉搜索树（AVL树或红黑树）实现
 *
 * 1.红黑树集合 TreeSet：底层使用红黑树存储元素
 * 查找、添加、添加的时间复杂度为：O(logn)
 * 注：集合元素E必须具有可比较性，即类E必须实现comparable接口，或者创建TreeSet时传入比较器comparator
 * 元素不能为null
 *
 * 2.链表集合LinkedSet:不存储重复元素
 * 查询、添加、删除的时间复杂度都是O(n):需要先遍历整个链表查找是否已存在元素，然后再判断执行下面的步骤
 *
 *
 *
 * @author Wen TingTing by 2020/4/18
 */
public interface Set<E> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor); //遍历集合

    public static abstract class Visitor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }
}
