package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/1/001
 * @description:
 */

import java.util.HashMap;
import java.util.HashSet;

public class q62 {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;
        for (int i = 0; i < n; i++)
            dp[0][i] = 1;
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsCompress(int m, int n) {
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        for (int i = 0; i < n; i++)
            dp1[i] = 1;

        dp2[0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp2[j] = dp1[j] + dp2[j - 1];
            }
            dp1 = dp2;
        }
        return dp1[n - 1];
    }

    public int uniquePathsUsingmem(int m, int n) {

        HashMap<Integer, Integer> mem = new HashMap<>();
        return helper(m - 1, n - 1, mem);
    }

    public int helper(int cx, int cy, HashMap<Integer, Integer> mem) {
        if (cx == 0 || cy == 0)
            return 1;
        if (cx < 0)
            return 0;
        if (cy < 0)
            return 0;

        int cur = cx * 100 + cy;
        if (mem.containsKey(cur))
            return mem.get(cur);

        int lx = cx;
        int ly = cy - 1;
        int ux = cx - 1;
        int uy = cy;

        int ans = helper(lx, ly, mem) + helper(ux, uy, mem);
        mem.put(cur, ans);
        return ans;
    }
}
