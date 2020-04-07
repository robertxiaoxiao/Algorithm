package LeetCode.List;

import java.util.LinkedList;
import java.util.List;

public class q86 {

    public ListNode partitionnaive(ListNode head, int x) {
        List<Integer> lesslist = new LinkedList<>();
        List<Integer> largerlist = new LinkedList<>();
        if (head == null)
            return null;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val < x)
                lesslist.add(temp.val);
            else
                largerlist.add(temp.val);
            temp=temp.next;
        }
        int idx = 0;
        int s1 = lesslist.size();
        int s2 = largerlist.size();
        temp = head;
        while (idx < s1) {
            temp.val = lesslist.get(idx++);
            temp = temp.next;
        }
        idx = 0;
        while (idx < s2) {
            temp.val = largerlist.get(idx++);
            temp = temp.next;
        }
        return head;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode smallerHead = new ListNode(0), biggerHead = new ListNode(0);
        ListNode smaller = smallerHead, bigger = biggerHead;
        while (head != null) {
            if (head.val < x) {
                smaller = smaller.next = head;
            } else {
                bigger = bigger.next = head;
            }
            head = head.next;
        }
        // no need for extra check because of fake heads
        smaller.next = biggerHead.next; // join bigger after smaller
        bigger.next = null; // cut off anything after bigger
        return smallerHead.next;
    }

}
