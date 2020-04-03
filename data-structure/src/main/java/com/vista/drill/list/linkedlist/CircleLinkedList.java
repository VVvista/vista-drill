package com.vista.drill.list.linkedlist;


/**
 * 自定义双向循环链表
 * https://juejin.im/post/5dfad5936fb9a0160b6381df#heading-3
 * <p>
 * 双向循环链表是在双链表的基础上 first.pre=last;last.next=pre
 * 只需重写add(index,e)\remove(index)方法即可。
 *
 * @author WenTingTing by 2020/3/24
 */
public class CircleLinkedList<E> extends LinkedList<E> {

    /**
     * Node<E> next = node(index);
     * Node<E> pre = next.pre;
     * Node<E> node = new Node<E>(pre, element, next);
     * pre.next = node;
     * next.pre = node;
     * <p>
     * 仅需尾插入index==size =>size=0(避免first.pre=null)
     * 因为双向循环链表size>1时，删除操作后都有至少有一个元素，此时指针指向仍成立，只需再修改指针位置即可。
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) { // 重点梳理
        rangeCheckForAdd(index);
        // 尾插入
        if (index == size) {
            Node<E> node = new Node<E>(last, element, first);
            if (size == 0) {
                node.pre = node;
                node.next = node;
                first = node;
                last = node;
            } else {
                last.next = node;
                first.pre = node;
                last = node;
            }
        } else {
            Node<E> next = node(index);
            Node<E> pre = next.pre;
            Node<E> node = new Node<E>(pre, element, next);
            pre.next = node;
            next.pre = node;
            if (index == 0) {
                first = node;
            }
        }
        size++;
    }

    /**
     * Node<E> node = node(index);
     * Node<E> pre = node.pre;
     * Node<E> next = node.next;
     * pre.next = next;
     * next.pre = pre;
     * <p>
     * 仅需判断size==1
     * 因为双向循环链表size>1时，删除操作后都有至少有一个元素，此时指针指向仍成立，只需再修改指针位置即可。
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) { // 重点梳理
        isIndexValid(index);
        Node<E> node = node(index);
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> pre = node.pre;
            Node<E> next = node.next;
            next.pre = pre;
            pre.next = next;
            // 如果node == first, 说明删除的是第一个节点
            if (node == first) {
                first = next;
            }
            // 如果next == last, 说明删除的是最后一个节点
            if (next == last) {
                last = pre;
            }
        }
        size--;
        return node.element;
    }
}
