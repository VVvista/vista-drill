package com.vista.drill.xiaochao.回溯;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/basic-calculator/
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 *
 * @author Wen TingTing by 2021/2/13
 */
public class 基本计算器 {
    private int start = 0;
    private char[] ch;

    public int calculate(String s) {
        ch = s.toCharArray();
        return calculate();
    }

    public int calculate() {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        int sign = '+';
        while (start < ch.length) {
            char c = ch[start];
            if (c == ' ' && start != ch.length - 1) {
                start++;
                continue;
            }
            boolean isNumber=isNumber(c);
            if (isNumber) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                start++;
                num = calculate();
            }
            if (!isNumber || start == ch.length - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                }
                sign = c;
                num = 0;
            }
            if (sign == ')') {
                break;
            }
            start++;
        }
        return sum(stack);
    }


    private int sum(Stack<Integer> stack) {
        int result = 0;
        while (!stack.empty()) {
            result += stack.pop();
        }
        return result;
    }

    private boolean isNumber(char ch) {
        if (ch >= 48 && ch <= 57) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new 基本计算器().calculate("(1+(4+5+2)-3)+(6+8)+1"));
        System.out.println(new 基本计算器().calculate(" (6+8)"));

    }
}
