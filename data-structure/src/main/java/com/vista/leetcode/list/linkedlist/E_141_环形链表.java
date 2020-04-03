package com.vista.leetcode.list.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * <p>
 * 示例：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 思路：快慢指针、hash表
 *
 * @author WenTingTing by 2020/3/23
 */
public class E_141_环形链表 {
    /**
     * 双指针：快慢指针
     *
     * @param head 注意head是否为null
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        if (head != null) {
            ListNode quick = head.next;
            // quick.next != null:为了赋值时出现空指针异常
            while (quick != null && quick.next != null) {
                if (slow.equals(quick)) {
                    return true;
                }
                quick = quick.next.next;
                slow = slow.next;
            }
        }
        return false;
    }

    /**
     * hash表
     *
     * @param head 注意head是否为null
     * @return
     */
    public boolean hasCycle2(ListNode head) {
         Set<ListNode> hashSet = new HashSet<ListNode>();
        while(head!=null){
            if(hashSet.contains(head)){
                return true;
            }
            hashSet.add(head);
            head=head.next;
        }
        return false;
    }


}
