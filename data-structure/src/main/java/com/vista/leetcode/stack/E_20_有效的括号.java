package com.vista.leetcode.stack;


import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * <p>
 * 解题思路：
 * 使用栈存储左括号，依次获取字符串中的字符
 * 1.如果字符为左括号，则字符进栈
 * 2.如果字符为右括号：如果栈为空，则返回false；否则弹出栈顶元素与字符判断，如果匹配成功继续进行循环，如果匹配不成功则返回false。
 * 3.所有字符操作完，栈为空则返回true，否则返回false。
 * <p>
 * 注意点：
 * 可以提前预备好所有的括号匹配情况，然后利用栈存储左括号，右括号与栈顶元素匹配。
 * 注意返回false的判断，以及栈空的判断
 *
 * @author WenTingTing by 2020/3/25
 */
public class E_20_有效的括号 {
    /**
     * 使用stack实现
     * 此方法简单粗暴，不推荐使用，阅读性差
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character pop = stack.pop();
                    if (pop == '(' && c != ')') {
                        return false;
                    }
                    if (pop == '{' && c != '}') {
                        return false;
                    }
                    if (pop == '[' && c != ']') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 使用stack+hashMap实现
     * 提前将括号情况存进map，然后将左括号入栈，右括号与出栈比对是否符合要求
     *
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>();
        HashMap<Character, Character> map = new HashMap<Character, Character>(3);
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character pop = stack.pop();
                    if (!map.get(pop).equals(c)) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
