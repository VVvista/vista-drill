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
     * 删除结点之后调整平衡
     * 1.如果删除的是red结点，直接删除
     * 2.如果删除的是black且替换的为red的结点，则将red结点染黑（此时仅有red为black的唯一子节点一种情况）
     * 3.如果删除的是black叶子结点，则进行下溢平衡操作
     * 3.1如果sibling为black，且有子节点，则进行旋转操作，旋转之后的中心节点继承parent的颜色，旋转后的左右结点变为black
     * 3.2如果sibling为black，没有子节点，则parent下移与sibling合并成一个结点，此时可能导致parent原所在位置下溢，则再进行下溢平衡操作
     * 3.3如果sibling为red，先将sibling进行旋转，sibling染为black，parent染为red，再进行3.1或3.2的操作
     *
     * @param node
     */
    @Override
    public void afterRemove(Node<E> node) {
 /*       // 删除结点为red，直接返回;
        if (isRED(node))  return;
        //删除结点为black，替换结点为red
        if (isBlack(node)) {
            if (node.left != null) {
                black(node.left);
            } else {
                black(node.right);
            }
            return;
        }
*/

        // 删除结点为red，直接返回;
        // 删除结点为black，替换结点为red->此种情况下参数 node = 替换red结点
        // 此处进行了代码优化
        if (isRED(node)) {
            black(node);
            return;
        }

        // 删除节点为black且没有任何子节点，进行下溢调整操作
        // 兄弟结点
        Node<E> parent = node.parent;

        // 删除根结点
        if (parent == null) return;

        // 删除非根节点
        // 判断被删除节点是左节点还是右节点
        boolean left = parent.left == null;
        Node<E> sibling = left ? parent.right : parent.left;

        if (left) {// 被删除结点为左节点，兄弟结点为右节点
            // node  sibling
            if (isRED(sibling)) { // 兄弟节点为红色，此时兄弟节点一定有两个black子节点，否则破坏了红黑树性质4
                //parent左旋，RR
                rotateLeft(parent);
                black(sibling);
                red(parent);
                // 重新赋值sibling，此时的sibling必为black，再按照sibling为black进行操作
                sibling = node.right;
            }
            if (isBlack(sibling.left) && isBlack(sibling.right)) {// sibling不存在子节点，此时叶子结点必然为黑色
                // 兄弟节点没有子节点
                Boolean black = isBlack(parent);
                //parent与sibling合并
                black(parent);
                red(sibling);
                // 如果父节点为black，则可能造成下溢，所以需要再次循环判断
                if (black) {
                    afterRemove(parent);
                }
                // 如果父节点为red，则合并操作一定不会造成下溢
            } else {// 兄弟节点至少有一个结点，向兄弟节点借节点
                if (isRED(sibling.left)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                rotateLeft(parent);
                black(parent);
                black(sibling.right);
            }
        } else {// 被删除结点为右节点，兄弟结点为左节点
            // sibling  node
            if (isRED(sibling)) { // 兄弟节点为红色，此时兄弟节点一定有两个black子节点，否则破坏了红黑树性质4
                //parent右旋，LL
                rotateRight(parent);
                black(sibling);
                red(parent);
                // 重新赋值sibling，此时的sibling必为black，再按照sibling为black进行操作
                sibling = node.left;
            }
            //兄弟结点为black
            if (isBlack(sibling.left) && isBlack(sibling.right)) {// sibling不存在子节点，此时叶子结点必然为黑色
                // 兄弟节点没有子节点
                Boolean black = isBlack(parent);
                //parent与sibling合并
                black(parent);
                red(sibling);
                // 如果父节点为black，则可能造成下溢，所以需要再次循环判断
                if (black) {
                    afterRemove(parent);
                }
                // 如果父节点为red，则合并操作一定不会造成下溢
            } else {// 兄弟节点至少有一个结点，向兄弟节点借节点
                if (isRED(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                rotateRight(parent);
                black(parent);
                black(sibling.left);
            }
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