package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class q23 {

    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode head : lists) {
            ListNode cur = head;
            while (cur != null) {
                pq.add(cur);
                cur = cur.next;
            }
        }

        ListNode ans = null;
        ListNode last = null;
        while (!pq.isEmpty()) {
            if (last == null) {
                last = pq.poll();
                ans = last;
            } else {
                last.next = pq.poll();
                last = last.next;
            }
            last.next = null;
        }
        return ans;
    }

}
