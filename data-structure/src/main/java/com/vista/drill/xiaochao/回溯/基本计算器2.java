package com.vista.drill.xiaochao.回溯;

import java.util.Stack;


/**
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * @author Wen TingTing by 2021/2/13
 */
public class 基本计算器2 {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        s.trim();
        int num = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c==' '&& i!=s.length()-1) continue;
            if (isNumber(c)) {
                num = num * 10 + (c - '0');
            }
            if (!isNumber(c) || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                sign=c;
                num=0;
            }
        }
        int result = 0;
        while (!stack.empty()) result += stack.pop();
        return result;
    }

    private boolean isNumber(char ch) {
        if (ch >= 48 && ch <= 57) {return true;}
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new 基本计算器2().calculate("3/2"));
    }
}
