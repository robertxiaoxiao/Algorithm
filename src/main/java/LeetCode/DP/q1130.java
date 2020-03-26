package LeetCode.DP;

import java.util.Arrays;

public class q1130 {

    public int mctFromLeafValues(int[] arr) {

        // dp[0][n-1]=  dp[1][n-1]+max(0)*max(1,n-1)   dp[0][n-1] +max[0,n-1]arr[n];
        int n = arr.length;
        if (n == 0)
            return 0;

        int[][] max = new int[n][n];
        for (int i = 0; i < n; i++) {
            max[i][i] = arr[i];
            for (int l = 2; i + l - 1 < n; l++) {
                int j = i + l - 1;
                max[i][j] = Math.max(max[i][j - 1], arr[j]);
            }
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        for (int l = 2; l <= n; l++)
            for (int i = 0; i + l - 1 < n; i++) {
                int j = i + l - 1;
                for (int cut = i; cut < j; cut++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][cut] + dp[cut + 1][j] + max[i][cut] * max[cut + 1][j]);
            }
        return dp[0][n - 1];
    }

}
