package com.vista.leetcode.list.linkedlist;

/**
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * 解题思路同：E_面试题_02_02_返回倒数第k个节点
 * 双指针
 *
 * @author WenTingTing by 2020/3/31
 */
public class E_面试题22_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p = head;
        ListNode q = head;
        for (int i = 0; i < k; i++) {
            q = q.next;
        }
        while (q != null) {
            q = q.next;
            p = p.next;
        }
        return p;
    }
}
