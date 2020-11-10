package com.vista.drill.raise.c栈队列;

import lombok.val;

import java.util.Stack;

/**
 * 1.最小栈
 * 设计一个push(入栈)、pop(出栈)、top(查询栈顶元素)操作，并能在常数时间内检索最小元素的栈
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * https://leetcode-cn.com/problems/min-stack/
 *
 * @author WenTingTing by 2020/10/20
 */
public class a_最小栈 {
    /*正常栈：存放正常数据*/
    private Stack<Integer> stack;

    /*最小栈：存放当前栈的最小值*/
    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public a_最小栈() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();

    }

    /**
     * 元素入栈
     *
     * @param x
     */
    public void push(int x) {
        // 元素入正常栈
        stack.push(x);

        // 最小栈添加最小元素
        if (minStack.empty()) minStack.push(x);
        else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    /**
     * 元素出栈
     */
    public void pop() {
        if (stack.empty()) return;
        stack.pop();
        minStack.pop();
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    public int top() {
        if (stack.empty()) return Integer.MIN_VALUE;
        return stack.peek();
    }

    /**
     * 查看栈中最小元素
     *
     * @return
     */
    public int getMin() {
        if (minStack.empty()) return Integer.MIN_VALUE;
        return minStack.peek();

    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */