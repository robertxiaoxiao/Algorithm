package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q24 {

    public ListNode swapPairs(ListNode head) {

        if (head == null)
            return null;
        if (head.next == null)
            return head;

        ListNode tn = head.next;
        ListNode temp = tn.next;
        head.next = swapPairs(temp);
        tn.next = head;
        return tn;
    }


}
