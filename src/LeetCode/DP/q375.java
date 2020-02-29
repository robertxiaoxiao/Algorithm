package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/25/025
 * @description:
 */

public class q375 {

    public int getMoneyAmountDP(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int l = 1; l <= n - len + 1; l++) {
                int r = l + len - 1;
                if (len == 2) {
                    dp[l][r] = l;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int mid = l + 1; mid < r; mid++) {
                    int val = mid + Math.max(dp[l][mid - 1], dp[mid + 1][r]);
                    min = Math.min(min, val);
                }
                dp[l][r] = min;
            }
        }
        return dp[1][n];
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return helper(1, n, dp);
    }

    public int helper(int i, int j, int[][] dp) {
        if (i == j)
            return 0;
        if (i + 1 == j)
            return dp[i][j] = i;
        if (dp[i][j] != 0)
            return dp[i][j];
        int min = Integer.MAX_VALUE;
        for (int cur = i + 1; cur < j; cur++) {
            int val = cur + Math.max(helper(i, cur - 1, dp), helper(cur + 1, j, dp));
            min = Math.min(min, val);
        }
        return dp[i][j] = min;
    }

}
