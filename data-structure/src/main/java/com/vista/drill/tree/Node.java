package com.vista.drill.tree;

/**
 * @author WenTingTing by 2020/4/7
 */
public class Node<E> {
    E element;
    Node<E> parent;
    Node<E> left;
    Node<E> right;

    public Node(E element, Node<E> parent) {
        this.element = element;
        this.parent = parent;
    }

    public Boolean hasTwoChildren() {
        return this.left != null && this.right != null;
    }
}
