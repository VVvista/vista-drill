package com.vista.drill.base.set;

import java.util.LinkedList;
import java.util.List;

/**
 * 链表集合LinkedSet:不存储重复元素
 * 查询、添加、删除的时间复杂度都是O(n):需要先遍历整个链表查找是否已存在元素，然后再判断执行下面的步骤
 *
 * @author Wen TingTing by 2020/4/18
 */
public class LinkedSet<E> implements Set<E> {
    private List<E> list = new LinkedList<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    /**
     * 添加
     * 如果元素存在则将新元素覆盖旧元素，否则添加
     *
     * @param element
     */
    @Override
    public void add(E element) {
        int index = list.indexOf(element);
        if (index != -1) {
            list.set(index, element);
        } else {
            list.add(element);
        }
    }

    /**
     * 删除操作
     * 如果元素存在则删除，否在删除
     *
     * @param element
     */
    @Override
    public void remove(E element) {
        if (!contains(element)) {
            list.remove(element);
        }
    }

    /**
     * 遍历输出
     *
     * @param visitor
     */
    @Override
    public void traversal(Visitor<E> visitor) {
        if(visitor==null) return;
        for (int i = 0; i < list.size(); i++) {
            visitor.visit(list.get(i));
        }
    }
}
