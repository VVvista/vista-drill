package com.vista.leetcode.list.linkedlist;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * @author WenTingTing by 2020/3/30
 */
public class E_83_删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            /*错误解法
            node.next = node.val == node.next.val ? node.next.next : node.next;
            node = node.next;*/
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }
}
