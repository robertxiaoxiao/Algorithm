package LeetCode.DP;

import java.util.Arrays;

public class q1140 {

    public int stoneGameII(int[] piles) {
        int m = 1;
        int n = piles.length;
        int[] presum = new int[n];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        for (int i = 1; i <= n; i++)
            presum[i] = presum[i - 1] + piles[i - 1];

        return (helper(piles, dp, presum, 0, 1) + presum[n]) / 2;
    }

    public int helper(int[] piles, int[][] dp, int[] presum, int i, int m) {
        int max = Integer.MIN_VALUE;

        if (i == piles.length)
            return 0;

        if (i + m >= piles.length)
            return dp[i][m] = presum[piles.length] - presum[i];

        if (dp[i][m] != Integer.MIN_VALUE)
            return dp[i][m];

        for (int k = i; k < i + 2 * m && k < piles.length; k++) {
            max = Math.max(max, presum[k + 1] - presum[i] - helper(piles, dp, presum, k + 1, Math.max(m, k - i + 1)));
        }

        return dp[i][m] = max;
    }

}
