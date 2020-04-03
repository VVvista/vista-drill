package com.vista.leetcode.stack;

import java.util.Stack;

/**
 * 题目：https://leetcode-cn.com/problems/score-of-parentheses/
 * 解题思路：https://leetcode-cn.com/problems/score-of-parentheses/solution/kan-bu-dong-bie-ren-de-ti-jie-zi-ji-you-xie-liao-y/
 * 题目：给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * <p>
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *
 *
 * 解题思路：主要还是在括号匹配的基础上，利用题目中的规律转换成代码逻辑
 * 1.如果字符为左括号，字符入栈
 * 2.如果字符为右括号，弹出栈顶元素：
 *   2.1 如果元素为左括号，"1"入栈
 *   2.2 否则（元素为数字），继续循环弹出栈顶元素，如果弹出元素仍为数字则数字相加；直到弹出元素为左括号，此时数字和*2入栈
 * 3.最后将栈元素依次弹出相加，便得到最终结果
 *
 * 注意：字符串遍历完之后可能栈中有多个数字，此时需要将所有栈元素相加，如：()()()
 * @author WenTingTing by 2020/3/25
 */
public class M_856_括号的分数 {

    public int scoreOfParentheses(String S) {
        Stack<String> stack = new Stack<String>();
        int length = S.length();
        for (int i = 0; i < length; i++) {
            String c = S.substring(i, i + 1);
            // 左括号入栈
            if ("(".equals(c)) {
                stack.push(c);
            } else {
                // 弹出栈顶元素
                String pop = stack.pop();
                int num = 0;
                // 如果pop为数字则依次相加，并一直循环弹出，知道弹出元素为左括号
                while (!"(".equals(pop)) {
                    num = num + Integer.parseInt(pop);
                    pop = stack.pop();
                }
                // 将数字和*2入栈
                num = num == 0 ? 1 : num * 2;
                stack.push(Integer.toString(num));
            }
        }
        // 计算栈元素的和
        int n = 0;
        while (!stack.isEmpty()) {
            n += Integer.parseInt(stack.pop());
        }
        return n;
    }
}
