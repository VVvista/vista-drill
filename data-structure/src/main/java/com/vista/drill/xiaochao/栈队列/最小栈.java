package com.vista.drill.xiaochao.栈队列;


import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/min-stack/
 *
 * @author Wen TingTing by 2021/2/6
 */
public class 最小栈 {
    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<Integer>();
            minStack = new Stack<Integer>();

        }

        public void push(int x) {
            stack.push(x);
            if (!minStack.empty() && minStack.peek() < x) {
                minStack.push(minStack.peek());
            } else {
                minStack.push(x);
            }

        }

        public void pop() {
            stack.pop();
            minStack.pop();

        }

        public int top() {
            return stack.peek();

        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
