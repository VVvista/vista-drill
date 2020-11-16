package com.vista.drill.raise.c栈队列;

import java.util.Stack;

/**
 * 请根据每日气温列表，重新生成一个列表。
 * 对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数
 * 思路：找到右边第一个比它大的值
 * <p>
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 *
 * @author Wen TingTing by 2020/11/10
 */
public class f_每日温度 {
    /**
     * 方法1：
     * 思路：找到右边第一个比它大的值
     * 实现：创建一个栈
     * - 栈：栈底->栈顶 单调递减
     * - 将数组元素单调递减的存入栈中
     * - 如果值a，不满足单调递减条件，则弹出栈顶元素，弹出元素右边第一个比它大的元素即为值a；
     * - 遍历完所有元素，则栈中元素的等待天数为0
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!stack.empty() && T[stack.peek()] < T[i]) {
                int tmp = stack.peek();
                result[tmp] = i - tmp;
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 方法2：倒推法
     * 思路：动态规划：从后往前遍历元素，i位置元素依次与j位置元素比较大小，直到找到右边第一个比i大的元素
     * 实现：创建两个指针 i,j
     * - i：从后往前扫描所有元素，i从倒数第二个位置开始(因为最后一个元素的等待天数必为0)
     * - j：指向i右边索引位置，初始j=i+1
     * - 对于每一个i，初始j=i+1
     * - 1.如果T[i]<T[j],则i右边第一个比它大的是j，即：values[i]=i-i;i--
     * - 2.如果value[j]==0,则j的后边没有比它大,推出i的后边没有比它大，即：value[i]==0
     * - 3.否则设置j=j+values[j],循环步骤1
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures1(int[] T) {
        int[] values = new int[T.length];
        for (int i = T.length - 1; i >= 0; i--) {
            int j = i + 1;
            while (j < T.length) {
                if (T[i] < T[j]) {
                    values[i] = j - i;
                    break;
                } else if (values[j] == 0) {
                    values[i] = 0;
                    break;
                }
            j=j+values[j];
            }
        }
        return values;
    }
}
