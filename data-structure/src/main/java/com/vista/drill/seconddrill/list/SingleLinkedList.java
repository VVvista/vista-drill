package com.vista.drill.seconddrill.list;

/**
 * 单向链表
 *
 * @author WenTingTing by 2020/5/12
 */
public class SingleLinkedList<E> implements List<E> {
    private int size;
    private Node<E> first;

    private class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != DEFAULT_BOUNDARY_VALUE;
    }

    @Override
    public void add(E element) {
        if (size == 0) {
            first = new Node<>(element, first);
        } else {
            Node<E> node = node(size - 1);
            node.next = new Node<>(element, null);
        }
        size++;
    }


    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 根据索引查找node结点
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = this.first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E OldElement = node.element;
        node.element = element;
        return OldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheck(index);
        if (index == 0) {
            first = new Node<>(element, first);
        } else {
            Node<E> node = node(index - 1);
            node.next = new Node<>(element, node.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> removeNode= first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> node = node(index - 1);
            removeNode = node.next;
            node.next = removeNode.next;
        }
        size--;
        return removeNode.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = this.first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return DEFAULT_BOUNDARY_VALUE;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }


    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }
}
