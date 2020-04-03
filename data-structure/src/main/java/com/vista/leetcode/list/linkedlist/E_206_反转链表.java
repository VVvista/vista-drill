package com.vista.leetcode.list.linkedlist;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * 示例：
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author WenTingTing by 2020/3/23
 */
public class E_206_反转链表 {
    // 递归
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    // 迭代
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }
}
