package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/16/016
 * @description:
 */

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class q817 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public int numComponents(ListNode head, int[] G) {
            HashSet<Integer> hset = new HashSet<>();
            for (int i : G)
                hset.add(i);
            HashMap<Integer, List<Integer>> hm = new HashMap<>();
            int cv = head.val;
            while (head.next != null) {
                int nv = head.next.val;
                if (hm.get(cv) == null)
                    hm.put(cv, new LinkedList<Integer>());
                if (hm.get(nv) == null)
                    hm.put(nv, new LinkedList<Integer>());

                if (hset.contains(cv) && hset.contains(nv)) {
                    hm.get(cv).add(nv);
                    hm.get(nv).add(cv);
                }
                cv = nv;
                head = head.next;
            }
            HashSet<Integer> visited = new HashSet<>();
            int ans = 0;
            for (int i : G) {
                if (visited.contains(i))
                    continue;
                dfs(hm, i, visited);
                ans++;
            }
            return ans;
        }

        public void dfs(HashMap<Integer, List<Integer>> hm, int cv, HashSet<Integer> visited) {
            if (visited.contains(cv))
                return;
            visited.add(cv);
            for (int next : hm.get(cv))
                dfs(hm, next, visited);
        }

        public int getComponents(ListNode head, int[] G) {
            HashSet<Integer> hset = new HashSet<>();
            for (int i : G)
                hset.add(i);
            ListNode temp = head;
            int ans = 0;
            while (temp != null) {
                if (hset.contains(temp.val) && (temp.next == null || !hset.contains(temp.next.val)))
                    ans++;
                temp = temp.next;
            }
            return ans;
        }
    }


}
