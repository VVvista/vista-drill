package com.vista.drill.base.tree;


import java.util.Comparator;

/**
 * 二叉搜索树
 * 中序遍历时呈递增序列，结点元素必须具有可比性，元素不能为null
 * https://juejin.im/post/5dfc735ee51d45582d3405de#heading-6
 *
 * @author WenTingTing by 2020/4/7
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    public BinarySearchTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 添加元素
     * 注意：
     * 1.待添加元素不能为null
     * 2.当根结点为null时，直接创建新结点赋值给root
     * 3.查找待插入位置的父节点，并进行赋值
     * 4.元素相等时进行值替换，因为E为引用类型时，可能地址不同
     *
     * @param element 待添加元素，element不能为null
     */
    public void add(E element) {
        // 判断元素是否为null
        elementNotNullCheck(element);

        // 如果根结点为空
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
            // 添加新结点后调整树的平衡
            afterAdd(root);
            return;
        }
        // 如果根结点不为空
        Node<E> node = this.root;
        Node<E> nodeParent = null;// 带插入位置的父节点
        int compare = 0;
        // 获取待插入位置
        while (node != null) {
            // 判断元素与结点值大小
            compare = compare(element, node.element);
            nodeParent = node;
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                // 相等
                node.element = element;
                return;
            }
        }
        // 插入新结点
        Node<E> newNode = new Node<E>(element, nodeParent);
        if (compare > 0) {
            nodeParent.right = newNode;
        } else {
            nodeParent.left = newNode;
        }
        // 添加新结点后调整树的平衡
        afterAdd(newNode);
        size++;
    }

    /**
     * 调整树的平衡
     * 新结点一定是叶子结点，所以添加完新结点之后再进行调整树的平衡
     * 此方法主要是在AVl树和红黑树中重写调整平衡的逻辑
     * 此类：二叉搜索树中无需调整树的平衡
     *
     * @param node 添加的新结点
     */
    public void afterAdd(Node<E> node) {

    }

    /**
     * 删除结点
     * 如果度为1或0，直接删除结点
     * 如果度为2，则将其替换为后继结点，再删除前驱结点
     *
     * @param element
     */
    public void remove(E element) {
        remove(node(element));
    }

    public void remove(Node<E> node) {
        // 如果结点为null，直接返回
        if (node == null) {
            return;
        }
        // 如果结点度为2，将结点值替换为后继结点值
        if (node.hasTwoChildren()) {
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;
        }
/*        // 如果结点度为1或0，先理清思路然后再归类整理，最后书写代码（此处的实现逻辑比视频中简单）
        Node<E> child = node.left != null ? node.left : node.right; // 隐藏 left==right==null,此时child=null，表明结点度为0
        if (node.parent == null) {
            root = child;
        } else if (node == node.parent.left) {
            if (child != null) {
                child.parent = node.parent;
            }
            node.parent.left = child;
        } else {
            if (child != null) {
                child.parent = node.parent;
            }
            node.parent.right = child;
        }
        // 删除某个结点后，调整平衡
        afterRemove(child);*/
        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        Node<E> parent = node.parent;
        if (replacement != null) { // 度为1
            replacement.parent =parent; // 修改parent
            // 更改parent的left、right的指向
            if (parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node.isLeftChild()) {
                parent.left = replacement;
            } else {// node == node.parent.right
                parent.right = replacement;
            }
            afterRemove(replacement);
        } else if (parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else {// node是叶子节点，但不是根节点
            if (node.isLeftChild()) {
                parent.left = null;
            } else {// node == node.parent.right
                parent.right = null;
            }
            afterRemove(node);
        }


    }

    /**
     * 删除某个结点之后，对结点进行平衡调整
     * 此方法是在AVL树或红黑树中重写实现逻辑
     * 二叉搜索树种无需实现该逻辑
     *
     * @param node
     */
    public void afterRemove(Node<E> node) {
    }

    /**
     * 根据元素查找其所在结点
     *
     * @param element
     * @return
     */
    public Node<E> node(E element) {
        Node<E> node = this.root;
        int compare;
        while (node != null) {
            compare = compare(element, node.element);
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 查找其是否包含该结点
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return node(element) != null;
    }

}
