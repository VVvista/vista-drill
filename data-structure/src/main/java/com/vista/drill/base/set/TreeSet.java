package com.vista.drill.base.set;

import com.vista.drill.base.tree.RBTree;

import java.util.Comparator;

/**
 * 红黑树集合 TreeSet：底层使用红黑树存储元素
 * 查找、添加、添加的时间复杂度为：O(logn)
 * <p>
 * 注：集合元素E必须具有可比较性，即类E必须实现comparable接口，或者创建TreeSet时传入比较器comparator
 *
 * @author Wen TingTing by 2020/4/18
 */
public class TreeSet<E> implements Set<E> {
    private RBTree<E> tree;

    public TreeSet() {
        this(null);
    }

    public TreeSet(Comparator<E> comparator) {
        this.tree = new RBTree<E>(comparator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    /**
     * 添加
     * 如果元素存在则将新元素覆盖旧元素，否则添加
     * 红黑默认具有去重功能，所以直接添加即可
     *
     * @param element
     */
    @Override
    public void add(E element) {
        tree.add(element);
    }

    /**
     * 删除操作
     * 如果元素存在则删除，否在删除
     *
     * @param element
     */
    @Override
    public void remove(E element) {
        tree.remove(element);

    }

    /**
     * 建议使用中序遍历输出
     * @param visitor
     */
    @Override
    public void traversal(Visitor<E> visitor) {
    }
}
