package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/10/010
 * @description:
 */

import TomcatLearning.objectConcurrency.ThreadConcurrency.TestDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class q975 {

    // learning TreeMap usage
    public int oddEvenJumps(int[] A) {
        TreeMap<Integer, Integer> midx = new TreeMap<>();
        int n = A.length;
        int ans = 1;
        boolean[][] dp = new boolean[n][2];
        dp[n - 1][0] = true;
        dp[n - 1][1] = true;
        midx.put(A[n - 1], n - 1);
        for (int i = n - 2; i >= 0; i--) {
            Map.Entry<Integer, Integer> up = midx.ceilingEntry(A[i]);
            if (up != null) {
                dp[i][0] = dp[up.getValue()][1];
            }
            Map.Entry<Integer, Integer> down = midx.floorEntry(A[i]);
            if (down != null) {
                dp[i][1] = dp[down.getValue()][0];
            }

            midx.put(A[i], i);
            if (dp[i][0])
                ans++;
        }

        return ans;
    }


}
