package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/26/026
 * @description:
 */

import java.util.HashSet;

public class q898 {


    public int subarrayBitwiseORs(int[] A) {
        int n = A.length;
        HashSet<Integer> ans = new HashSet<>();
        HashSet<Integer> cur = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            cur.clear();
            HashSet<Integer> next = new HashSet<>();
            int c = A[i - 1];
            next.add(c);
            for (int k : cur) {
                cur.add(c | k);
            }
            cur = next;
            ans.addAll(next);
        }
        return ans.size();

    }

}
