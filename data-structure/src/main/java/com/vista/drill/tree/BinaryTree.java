package com.vista.drill.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 *
 * @author WenTingTing by 2020/4/13
 */
public class BinaryTree<E> {
    protected int size;
    protected Node<E> root;
    protected Comparator<E> comparator;

    public BinaryTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * 比较结点值大小
     * 如果外部传递了比较器，则使用比较器比较大小；
     * 否则将E强转为Comparable类调用compareTo方法比较，如果E没有实现Comparable接口会强转报错
     * 注意：二叉搜索树的元素E必须支持比较
     *
     * @param e1
     * @param e2
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    protected int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    /**
     * 判断元素是否为null
     *
     * @param element
     */
    protected void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 递归实现前序遍历
     *
     * @param node
     */
    private void preorderTraversal(Node<E> node) {
        // 退出条件
        if (node == null) {
            return;
        }
        // 打印当前值
        System.out.println(node.element);
        // 遍历左子树
        preorderTraversal(node.left);
        // 遍历右子树
        preorderTraversal(node.right);
    }

    /**
     * 递归实现中序遍历
     *
     * @param node
     */
    private void inorderTraversal(Node<E> node) {
        // 退出条件
        if (node == null) {
            return;
        }
        // 遍历左子树
        preorderTraversal(node.left);
        // 打印当前值
        System.out.println(node.element);
        // 遍历右子树
        preorderTraversal(node.right);
    }

    /**
     * 递归实现后序遍历
     *
     * @param node
     */
    private void postorderTraversal(Node<E> node) {
        // 退出条件
        if (node == null) {
            return;
        }
        // 遍历左子树
        preorderTraversal(node.left);
        // 遍历右子树
        preorderTraversal(node.right);
        // 打印当前值
        System.out.println(node.element);
    }

    /**
     * 层次遍历
     */
    public void levelOrderTranversal() {
        Node<E> node = this.root;
        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        // 根结点入队列
        queue.offer(node);
        while (!queue.isEmpty()) {
            // 打印结点值
            System.out.println(node.element);
            // 弹出队列头结点
            Node<E> poll = queue.poll();
            // 获取左节点
            Node<E> left = poll.left;
            // 获取右节点
            Node<E> right = poll.right;
            // 如果结点不为null，入队列
            if (left != null) {
                queue.offer(poll.left);
            }
            if (right != null) {
                queue.offer(poll.right);
            }
        }
    }

    /**
     * 查找结点的前驱结点
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 如果结点有左子树，则找左子树的最右结点
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        // 如果结点不存在左子树，则向上寻找父节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 查找结点的后继结点
     *
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 如果结点有右子树，则找右子树的最左结点
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        // 如果结点不存在左子树，则向上寻找父节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;

    }

}
