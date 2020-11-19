package com.vista.drill.raise.d串;

/**
 * 4.d_翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 说明：
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * https://juejin.im/post/6844904151005003783#heading-8
 * 要求：删除字符串两端的空格，删减两个单词间多余的空格(2单词间仅一个空格)
 *
 * @author WenTingTing by 2020/11/19
 */
public class d_翻转字符串里的单词 {
    /**
     * 思路：
     * - 删除字符串两端及中间多余空格： 循环遍历字符，将符合要求字符往前移
     * 设置两个指针： i 循环遍历字符  cur 截止当前可以放字符的地方
     * 设置一个变量：space 前一个字符是否为空字符
     * - 对字符串逆序
     * - 对每个单词逆序：
     * 设置两个指针，分别指向单词首尾处的空格位置
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return null;
        char[] chars = s.toCharArray();
        // 消除多余空格
        // 记录前一个位置是否为空格,默认为true，防止首位置为空格
        boolean space = true;
        // 当前可以放字符的位置
        int cur = 0;
        for (int i = 0; i < chars.length; i++) {
            // 若字符不是空格，将字符移到cur位置，cur++
            if (chars[i] != ' ') {
                chars[cur++] = chars[i];
                space = false;
            } else if (!space) {
                chars[cur++] = ' ';
                space = true;
            }
        }
        // 判断cur-1位置是否为空格
        cur = space ? (cur - 1) : cur;
        // 翻转整个字符串
        reverse(chars, 0, cur);
        // 翻转每个单词
        int begin = -1;
        for (int end = 0; end < cur; end++) {
            if (chars[end] == ' ') {
                reverse(chars, begin+1, end);
                begin = end;
            }
        }
        reverse(chars, begin+1, cur);
        return new String(chars,0,cur);
    }

    /**
     * 翻转字符串 [begin,end)
     *
     * @param chars
     * @param begin
     * @param end
     */
    private void reverse(char[] chars, int begin, int end) {
        end--;
        while (begin < end) {
            char tmp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = tmp;
            begin++;
            end--;
        }
    }
}
