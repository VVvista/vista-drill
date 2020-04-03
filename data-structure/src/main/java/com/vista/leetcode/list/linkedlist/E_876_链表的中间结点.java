package com.vista.leetcode.list.linkedlist;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * @author WenTingTing by 2020/3/31
 */
public class E_876_链表的中间结点 {
    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode low = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            low = low.next;
        }
        return low;
    }

}
