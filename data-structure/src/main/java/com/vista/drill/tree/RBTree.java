package com.vista.drill.tree;

import lombok.NoArgsConstructor;

import java.util.Comparator;

/**
 * 红黑树-平衡二叉树
 * <p>
 * 特有性质：
 * 1.节点必须是red或black
 * 2.根节点是black
 * 3.叶子节点是black（自添加的叶子节点，区别树的叶子节点概念）
 * 4.red节点的子节点是black节点
 * 5.任意节点到叶子节点的black数相同
 * <p>
 * <p>
 * 红黑树的添加、删除操作都是在操作完成后，再进行调整使之满足以上5条性质
 *
 * @author WenTingTing by 2020/4/16
 */
public class RBTree<E> extends BBSTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree(Comparator comparator) {
        super(comparator);
    }

    /**
     * 节点添加后的红黑颜色调整工作
     * 1.红黑树继承自二叉树，所以新结点必定是添加在叶子节点。默认新添加节点为红色节点。
     * 2.在讨论红黑树的添加时，将之转换成B树看待，从而分析其添加后平衡调整的情况
     * <p>
     * 分情况讨论：
     * 1.如果新节点的父节点是black：直接添加，无需操作
     * 2.如果新结点的父节点是red，且叔父节点不为red(即叔父节点不存在)：调整B树的节点
     * LL/RR: grand单旋转；parent=black，grand=red；parent变成子树的根结点
     * LR/RL：双旋转；grand=red，新节点=black；新结点变成子树的根结点，grand parent为子节点
     * 3.如果新结点的父节点是red，叔父节点是red(即叔父节点存在，此时B树节点元素个数已达上限)：添加新节点导致上溢
     * 3.1 parent/uncle=black；grand向上合并，grand初始为red，当做新节点进行处理
     * parent和uncle分列成两个B树子节点，grand向上合并可能再次发生上溢，此时当做新节点同样处理即可。
     * 若上溢一直持续到根结点，只需将新根结点=black，此时树的高度+1.
     *
     * @param node 添加的新结点
     */
    @Override
    public void afterAdd(Node<E> node) {
        afterAdd(node, RED);
    }

    /**
     * 添加新节点操作，默认节点颜色为red
     *
     * @param node
     * @param color
     */
    public void afterAdd(Node<E> node, Boolean color) {
        RBNode<E> parent = (RBNode<E>) node.parent;

        // node为根结点，直接染成黑色，并退出
        if (parent == null) {
            black(node);
            return;
        }

        // 情况1：parent为black ，直接添加
        if (isBlack(parent)) return;

        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        // 情况2：parent为red，uncle为red
        if (isRED(uncle)) {
            // parent、uncle染为black,parent.parent向上合并
            black(parent);
            black(uncle);
            afterAdd(grand);
            return;
        }

        // 情况3：parent为red，uncle不为red(叔父节点不存在或black)
        // 旋转+grand、parent/node染色
        if (grand.isLeftChild()) {
            if (parent.isLeftChild()) {
                //LL
                black(parent);
            } else {
                //LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (parent.isLeftChild()) {
                //RL
                black(node);
                rotateRight(parent);
            } else {
                //RR
                black(parent);
            }
            rotateLeft(grand);
        }

    }


    /**
     * 节点染色
     *
     * @param node
     * @param color
     */
    public Node<E> color(Node<E> node, Boolean color) {
        if (node != null) ((RBNode) node).color = color;
        return node;
    }

    /**
     * 将结点染成red
     *
     * @param node
     */
    public Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * 将结点染成black
     *
     * @param node
     */
    public Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * 判断节点颜色
     * 如果节点为null，默认无black；
     * 否则返回结点颜色
     *
     * @param node
     * @return
     */
    public boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode) node).color;
    }

    /**
     * 判断结点是否为black
     *
     * @param node
     * @return
     */
    public Boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断结点是否为black
     *
     * @param node
     * @return
     */
    public Boolean isRED(Node<E> node) {
        return colorOf(node) == RED;
    }

    /**
     * 红黑树节点
     * 添加节点颜色变量：为了方便利用定义为Boolean型
     *
     * @param <E>
     */
    private static class RBNode<E> extends Node<E> {
        Boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 查找兄弟结点
         *
         * @return
         */
        public Node<E> sibling() {
            if (parent == null) {
                return null;
            }
            return isLeftChild() ? parent.right : parent.left;
        }

    }
}