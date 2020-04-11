package com.vista.drill.tree;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树
 * 中序遍历时呈递增序列，结点元素必须具有可比性，元素不能为null
 * https://juejin.im/post/5dfc735ee51d45582d3405de#heading-6
 *
 * @author WenTingTing by 2020/4/7
 */
public class BinarySearchTree<E> implements BinarySearchTreeInterface<E> {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
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
    private int compare(E e1, E e2) {
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
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
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
    @Override
    public void add(E element) {
        // 判断元素是否为null
        elementNotNullCheck(element);

        // 如果根结点为空
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
            return;
        }
        // 如果根结点不为空
        Node<E> node = this.root;
        Node<E> nodeParent = null;// 带插入位置的父节点
        int compare = 0;
        // 获取待插入位置
        if (node != null) {
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
        size++;
    }

    /**
     * 删除结点
     * 如果度为1或0，直接删除结点
     * 如果度为2，则将其替换为后继结点，再删除前驱结点
     *
     * @param element
     */
    @Override
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
        // 如果结点度为1或0，先理清思路然后再归类整理，最后书写代码（此处的实现逻辑比视频中简单）
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

    }

    /**
     * 根据元素查找其所在结点
     *
     * @param element
     * @return
     */
    public Node<E> node(E element) {
        Node<E> node = this.root;
        int compare = 0;
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
        return node;
    }

    /**
     * 查找其是否包含该结点
     *
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element) {
        return node(element) != null;
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

interface BinarySearchTreeInterface<E> {
    // 元素的数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 清空所有元素
    void clear();

    // 添加元素
    void add(E element);

    // 删除元素
    void remove(E element);

    // 是否包含某元素
    boolean contains(E element);

}