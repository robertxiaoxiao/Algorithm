package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

public class q931 {
    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            dp[0][i] = A[0][i];
        for (int i = 1; i < n; i++)
            for (int j = 0; j < n; j++) {
                int temp = dp[i - 1][j];
                if (j - 1 >= 0)
                    temp = Math.min(temp, dp[i - 1][j - 1]);
                if (j + 1 < n)
                    temp = Math.min(temp, dp[i - 1][j + 1]);

                dp[i][j] = temp + A[i][j];
            }
        int min = Integer.MAX_VALUE;
        for (int num : dp[n - 1])
            if (num < min)
                min = num;

        return min;
    }
}
