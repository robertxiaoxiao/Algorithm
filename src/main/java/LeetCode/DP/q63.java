package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.Arrays;

public class q63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        if (n == 0)
            return 0;

        int m = obstacleGrid[0].length;
        int[][] dp = new int[n + 1][m + 1];
        Arrays.fill(dp[0], -1);
        for (int i = 0; i < m; i++)
            dp[0][i] = -1;
        dp[1][1] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1)
                    continue;
                if (obstacleGrid[i - 1][j - 1] == 1)
                    dp[i][j] = -1;
                else {
                    int path = 0;
                    if (dp[i - 1][j] != -1)
                        path += dp[i - 1][j];
                    if (dp[i][j - 1] != -1)
                        path += dp[i][j - 1];
                    dp[i][j] = path;
                }
            }

        return dp[n][m];
    }

    public int dp(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        if (n == 0)
            return 0;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];

        if (obstacleGrid[0][0] == 1)
            dp[0][0] = 0;
        else
            dp[0][0] = 1;
        for (int i = 1; i < m; i++)
            if (dp[0][i - 1] == 1 && obstacleGrid[0][i] != 1)
                dp[0][i] = 1;

        for (int i = 1; i < n; i++)
            if (dp[i - 1][0] == 1 && obstacleGrid[i][0] != 1)
                dp[i][0] = 1;

        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] != 1)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }

        return dp[n - 1][m - 1];
    }
}
