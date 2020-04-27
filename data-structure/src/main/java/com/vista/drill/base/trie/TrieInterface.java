package com.vista.drill.base.trie;

/**
 * Trie
 * 称为：字典树、前缀树、单词查找树
 * 需求：如何判断一堆不重复的字符串是否以某个前缀开头
 * Trie搜索字符串的效率跟字符串的长度有关
 * 缺点：需要耗费大量的内存，有待改进
 *
 * @author WenTingTing by 2020/4/26
 */
public interface TrieInterface<V> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(String str); // 是否包含字符串str单词

    V get(String str); // 获取字符串str单词的value值

    V add(String str, V value);// 添加字符串单词str

    V remove(String str);// 删除字符串单词str

    boolean startWith(String prefix);// 判断是否存在以prefix开头的单词

}
