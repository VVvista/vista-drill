package com.vista.leetcode.list.string;


import com.vista.drill.stack.Stack;

/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 * <p>
 * 执行效率： reverseWords2> reverseWords1> reverseWords3> reverseWords4
 *
 * @author WenTingTing by 2020/4/2
 */
public class E_557_反转字符串中的单词III {
    /**
     * 使用split+reverse方法
     * 将输入字符串中按照空白字符串分开，
     * 然后把所有单词放到一个字符串列表中，
     * 然后逐一遍历每一个字符串并把反转结果连接起来。
     * 最后，我们将删除了额外空白字符的字符串返回。
     *
     * @param s
     * @return
     */
    public String reverseWords1(String s) {
        // 字符串分割
        String[] split = s.split(" ");
        StringBuffer buffer = new StringBuffer();
        for (String str : split) {
            char[] chars = str.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                buffer.append(chars[i]);
            }
            buffer.append(" ");
        }
        return buffer.substring(0, buffer.length() - 1);
    }


    /**
     * 方法1的精简版：使用自带的reverse方法
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        // 字符串分割
        String[] split = s.split(" ");
        StringBuffer buffer = new StringBuffer();
        for (String str : split) {
            buffer.append(new StringBuffer(str).reverse()).append(" ");
        }

        return buffer.toString().trim();
    }

    /**
     * 使用StringBuffer和reverse
     *
     * @param s
     * @return
     */
    public String reverseWords3(String s) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer tmp = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                buffer.append(tmp.reverse()).append(c);
                tmp.setLength(0);
            } else {
                tmp.append(c);
            }
        }
        buffer.append(tmp.reverse());
        return buffer.toString();
    }

    /**
     * 利用栈存储字符
     * 但是此方法效率比较低，主要是学习利用栈反转字符串
     *
     * @param s
     * @return
     */
    public String reverseWords4(String s) {
        // 字符串分割
        String[] split = s.split(" ");
        Stack<Character> stack = new Stack<Character>();
        StringBuffer buffer = new StringBuffer();

        for (String str : split) {
            for (int i = 0; i < str.length(); i++) {
                stack.push(Character.valueOf(str.charAt(i)));
            }
            while (!stack.isEmpty()) {
                buffer.append(stack.pop());
            }
            buffer.append(" ");
        }
        return buffer.toString().trim();
    }

}
