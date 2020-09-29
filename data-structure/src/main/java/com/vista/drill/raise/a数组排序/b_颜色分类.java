package com.vista.drill.raise.a数组排序;

/**
 * 2.颜色分类(荷兰国旗)
 * 给定一个包含红 白 蓝共n个元素数组，原地进行排序，使相同颜色元素相邻，且按照红白蓝顺序排序
 * 红 白 蓝分别用0 1 2 代替
 * 定义3个指针：
 * i：从左到右遍历数组的指针
 * l：从左到右待存放0的指针。在l处存放一个0后l++
 * r：从右往左待存放2的指针。在r出存放一个2后r++
 * <p>
 * i指针移动规则：
 * 遇到1：i跳过，i++
 * 遇到0：跟l指针交换值，l右移一位 l++，i右移一位 i++
 * 遇到2：跟r指针交换值，r左移一位 r--；再次对i位置元素值进行判断
 * 若i>r时退出
 * <p>
 * https://leetcode-cn.com/problems/sort-colors/
 *
 * @author WenTingTing by 2020/9/28
 */
public class b_颜色分类 {
    /**
     * @param nums
     */
    public void sortColors(int[] nums) {
        int i = 0, l = 0, r = nums.length - 1;
        while (i <= r) {
            switch (nums[i]) {
                case 0: {
                    int tmp = nums[i];
                    nums[i] = nums[l];
                    nums[l] = tmp;
                    l++;
                    i++;
                    break;
                }
                case 1: {
                    i++;
                    break;
                }
                case 2: {
                    int tmp = nums[i];
                    nums[i] = nums[r];
                    nums[r] = tmp;
                    r--;
                    break;
                }
            }
        }

    }
}
