package com.vista.drill.raise.h高频题;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 思路：一趟扫描即可解决
 * 设置两个指针：
 * - i： 循环遍历元素，从0开始
 * - cur: 非零元素可存放位置
 * - 若nums[i]不为0，nums[cur]=nums[i],nums[i]=0,cur++,i++
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * @author WenTingTing by 2020/12/17
 */
public class a_移动零 {
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            // 判断条件：cur != i ，是为了避免[1] 时输出错误结果[0]
            if (cur != i) {
                nums[cur] = nums[i];
                nums[i] = 0;
            }
            cur++;

        }
    }
}
