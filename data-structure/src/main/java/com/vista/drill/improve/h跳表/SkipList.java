package com.vista.drill.improve.h跳表;

import java.util.Comparator;

/**
 * 1.跳表
 * https://blog.csdn.net/weixin_43734095/article/details/105780061
 * 在有序列表的基础上添加跳跃功能
 *
 * @author WenTingTing by 2020/9/27
 */
public class SkipList<K, V> {
    // 首节点
    Node<K, V> first;

    // 仿照Redis的做法, 维护一个概率值.主要用于产生节点的随机层数
    private static final double P = 0.25;
    // 最大层数
    int MAX_LEVEL = 32;

    // 有效层数
    int level;

    //比较器
    private Comparator<K> comparator;

    //构造器
    SkipList(Comparator comparator) {
        this.comparator = comparator;
        // 头节点的高度必须是跳表的最高层数(非有效层数), 为了后面插入新节点做准备
        first = new Node<>(null, null, MAX_LEVEL);
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k2, k2) : ((Comparable) k1).compareTo(k2);
    }

    /**
     * 跳表的搜索
     *
     * @param key
     * @return
     */
    public V get(K key) {
        if (key == null) return null;

        Node node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.next[i] != null && (cmp = compare((K) node.next[i].key, key)) < 0) {
                node = node.next[i];
            }
            if (cmp == 0) {
                return (V) node.next[i].value;
            }
        }
        return null;
    }

    /**
     * 计算节点的随机层数
     *
     * @return
     */
    private int randomLevel() {
        int level = 1;
        while (level <= MAX_LEVEL && Math.random() < P) {
            level++;
        }
        return level;
    }

    /**
     * 添加 节点
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        int nodeLevel = randomLevel();
        Node newNode = new Node(key, value, nodeLevel);
        Node node = first;
        for (int i = nodeLevel - 1; i >= 0; i--) {

            if (i >= level) {
                first.next[i] = newNode; // 让头节点指向新节点(头节点创建时是最高层)
            } else {
                int cmp = -1;
                while (node.next[i] != null && compare((K) node.next[i].key, key) < 0) {
                    node = node.next[i];
                }
                if (cmp == 0) {
                    // 节点存在，修改节点value值
                    V oldValue = (V) node.next[i].value;
                    node.next[i].value = value;
                    return oldValue;
                }
                // 修改指针
                newNode.next[i] = node.next[i];// 让新节点的后继节点指向(之前
                node.next[i] = newNode;// 让前驱节点指向新节点
            }
        }
        level = Math.max(nodeLevel, level);// 计算跳表的最终层数(更新有效层数)
        return null;// 之前不存在该节点, 返回null
    }

    /**
     * 删除节点
     *
     * @param key
     * @return
     */
    public V remove(K key) {
        Node removeNode = null;
        Node node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.next[i] != null && (cmp = compare((K) node.next[i].key, key)) < 0) {
                node = node.next[i];
            }
            if (cmp == 0) {// 查找到待删除节点
                removeNode = node.next[i];
                node.next[i] = removeNode.next[i];
            }
        }

        // 更新跳表层数
        while (level > 0 && first.next[level - 1] == null) {
            level--;
        }
        return removeNode == null ? null : (V) removeNode.value;

    }


    private static class Node<K, V> {
        K key;
        V value;
        // 该节点的不同层指向的下一节点
        Node<K, V>[] next;

        Node(K key, V value, int len) {
            this.key = key;
            this.value = value;
            this.next = new Node[len];
        }


    }
}
