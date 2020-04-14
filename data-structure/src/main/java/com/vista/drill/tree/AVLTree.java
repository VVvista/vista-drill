package com.vista.drill.tree;

import java.util.Comparator;

/**
 * 平衡二叉树-AVL树
 * <p>
 * 特有概念：
 * 平衡因子：某结点的左右子树的高度差
 * <p>
 * 特点：
 * 1.AVL树是特殊的二叉搜索树，|平衡因子|<=1
 * 2.AVL树的在添加、删除操作之后再想方式调整二叉树恢复平衡（即减少树的高度）
 * 3.AVL树的搜索、添加、删除的时间复杂度：o(logn)
 * 4.添加操作可能导致所有祖先节点都失衡；父节点、非祖先节点都不可能失衡（因为结点一定是添加在父节点左右子树的，而祖父节点整体高度可能加1，导致失衡，连带祖父节点向上的父节点一起失衡）
 * 5.删除可能导致父节点或祖先节点失衡，除父节点或祖先节点外所有结点都不可能失衡
 * （因为删除结点可能导致父节点的整体高度不变，但平衡因子改变，所以父节点失衡，祖先节点不会失衡。）
 * <p>
 * https://juejin.im/post/5e057217f265da33d912ecfe
 *
 * @author WenTingTing by 2020/4/13
 */
public class AVLTree<E> extends BinarySearchTree<E> {


    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 添加完新结点之后，调整树的平衡，
     * 1.从新结点的父结点开始，向上查找树的父节点的祖父结点是否失衡，如果失衡便重新调整树的结点直至平衡。
     * 2.调整完之后便退出。
     * 原理：添加新结点后祖父结点失衡，肯定是因为高度加1，导致祖父结点及祖父节点的父节点们都失衡；
     * 所以从新结点开始向上找到第一个失衡的祖父节点并调整平衡完成后，树的高度就减少了，必定是减少1，从而使得该祖父节点的高度恢复到之前的高度
     * 所以该祖父结点向上的父节点们的高度便自动调整为了之前的高度，即平衡因子恢复到之前的数值。所以只需对第一个失衡祖父节点调整平衡即可
     * <p>
     * 添加失衡后的调整主要分为四种：
     * LL:右旋转
     * RR：左旋转
     * LR：左旋转然后右旋转
     * RL：右旋转然后左旋转
     *
     * @param node 添加的新结点
     */
    @Override
    public void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            // 判断是否平衡
            if (isBalanced(node)) {
                // 如果平衡，更新结点的高度
                updateHeight(node);
            } else {
                // 如果失衡，调整平衡
                rebalanced(node);
                // 树恢复平衡后退出
                break;
            }
        }
    }

    /**
     * 判断结点是否平衡
     * 即|平衡因子|<=1
     *
     * @param node
     * @return
     */
    public boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode) node).balanceFactor()) <= 1;
    }

    /**
     * 更新树的高度
     *
     * @param node
     * @return
     */
    public void updateHeight(Node<E> node) {
        ((AVLNode) node).updateHeight();
    }

    /**
     * 调整失衡结点
     * 1.如果结点失衡，则假设该失衡结点为grand
     * 2.查找grand左右子树中高度最高的结点记为parent
     * 3.查找parent左右子树中高度最高的结点记为node
     * 则 grand -> parent -> node的位置决定了结点的失衡方式，从而根据失衡方式进行调整即可
     *
     * @param grand
     */
    public void rebalanced(Node<E> grand) {
        // 查找grand高度最大的子树parent
        AVLNode<E> parent = (AVLNode<E>) ((AVLNode<E>) grand).tallerChild();
        // 查找parent高度最大的子树node
        AVLNode<E> node = (AVLNode<E>) parent.tallerChild();

        // 判断parent是否为grand的左子树
        if (parent.isLeftChild()) {
            // 判断node是否为parent的左子树
            if (node.isLeftChild()) {
                // LL 类型，grand进行右旋转
                rotateRight(grand);
            } else {
                // LR 类型
                // parent进行左旋转
                rotateLeft(parent);
                // grand进行右旋转
                rotateRight(grand);
            }
        } else {
            // parent为grand的右子树
            // 判断node是否为parent的左子树
            if (node.isLeftChild()) {
                // RL类型
                // parent右旋转
                rotateRight(parent);
                // grand左旋转
                rotateLeft(grand);
            } else {
                // node为parent的左子树
                // RR类型，grand左旋转
                rotateLeft(grand);
            }
        }
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
     * 5.更新树的高度
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
     * 5.更新树的高度
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
        } else if (((AVLNode<E>) grand).isLeftChild()) {
            grand.parent.left = parent;
        } else {
            grand.parent.right = parent;
        }

        // 赋值父节点
        parent.parent = grand.parent;
        grand.parent = parent;
        if (child != null) child.parent = grand;

        // 更新结点的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 删除某结点之后，调整树的平衡
     * 删除某个结点，只可能是父节点或祖先节点中的其中一个失衡，除该失衡结点外的其他结点都不失衡
     * （因为删除某个结点，只有在父节点或祖先结点的所在的子树，其高度最低的子树减少了树的高度时出现失衡，导致父节点或祖先节点失衡，
     * 但因其最大高度没有变化，所以对外的高度没变，即除了该结点外的所有祖先结点平衡性均没有变化，
     * 所以先调整该失衡结点的平衡即可。
     * 但在调整完该结点平衡之后(可能会导致该结点对外的高度减1)，导致祖先节点们出现失衡的情况，因此需要继续向上查找失衡结点并调整，直至整个树平衡）
     *
     * @param node
     */
    public void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            //判断结点是否平衡
            if (isBalanced(node)) {
                // 如果平衡，更新结点高度
                updateHeight(node);
            } else {
                // 如果失衡，调整结点平衡
                rebalanced(node);
            }
        }
    }


    /**
     * AVL结点类
     * 继承Node类，添加height字段
     *
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {
        // 树的高度
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 计算结点的平衡因子
         * 左子节点的高度-右子节点的高度
         *
         * @return 平衡因子
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            int rightHeight = left == null ? 0 : ((AVLNode) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新树的高度
         * 1+左右子树的最大高度
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            int rightHeight = left == null ? 0 : ((AVLNode) right).height;
            this.height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 查找结点高度最大的子节点
         * 如果高度相同则返回与结点与父结点相同的方向
         *
         * @return
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            int rightHeight = left == null ? 0 : ((AVLNode) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
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


}
