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

    /**
     * 判断该结点是否为父结点的左子节点
     *
     * @return
     */
    public boolean isLeftChild() {
        return parent != null && this == parent.left;
    }

    /**
     * 判断该结点是否为父结点的右子节点
     *
     * @return
     */
    public boolean isRightChild() {
        return parent != null && this == parent.right;
    }
}
