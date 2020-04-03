package com.vista.leetcode.list.linkedlist;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * @author WenTingTing by 2020/3/30
 */
public class E_160_相交链表 {
    /**
     * 解题思路：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
     * 主要运用快慢指针：
     * 若相交，链表A： a+c, 链表B : b+c. a+c+b+c = b+c+a+c 。则会在公共处c起点相遇。
     * 若不相交，a +b = b+a 。因此相遇处是NULL
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
