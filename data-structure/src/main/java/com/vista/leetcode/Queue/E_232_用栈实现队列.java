package com.vista.leetcode.Queue;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * @author WenTingTing by 2020/3/27
 */
public class E_232_用栈实现队列 {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    /**
     * Initialize your data structure here.
     */
    public E_232_用栈实现队列() {
        inStack = new Stack();
        outStack = new Stack();
    }

    /**
     * 将元素放入队列的尾部
     * Push element x to the back of queue.
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * 从队列首部移除元素
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();

    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public static void main(String[] strings) {
        E_232_用栈实现队列 obj = new E_232_用栈实现队列();
        obj.push(1);
        obj.push(1);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        obj.push(1);

        boolean param_4 = obj.empty();
        System.out.println(param_4);
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */