package com.vista.drill.base.tree;

import java.util.Comparator;

/**
 * 平衡二叉搜索树
 *
 * @author WenTingTing by 2020/4/16
 */
public class BBSTree<E> extends BinarySearchTree<E> {

    public BBSTree(Comparator comparator) {
        super(comparator);
    }

    /**
     * 结点进行右旋转
     * 结点进行右旋转，只有可能是grand与其左子节点parent进行交换
     * 1.将parent的右子树child变成grand的左子树：grand.left=parent.right
     * 2.将grand变成parent的右子树：parent.right=grand
     * 3.将parent变成grand的父节点的子树：grand.XX=parent
     * 4.调整child parent grand的父节点
     * child.parent=grand;
     * parent.parent=grand.parent;
     * grand.parent=parent;
     *
     * @param grand
     */
    public void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        // 交换左右子树
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 结点进行左旋转
     * 结点进行左旋转，只有可能是grand与其右子节点parent进行交换
     * 1.将parent的左子树child变成grand的右子树：grand.right=parent.left
     * 2.将grand变成parent的左子树：parent.left=grand
     * 3.将parent变成grand的父节点的子树：grand.XX=parent
     * 4.调整child parent grand的父节点
     * child.parent=grand;
     * parent.parent=grand.parent;
     * grand.parent=parent;
     *
     * @param grand
     */
    public void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        // 交换左右子树
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    public void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 将parent赋值给grand父节点的左右子树
        if (grand.parent == null) {
            root = parent;
        } else if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else {
            grand.parent.right = parent;
        }

        // 赋值父节点
        parent.parent = grand.parent;
        grand.parent = parent;
        if (child != null) child.parent = grand;
    }
}
