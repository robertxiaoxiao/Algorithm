package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

public class q1039 {
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        return helper(A, 0, n - 1, dp);
    }

    public int dp(int[] A) {

        int n = A.length;
        int[][] dp = new int[n][n + 1];

        //dp[i][j]  start i end j the minTri ;  [0][i]  start i end  [i][end]
        // dp[i][j] =dp[i][mid] +dp[mid][j] +A[i][j][mid];

        for (int len = 1; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (j == i + 1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + A[i] * A[k] * A[j] + dp[k][j]);
                }
            }
        return dp[0][n];
    }

    public int helper(int[] A, int start, int end, int[][] mem) {
        if (start + 1 == end)
            return 0;

        if (start + 2 == end)
            return A[start] * A[(start + 1)] * A[(start + 2)];

        if (mem[start][end] != 0)
            return mem[start][end];

        int min = Integer.MAX_VALUE;
        for (int i = start + 1; i < end; i++)
            min = Math.min(min, helper(A, start, i, mem) + A[start] * A[i] * A[end] + helper(A, i, end, mem));

        return mem[start][end] = min;
    }

}
