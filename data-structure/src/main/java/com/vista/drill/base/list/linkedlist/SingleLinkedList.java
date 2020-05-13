package com.vista.drill.base.list.linkedlist;

import com.vista.drill.base.list.AbstractList;

/**
 * 单链表方法
 * https://juejin.im/post/5df98c92e51d455836159eef
 * <p>
 * 允许添加null元素
 * 注意点：
 * 1.私有内部类Node<E>:private static class Node{E element},因为已经定义为私有内部类了，所以类属性无需加修饰符
 * 2.clear()：size=0;first=null;无需将结点元素或next置为null，因为首节点失去引用后，Jvm就会回收
 * 3.add(index,E):注意边界位置：index=0,size-1,size,index-1...
 * 4.indexOf(E):遍历链表的时候使用for(i->size)
 * 5.remove(index):注意边界位置
 *
 * @author WenTingTing by 2020/3/19
 */
public class SingleLinkedList<E> extends AbstractList<E> {

    protected static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    // 头指针
    Node<E> first;


    // 查找索引位置对应的node
    Node<E> node(int index) {// 梳理逻辑
        // 判断下表是否越界
        isIndexValid(index);
        Node<E> node = this.first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        final E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            first = new Node<E>(element, first);
        } else {
            Node<E> node = node(index - 1);
            node.next = new Node<E>(element, node.next);
        }
        size++;

    }

    @Override
    public E remove(int index) {
        isIndexValid(index);
        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> preNode = node(index - 1);
            node = preNode.next;
            node.next = preNode.next.next;
        }
        return node.element;

    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
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
        size = 0;
        first = null;
    }

}

