package com.vista.drill.raise.a数组排序;

/**
 * 1.合并两个有序数组
 * 有序数组nums1 nums2，将nums2合并到nums1中，使nums1有序
 * 从后往前遍历数组，比较两个数组元素大小，并将元素较大的值放在nums1尾部
 * 假设：
 * 1.初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 2.你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * https://leetcode-cn.com/problems/merge-sorted-array
 *
 * @author WenTingTing by 2020/9/28
 */
public class a_合并两个有序数组 {

    /**
     * 从后往前遍历数组，比较两个数组元素大小，并将元素较大的值放在nums1尾部
     * 定义三个指针：
     * i1:从后往前遍历nums1
     * i2：从后往前遍历nums2
     * cur：nums1尾部待插入位置
     * <p>
     * 此题涉及归并排序思想。
     * 搞两个指针，分别指向nums1和nums2两个数组最后一个元素，即3和6。再拿一个指针指向nums1最后一个位置。
     * 拿出nums1和nums2两个数组最后一个元素进行比较，将两者中较大值放在nums1最后一位指针处，并将该指针位置向前移动一位。并且较大值数组指针也向前移动一位。
     * 循环以上步骤，直到nums2数组下标小于0，则排序完成。
     * 若nums1数组下标小于0，则只需要将nums2数组剩余值依次赋给nums1即完成排序。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1, i2 = n - 1, cur = m + n - 1;
        while (i2 >= 0) {
            if (i1 >= 0 && nums1[i1] > nums2[i2]) {
                nums1[cur--] = nums1[i1--];
            } else {
                nums1[cur--] = nums2[i2--];
            }
        }

    }
}
