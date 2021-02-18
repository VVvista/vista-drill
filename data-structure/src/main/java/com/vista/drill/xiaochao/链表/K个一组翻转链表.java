package com.vista.drill.xiaochao.链表;

/**https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * @author Wen TingTing by 2021/2/9
 */
public class K个一组翻转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode tail =head;
        for (int i = 1; i <= k; i++) {
            if(tail==null) return head;
            tail=tail.next;
        }
        ListNode newHead=reverseKGroup(tail,k);
        ListNode res=reverse(head,tail);
        head.next=newHead;
        return res;

    }

    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode newHead=null;
        ListNode cur=head;
        while(cur!=tail){
            ListNode tmp=cur.next;
            cur.next=newHead;
            newHead=cur;
            cur=tmp;
        }

        return newHead;
    }
}
