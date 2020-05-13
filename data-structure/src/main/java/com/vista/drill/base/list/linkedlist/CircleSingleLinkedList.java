package com.vista.drill.base.list.linkedlist;

/**
 * 单向循环链表
 * https://juejin.im/post/5dfad5936fb9a0160b6381df
 * <p>
 * 单向循环链表是在单链表的基础上，尾结点指向头结点
 * 只需重写add(index,e)\remove(index)方法即可。
 *
 * @author WenTingTing by 2020/3/24
 */
public class CircleSingleLinkedList<E> extends SingleLinkedList<E> {

    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * Node<E> pre = node(index - 1);
     * Node<E> node = new Node<E>(element, pre.next);
     * pre.next = node;
     * <p>
     * 1.index-1<0  => size==0
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 头插入
        if (index == 0) {
            Node<E> node = new Node<E>(element, first);
            // 插入空链表
       /*     if (size == 0) {
                node.next = node;
                first = node;
            } else {
                Node<E> last = node(size - 1);
                first = node;
                last.next = node;
            }*/
            Node<E> last = size == 0 ? node : node(size - 1);
            first = node;
            last.next = node;
        } else {
            Node<E> pre = node(index - 1);
            Node<E> node = new Node<E>(element, pre.next);
            pre.next = node;
        }
        size++;
    }

    /**
     * Node<E> pre = node(index - 1);
     * pre.next = pre.next.next;
     * <p>
     * 1.index-1<0  => size==0
     * 2.pre.next.next不可能为null，所以无需特殊判断
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        isIndexValid(index);
        Node<E> node = first;
        // 头删除
        if (index == 0) {
            // 只有一个结点
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                first = first.next;
                last.next = first;
            }
        } else {
            Node<E> pre = node(index - 1);
            node = pre.next;
            pre.next = node.next;
        }
        size--;
        return node.element;
    }
}
