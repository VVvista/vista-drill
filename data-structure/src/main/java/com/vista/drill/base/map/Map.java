package com.vista.drill.base.map;

/**
 * 映射Map
 * 元素为(K,V)格式，k唯一且不能重复、不能为null，v可重复可为null
 * Map底层可以使用链表、二叉树等实现，因链表实现方式效率比较低，所以此处仅使用二叉搜索树实现
 * <p>
 * TreeMap：红黑树实现
 * 查询、添加、删除的时间复杂度为O(logn),且元素必须具有可比较性，不能为null
 * 树结点存储在红黑树的RBNode的基础上，将元素element替换为key、value
 *
 * @author Wen TingTing by 2020/4/18
 */
public interface Map<K, V> {

    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value); //添加元素

    V get(K key);

    V remove(K key);

    boolean containsKey(K key); //查找key是否存在

    boolean containsValue(V value); //查找value是否存在

    void traversal(Visitor<K, V> visitor); //元素遍历

    public static abstract class Visitor<K, V> {
        boolean stop;

        public abstract boolean visit(K key, V value);
    }
}
