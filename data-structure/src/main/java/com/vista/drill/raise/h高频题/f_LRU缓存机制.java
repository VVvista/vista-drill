package com.vista.drill.raise.h高频题;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * - void put(int key, int value) 如果关键字已经存在，则变更其数据值；
 * 如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 链接：https://leetcode-cn.com/problems/lru-cache
 *
 * @author WenTingTing by 2020/12/21
 */
public class f_LRU缓存机制 {
    /**
     * LRU缓存机制： 最近最少使用、最近最久未使用
     * 思路： 使用hash + 双向链表
     * - hash: <key, Node>
     * - 双向链表： Node<key,value, prev, next>
     * -- 用于实现key的排序
     * 1.若查询key 或添加某个key，将key添加进链表首位置
     * 2.链表末尾位置即为最久未使用
     */

    // hash
    Map<Integer, Node> map;
    // 链表虚拟头指针
    Node first;
    // 链表虚拟尾指针
    Node last;
    // hash 容量
    int capacity;
    int size = 0;

    public f_LRU缓存机制(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>(capacity);
        this.first = new Node();
        this.last = new Node();
        first.next = last;
        last.prev = first;
    }

    /**
     * 查询key
     * 1.若key存在返回 value ，同时将key所在node放在链表首位
     * 2.若key不存在，返回-1
     *
     * @param key
     * @return
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        // 将key node放在链表首位
        // 先删除node原位置链表指针
        removeNode(node);
        // 在将node添加到首位
        addFirst(node);
        return node.value;

    }

    /**
     * 将key node放在链表首位： 即将node添加到首位
     *
     * @param node
     */
    private void addFirst(Node node) {
        // 修改链表指针
        // 先修改node右边指针关系
        first.next.prev = node;
        node.next = first.next;
        // 再修改node左边指针关系
        first.next = node;
        node.prev = first;
    }

    /**
     * 删除node：即更改node前后节点指针
     *
     * @param node
     */
    private void removeNode(Node node) {
        // 修改链表指针
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 添加key
     * 1.若key存在，将node value替换为value；再将node放在链表首位
     * 2.若key不存在，检查capacity 是否已满
     * - 若capacity已满，则删除最后节点，再将新结点添加到链表首位
     * - 若capacity已满，直接将新结点添加到链表首位
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {

        Node node = map.get(key);
        // node 存在
        if (node != null) {
            // 替换value值
            node.value = value;
            // 删除原位置
            removeNode(node);
            // 移动到首位
            addFirst(node);
        } else {
            // node不存在
            node = new Node(key, value);

            // 容量已满，删除尾部节点
            if (size == capacity) {
                map.remove(last.prev.key);
                removeNode(last.prev);
                size--;
            }
            // 将新结点添加到首位
            addFirst(node);
            map.put(key, node);
            size++;
        }
    }

    /**
     * 链表节点类
     */
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */