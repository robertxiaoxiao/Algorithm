package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.Arrays;

public class q64 {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n + 1][m + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        for (int i = 0; i <= n; i++)
            dp[i][0] = Integer.MAX_VALUE;

        dp[1][0] = 0;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }

        return dp[n][m];
    }
}
