package com.vista.drill.xiaochao.链表;

/**https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * @author Wen TingTing by 2021/2/8
 */
public class 链表的中间结点 {
    /**
     * 快慢指针
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;

    }
}
