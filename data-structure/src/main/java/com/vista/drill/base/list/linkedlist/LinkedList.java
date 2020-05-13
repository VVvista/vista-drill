package com.vista.drill.base.list.linkedlist;

import com.vista.drill.base.list.AbstractList;
import com.vista.drill.base.list.List;

/**
 * 双向链表方法
 * https://juejin.im/post/5df9c8256fb9a016214cd3de
 * <p>
 * 注意点：
 * node(index)：利用简单的二分法，根据index与size>>1的关系决定从前往后，还是从后往前遍历
 * clear():size=0,first=last=null即可。虽然结点之间存在应用，但GC root对象会对没有被JVM栈指向的对象（可以理解为临时变量）进行删除。linkedList源码中清除了结点连接是为了怕有迭代器引用
 * add(index,e):注意特殊位置：末尾添加，空链表
 * remove(index):同上
 * <p>
 * 总结：
 * add(index,e)\remove(index)中涉及到特殊位置或空链表的处理，常用方式是先写出常用的转换逻辑，然后判断哪些对象可能报空指针异常，然后对空指针进行特殊处理。
 *
 * @author WenTingTing by 2020/3/20
 */
public class LinkedList<E> extends AbstractList<E> implements List<E> {

    protected class Node<E> {
         E element;
         Node<E> pre;
         Node<E> next;

        public Node(Node<E> pre, E element, Node<E> next) {
            this.element = element;
            this.pre = pre;
            this.next = next;
        }
    }

    Node<E> first;
    Node<E> last;

    // 查找索引位置对应的node
    Node<E> node(int index) {
        // 判断下表是否越界
        isIndexValid(index);
        if (index < size >> 1) {
            Node<E> node = this.first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = this.last;
            for (int i = size - 1; i > index; i--) {
                node = node.pre;
            }
            return node;
        }
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        return node(index).element;
    }

    @Override
    public void add(int index, E element) { // 重点梳理
        // 末尾添加，优化后的代码
        if (index == size) {
            Node<E> oldLast = this.last;
            Node<E> node = new Node<E>(this.last, element, null);
            this.last = node;
            if (index == 0) {
                first = node;
            } else {
                // 在尾部插入
                oldLast.next = node;
            }
        } else {
            Node<E> next = node(index);
            Node<E> pre = next.pre;
            Node<E> node = new Node<E>(pre, element, next);
            // 在 0 位置插入
            if (index == 0) {
                first = node;
            } else {
                pre.next = node;
            }
            next.pre = node;
        }
        size++;
    }

    @Override
    public E remove(int index) { // 重点梳理

        Node<E> node = node(index);
        Node<E> pre = node.pre;
        Node<E> next = node.next;
        // 如果prev == null, 说明删除的是第一个节点;判断基准为：当pre==null时，pre.next 报空指针异常
        if (pre == null) {
            first = next;
        } else {
            pre.next = next;
        }
        // 如果next == null, 说明删除的是最后一个节点;判断基准为：当last==null时，next.pre 报空指针异常
        if (next == null) {
            last = pre;
        } else {
            next.pre = pre;
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = this.first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            Node<E> node = this.first;
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return DEFULT_bOUNDARY_VALUE;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
}
