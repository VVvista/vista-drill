package com.vista.drill.raise.b链表;

/**
 * 4.分割两个链表
 * 给定一个链表和一个特定值x，对链表进行分割，使得所有小于x的结点都在左边，所有大于等于x的结点都在右边。
 * 要求： 保留结点最初的相对位置，O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * https://leetcode-cn.com/problems/partition-list/
 *
 * @author WenTingTing by 2020/9/29
 */
public class d_分割两个链表 {
    /**
     * 设置两个新链表 a b，分别设定2个指针：虚拟头节点，尾指针
     * - 遍历目标链表
     * - 若节点小于x，节点加入a链表，a链表尾指针指向当前结点
     * - 若节点大于等于x，结点加入b链表，b链表指针指向当前结点
     * - 遍历完成后，a链表尾结点与b链表首个节点拼接在一起
     * - 设置b链表尾结点 next 为null，返回a链表首个节点
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;

        // 创建2个新链表的虚拟头结点、尾结点
        ListNode aHead = new ListNode(0);
        ListNode aTail = aHead;
        ListNode bHead = new ListNode(0);
        ListNode bTail = bHead;

        while (head != null) {
            if (head.val < x) {
                aTail.next = head;
                aTail = head;
            } else {
                bTail.next = head;
                bTail = head;
            }
            head = head.next;
        }

        bTail.next = null;
        aTail.next = bHead.next;
        return aHead.next;
    }
}
