package com.vista.drill.raise.b链表;

/**
 * 1.移除链表元素
 * 删除链表中等于给定值val的所有节点。要求：时间复杂度 O(n), 空间复杂度 O(1)
 * 设置3个指针：
 * head：扫描链表节点
 * newHead：虚拟头结点（新链表的头结点）
 * newTail：新链表的尾结点
 * 初始状态： newHead=newTail=new Node(0)
 * 返回结果：newHead.next
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * @author WenTingTing by 2020/9/29
 */
public class a_移除链表元素 {

    /**
     * -  建立新链表的虚拟头结点 newHead=newTail=new Node(0)
     * -  遍历链表节点 head=head.next
     * -  若节点不等于被删除的节点值，则newTail.next指向当前结点： newTail.next=head;
     * newTail前移一位指向当前结点： newTail=head；当前结点指向下一个节点： head=head.next
     * -  直至遍历到链表的null节点为止
     * -  newTail指针指向null ： newTail.next=null
     * -  返回新链表的首个节点： newHead.next
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // 创建虚拟头结点 、尾结点
        ListNode newHead = new ListNode(0);
        ListNode newTail = newHead;
        // 循环遍历链表
        while (head != null) {
            // 节点与给定值不同时，当前结点添加到新链表中，移动newTail
            if (head.val != val) {
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }
        newTail.next = null;
        return newHead.next;
    }
}
