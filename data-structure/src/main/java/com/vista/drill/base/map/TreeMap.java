package com.vista.drill.base.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TreeMap
 * 红黑树实现映射
 * 查询、添加、删除的时间复杂度为O(logn),且元素必须具有可比较性，不能为null
 * 树结点存储在红黑树的RBNode的基础上，将元素element替换为key、value
 * <p>
 * https://juejin.im/post/5e4d48e2e51d4526c80e987b#heading-0
 *
 * @author Wen TingTing by 2020/4/18
 */
public class TreeMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Comparator<K> comparator;
    private RBMapNode root;
    private int size;

    public TreeMap(Comparator<K> comparator) {
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
        root = null;
        size = 0;
    }

    private void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable) k1).compareTo(k2);
    }

    /**
     * 添加元素
     * 如果key存在则替换key value
     * 否则进行添加
     *
     * @param key
     * @param value
     * @return 返回未添加前key对应的value
     */
    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key); // key不能为空

        // 添加第一个节点
        if (root == null) {
            root = new RBMapNode<K, V>(key, value, null);
            size++;

            // 新添加节点之后的处理
            afterPut(root); //修复红黑树性质
            return null;
        }

        // 添加的不是第一个节点
        // 找到父节点
        RBMapNode<K, V> parent = root;
        RBMapNode<K, V> node = root;
        int cmp = 0;
        do {
            cmp = compare(key, node.key); //比较传入的key与原节点key
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                node.key = key; //覆盖key
                V oldValue = node.value;
                node.value = value; //覆盖value
                return oldValue; //返回原节点值
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        RBMapNode<K, V> newNode = new RBMapNode<K, V>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterPut(newNode);
        return null; //新添加节点，返回空。
    }

    private void afterPut(RBMapNode<K, V> node) {

    }

    /**
     * 查询元素
     *
     * @param key
     * @return 返回value
     */
    @Override
    public V get(K key) {
        RBMapNode<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    private RBMapNode<K, V> node(K key) {
        RBMapNode<K, V> node = root;
        while (node != null) {
            int cmp = compare(key, node.key);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else { // cmp < 0
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 删除元素
     * 先根据key查找到结点，然后进行删除结点操作
     *
     * @param key
     * @return 返回value
     */
    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    private V remove(RBMapNode<K, V> node) {
        if (node == null) return null;

        size--;

        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            RBMapNode<K, V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        RBMapNode replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }
            // 删除节点之后的处理
            afterRemove(node);
        }
        return oldValue;
    }

    /**
     * 查找后继结点
     * 操作与红黑树相同，此处不再累述
     *
     * @param node
     * @return
     */
    private RBMapNode<K, V> successor(RBMapNode<K, V> node) {
        return node;
    }

    /**
     * 元素添加后调整平衡
     * 操作与红黑树相同，此处不再累述
     */
    private void afterRemove(RBMapNode<K, V> node) {

    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    /**
     * 因为不要求V具有可比较性，所以需要遍历所有结点，并依次判断value值
     *
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(V value) {
        if (root == null) return false;

        //层序遍历
        Queue<RBMapNode<K, V>> queue = new LinkedList<RBMapNode<K, V>>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            RBMapNode<K, V> node = queue.poll();
            if (valEquals(value, node.value)) return true;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    private boolean valEquals(V v1, V v2) {
        return v1 == null ? v2 == null : v1.equals(v2);
    }

    /**
     * 中序遍历
     *
     * @param visitor
     */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        traversal(root, visitor);
    }

    private void traversal(RBMapNode<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) return;

        traversal(node.left, visitor);
        if (visitor.stop) return;
        visitor.visit(node.key, node.value);
        traversal(node.right, visitor);
    }

    private static class RBMapNode<K, V> {
        private K key;
        private V value;
        private RBMapNode parent;
        private RBMapNode left;
        private RBMapNode right;
        private boolean color = RED;

        RBMapNode(K key, V value, RBMapNode parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public RBMapNode sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }
}
