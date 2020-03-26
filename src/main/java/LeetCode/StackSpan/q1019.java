package LeetCode.StackSpan;/*
 * @author: Robert
 * @date:  2019/12/12/012
 * @description:
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class q1019 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] nextLargerNodes(ListNode head) {
        HashMap<ListNode, Integer> hm = new HashMap<>();
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        int idx = 0;
        while (temp != null) {
            int curval = temp.val;
            while (!stack.isEmpty() && stack.peek().val < curval) {
                ListNode tempNode = stack.pop();

                hm.put(tempNode, curval);
            }
            stack.push(temp);
            temp = temp.next;
            idx++;
        }
        int[] res = new int[idx];
        ListNode tnode = head;
        int i = 0;
        while (tnode != null) {
            res[i++] = hm.getOrDefault(tnode, 0);
            tnode = tnode.next;
        }

        return res;
    }

    public int[] nextLargerNodesBrief(ListNode head) {

        List<Integer> nums = new LinkedList<>();
        for (ListNode temp = head; temp != null; temp = temp.next)
            nums.add(temp.val);

        int n = nums.size();
        int j = 0;
        int[] keys = new int[n];
        for (int key : nums)
            keys[j++] = key;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && keys[stack.peek()] < keys[i]) {
                stack.pop();
                res[stack.peek()] = keys[i];
            }
            stack.push(i);
        }
        return res;
    }
}
