package com.vista.drill.raise.d串;

/**
 * 1.字符串轮转
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 * <p>
 * 示例1:
 * 输入：s1 = "waterbottle", s2 = "erbottlewat"
 * 输出：True
 * 链接：https://leetcode-cn.com/problems/string-rotation-lcci
 * https://juejin.im/post/6844904149474082830
 *
 * @author Wen TingTing by 2020/11/12
 */
public class a_字符串轮转 {
    /**
     * 思路：利用旋转的特性，两个s1串收尾拼接后，则s2一定是s1+s1的子串
     * - s1+s1="waterbottlewaterbottle"
     * - s2="erbottlewat"
     * 总结：
     * 1.s1的任何旋转串都是s1+s1的子串
     * 2.s1与s2的长度必须相同
     * 注意：此处比较两个子串直接调用apI，也可以使用kmp算子实现
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        // 可以使用kmp算法
        return (s1 + s1).contains(s2);
    }
}
