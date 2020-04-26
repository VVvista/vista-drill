package com.vista.drill.map;

import java.util.Objects;

/**
 * LinkedHashMap
 * 在HashMap的基础上维护元素的添加顺序，使得元素的比那里顺序与添加顺序相同
 * 节点添加：前指针、后指针
 *
 * @author WenTingTing by 2020/4/23
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {
    private LinkedHashNode<K, V> first;
    private LinkedHashNode<K, V> last;

    /**
     * 默认数组长度为16
     */
    public LinkedHashMap() {
        this.table = new LinkedHashNode[DEFAULT_CAPACITY];
    }

    /**
     * 建议数组长度为2^n
     *
     * @param length
     */
    public LinkedHashMap(int length) {
        this.table = new LinkedHashNode[length];
    }

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    /**
     * 添加元素
     * 在创建新节点的时候已经将元素与last相连，所以添加完成后无需再进行其他操作
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }


    /**
     * 创建新结点
     * 新节点添加到链表的尾部
     *
     * @param key
     * @param value
     * @param parent
     * @return
     */
    @Override
    protected HashNode<K, V> createNode(K key, V value, HashNode<K, V> parent) {
        LinkedHashNode<K, V> node = new LinkedHashNode<>(key, value, parent);
        if (first == null) {//没有头节点
            first = node;
            last = node;
        } else {//有头节点
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    @Override
    public V get(K key) {
        return super.get(key);
    }

    @Override
    public V remove(K key) {
        return super.remove(key);
    }

    /**
     * 调整删除节点的前指针、后指针
     *
     * @param willNode    外界想删除的结点
     * @param removedNode 红黑树中实际删除的结点
     */
    @Override
    protected void afterRemove(HashNode<K, V> willNode, HashNode<K, V> removedNode) {
        LinkedHashNode<K, V> node1 = (LinkedHashNode) willNode;
        LinkedHashNode<K, V> node2 = (LinkedHashNode) removedNode;

        if (node1 != node2) {// 删除结点度为2
            LinkedHashNode<K, V> tmp = node1;
            node1.prev = node2.prev;
            node2.prev = tmp.prev;

            node1.next = node2.next;
            node2.next = tmp.next;
        }


    }

    /**
     * 查找key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(K key) {
        return super.containsKey(key);
    }

    /**
     * 查找value是否存在
     * 可以采用HashMap的查询逻辑，也可以使用 first->last 循环遍历比较每个元素
     *
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(V value) {
        if (first == null) return false;
        LinkedHashNode<K, V> node = this.first;
        while (node != null) {
            if (Objects.equals(value, node.value)) return true;
            node = node.next;
        }
        return false;
    }


    /**
     * 遍历元素
     * 元素的输出顺序与添加顺序相同
     *
     * @param visitor
     */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (first == null) return;
        LinkedHashNode<K, V> node = this.first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) return;
            node = node.next;
        }
    }


    protected static class LinkedHashNode<K, V> extends HashNode<K, V> {
        private LinkedHashNode<K, V> prev;
        private LinkedHashNode<K, V> next;

        public LinkedHashNode(K key, V value, HashNode<K, V> parent) {
            super(key, value, parent);
        }
    }
}
