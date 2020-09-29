package com.vista.drill.raise.b链表;

/**
 * 3.相交链表
 * 找出两个链表相交的起始节点，要求： O(n) 时间复杂度，且仅用 O(1) 内存。
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * <p>
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * @author WenTingTing by 2020/9/29
 */
public class c_相交链表 {

    /**
     * 思路：（两个链表分别加上另一个链表，使得总长度相同，最后部分即为相同部分）
     * - 遍历两个链表，判断两节点元素是否相同
     * - 若一个链表下一个节点为null，则循环另一个链表
     * - 直至查找到相同节点，或两节点都为null
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }
}
