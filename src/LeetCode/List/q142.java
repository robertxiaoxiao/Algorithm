package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q142 {

    public ListNode detectCycle(ListNode head) {
        ListNode t1 = head;
        ListNode t2 = head;

        /*
         n  ^  f   l-f   ^
            |  _________ |
              slow
         slow : n+ f
         fast : n + f +xl
         2 *(n+f) =n+f+xl
         n+f=xl
         n = xl-f   meet again
         */
        while (t2 != null && t2.next != null) {
            t1 = t1.next;
            t2 = t2.next.next;
            if (t1 == t2) {
                t1 = head;
                while (t1 != t2) {
                    t1 = t1.next;
                    t2 = t2.next;
                }
                return t1;
            }
        }

        return null;
//        int len = 1;
//        ListNode cur = t1;
//        while (cur.next != t1) {
//            len++;
//            cur = cur.next;
//        }
//
//        ListNode f = head;
//        ListNode s = head;
//        while (len > 0) {
//            f = f.next;
//            len--;
//        }
//
//        while (f != s) {
//            f = f.next;
//            s = s.next;
//        }
        //return f;
    }


}
