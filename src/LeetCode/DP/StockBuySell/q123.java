package LeetCode.DP.StockBuySell;/*
 * @author: Robert
 * @date:  2020/2/24/024
 * @description:
 */

public class q123 {

    public int maxProfitTOL(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][n][2];
        int maxprofit = 0;
        for (int len = 1; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j][0] = getMaxprofit(i, j, prices);
                maxprofit = Math.max(maxprofit, dp[i][j][0]);
                if (dp[i][j][0] != 0) {
                    for (int k = i; k < j; k++)
                        dp[i][j][1] = Math.max(dp[i][j][1], dp[i][k][0] + dp[k + 1][j][0]);
                    maxprofit = Math.max(maxprofit, dp[i][j][1]);
                }
            }
        return maxprofit;
    }

    public int getMaxprofit(int i, int j, int[] prices) {
        if (i == j)
            return 0;
        int minval = prices[i];
        int maxprofit = 0;
        for (int t = i; t <= j; t++) {
            if (prices[t] < minval) {
                minval = prices[t];
            } else
                maxprofit = Math.max(maxprofit, prices[t] - minval);
        }
        return maxprofit;
    }

    public int maxProfit(int[] prices) {

        int n = prices.length;
        if (n == 0)
            return 0;
        int[] left = new int[n];
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(prices[i], min);
        }

        int[] right = new int[n];
        right[n - 1] = 0;
        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(prices[i], max);
        }

        int mp = 0;
        for (int i = 0; i < n; i++)
            mp = Math.max(mp, left[i] + right[i]);

        return mp;
    }

    public int maxProfitPer(int[] prices) {

        if (prices.length == 0) return 0;
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        int min = prices[0];
        left[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        int max = prices[prices.length - 1];
        right[prices.length - 1] = 0;
        for (int i = prices.length - 2; i >= 0; i--) {

            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            sum = Math.max(sum, left[i] + right[i]);
        }
        return sum;
    }

}
