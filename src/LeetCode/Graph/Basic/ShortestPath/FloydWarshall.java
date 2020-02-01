package LeetCode.Graph.Basic.ShortestPath;/*
 * @author: Robert
 * @date:  2020/1/18/018
 * @description:
 */

import java.util.Arrays;

public class FloydWarshall {
    // dp[i][j][k] : shortest path from node i to node j through nodes{0,k-1,k}
    // dp[0][i][j]=M[i][j]
    // dp[k][i][j]=Math.min(dp[k-1][i][j],dp[k-1][i][k]+dp[k-1][k][j])

    public int[][] FW(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            // unlinked : -1
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
            for (int j = 0; j < n; j++)
                dp[i][j] = matrix[i][j];
        }

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j])
                        dp[i][j] = dp[i][k] + dp[k][j];
                }

        // Identify negative cycles by propagating the value 'NEGATIVE_INFINITY'
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j])
                        dp[i][j] = Integer.MIN_VALUE;
                }
        return dp;
    }
}
