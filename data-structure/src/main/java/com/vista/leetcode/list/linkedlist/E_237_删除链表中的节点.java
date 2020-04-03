package com.vista.leetcode.list.linkedlist;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * 示例：
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author WenTingTing by 2020/3/23
 */
public class E_237_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;

    }
}
