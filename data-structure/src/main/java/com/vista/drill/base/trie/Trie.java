package com.vista.drill.base.trie;

import java.util.HashMap;

/**
 * Trie
 * 称为：字典树、前缀树、单词查找树
 * 需求：如何判断一堆不重复的字符串是否以某个前缀开头
 * Trie搜索字符串的效率跟字符串的长度有关
 * 缺点：需要耗费大量的内存，有待改进
 *
 * <p>
 * 注意：
 * 1.默认Trie存储和查找的为字符串，每个节点存储Character，且每个字符串的字后一个字符节点，存储该字符串的value值
 * 2.节点是否为字符串的结尾使用 Boolean Word 区分
 * 3.节点可以有n个子节点，使用HashMap<Character，TrieNode>存储子节点
 * 4.为了应对字符串删除，节点类添加父节点 parent、字符 char 字段
 *
 * @author WenTingTing by 2020/4/26
 */
public class Trie<V> implements TrieInterface<V> {
    private int size;
    private TrieNode<V> root;


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
     * 判断是否包含字符串str
     *
     * @param str
     * @return
     */
    @Override
    public boolean contains(String str) {
        return node(str) != null;
    }

    /**
     * 获取字符串str的value值
     * 若不存在则返回null
     *
     * @param str
     * @return
     */
    @Override
    public V get(String str) {
        TrieNode<V> node = node(str);
        return node != null ? node.value : null;
    }

    /**
     * 根据key查找最后字符所在的节点
     * 1.如果key不存在，返回null
     * 2.如果key存在，返回最后字符节点
     * <p>
     * 可以根据返回值判断其是否为单词结尾，或者判断是否存在该单词或前缀
     *
     * @param key
     * @return
     */
    private TrieNode<V> node(String key) {
        keyCheck(key);
        TrieNode<V> node = this.root;
        for (int i = 0; i < key.length(); i++) {
            // 若节点或子节点为null，直接返回
            if (node == null || node.child == null || node.child.isEmpty()) return null;
            node = node.child.get(key.charAt(i));
        }
        return node;
    }

    /**
     * 判断key是否有效
     *
     * @param key
     */
    private void keyCheck(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }

    /**
     * 添加字符串str及其value
     * 1.如果str存在，覆盖value值
     * 2.如果不存在，则添加
     *
     * @param str
     * @param value 返回添加之前的value
     * @return
     */
    @Override
    public V add(String str, V value) {
        keyCheck(str);
        size++;

        if (root == null) {
            root = new TrieNode<>(null);
        }

        TrieNode<V> node = this.root;
        for (int i = 0; i < str.length(); i++) {

            HashMap<Character, TrieNode<V>> child = node.child;
            // 如果child不存在，创建child
            child = child != null ? child : new HashMap<>();

            // child查找是否存在对应子节点
            char c = str.charAt(i);
            TrieNode<V> childNode = child.get(c);
            // 如果子节点不存在，则创建新节点，并添加进child
            childNode = childNode != null ? childNode : new TrieNode<>(node);
            // 将结点添加进child
            node.child.put(c, childNode);
            // 如果子节点存在，继续循环
            node = childNode;
        }
        V oldValue = node.value;
        node.word = true;
        node.value = value;
        return oldValue;
    }

    /**
     * 删除字符串str
     * 1.如果存在字符串则删除
     * 2.如果不存在，则返回
     * <p>
     * 操作：
     * 1.先找出字符串最后一个结点，若不存在则直接返回null
     * 2.若结点不存在或不为串尾，则返回null
     * 3.若结点存在子节点，则修改串尾标志，清空value，返回
     * 4.向上查看父节点，父节点删除子节点，若仍存在子节点或父节点为串尾，则停止删除
     * 5.若父节点除了子节点之外无其他子节点则删除
     * 6.直至情况4或父节点为null，循环结束
     *
     * @param str
     * @return 删除前value值
     */
    @Override
    public V remove(String str) {
        TrieNode<V> node = node(str);
        // 若结点不存在或不为串尾，则返回null
        if (node == null || !node.word) return null;
        size--;

        V oldValue = node.value;
        if (node.child != null && !node.child.isEmpty()) {
            node.word = false;
            node.value = null;
            return oldValue;
        }

        TrieNode<V> parent = node.parent;
        while (parent != null) {
            parent.child.remove(node.character);
            if (!parent.word || !parent.child.isEmpty()) break;
            parent = parent.parent;
        }

        return oldValue;
    }

    /**
     * 前缀搜索
     * 1.如果存在前缀prefix，返回true
     * 2.否则返回false
     *
     * @param prefix
     * @return
     */
    @Override
    public boolean startWith(String prefix) {
        return node(prefix) != null;
    }

    private static class TrieNode<V> {
        V value;
        Character character;
        TrieNode<V> parent;
        HashMap<Character, TrieNode<V>> child;
        boolean word;

        public TrieNode(TrieNode<V> parent) {
            this.parent = parent;
        }
    }

}
