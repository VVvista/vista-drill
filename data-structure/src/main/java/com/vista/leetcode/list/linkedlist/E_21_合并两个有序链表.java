package com.vista.leetcode.list.linkedlist;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 计算效率：迭代算法<递归算法
 *
 * @author WenTingTing by 2020/3/30
 */
public class E_21_合并两个有序链表 {
    /**
     * 迭代算法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cFirst = new ListNode(-1);
        ListNode cLast = cFirst;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cLast.next = l1;
                l1 = l1.next;
            } else {
                cLast.next = l2;
                l2 = l2.next;
            }
            cLast = cLast.next;
        }
        cLast.next = l1 != null ? l1 : l2;
        return cFirst.next;
    }

    /**
     * 递归算法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
