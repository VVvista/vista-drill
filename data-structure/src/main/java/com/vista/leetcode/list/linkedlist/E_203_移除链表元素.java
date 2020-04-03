package com.vista.leetcode.list.linkedlist;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * @author WenTingTing by 2020/4/2
 */
public class E_203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        if(head.val==val){
            head=head.next;
        }
        ListNode node = head;

        while (node != null && node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }
}
