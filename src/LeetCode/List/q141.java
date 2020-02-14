package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q141 {

    public boolean hasCycle(ListNode head) {
        ListNode t1 = head;
        if (head == null || head.next == null)
            return false;
        ListNode t2 = head.next.next;

        while (t1 != t2) {
            t1 = t1.next;
            if (t2 == null || t2.next == null)
                return false;
            t2 = t2.next.next;
        }

        return true;
    }
}
