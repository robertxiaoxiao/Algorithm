package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

import java.awt.*;

public class q147 {

    public ListNode insertionSortList(ListNode head) {

        ListNode s = head;
        ListNode ans = head;
        if (s == null || s.next == null)
            return s;
        ListNode cur = s.next;
        // cut from list
        head.next = null;
        while (cur != null) {
            ListNode temp = cur;
            cur = cur.next;
            temp.next = null;
            ans = insert(ans, temp);
        }
        return ans;
    }

    public ListNode insert(ListNode head, ListNode s) {
        if (head.val > s.val) {
            s.next = head;
            return s;
        }

        ListNode cur = head;
        ListNode next = head.next;
        while (cur != null && next != null) {
            if (cur.val <= s.val && next.val >= s.val) {
                cur.next = s;
                s.next = next;
                return head;
            }
            cur = next;
            next = next.next;
        }
        if (next == null)
            cur.next = s;

        return head;
    }

}
