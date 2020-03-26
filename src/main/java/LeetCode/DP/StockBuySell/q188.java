package LeetCode.DP.StockBuySell;/*
 * @author: Robert
 * @date:  2020/2/24/024
 * @description:
 */

public class q188 {

    // dp[n][k][buy] :the most profit at n days with at most k transaction
    static Integer[][][] dp;

    public int maxProfitTOL(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][n][k + 1];
        int maxprofit = 0;
        for (int len = 1; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                for (int t = 1; t <= k; t++) {
                    if (t == 1) {
                        dp[i][j][1] = getMaxprofit(i, j, prices);
                        maxprofit = Math.max(maxprofit, dp[i][j][0]);
                    } else {
                        if (dp[i][j][0] != 0) {
                            for (int cut = i; cut < j; cut++)
                                dp[i][j][t] = Math.max(dp[i][j][t], dp[i][cut][t - 1] + dp[cut + 1][j][1]);
                            maxprofit = Math.max(maxprofit, dp[i][j][t]);
                        }
                    }
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

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int res = 0;
        if (k > prices.length / 2) {
            for (int i = 1; i < prices.length; i++)
                if (prices[i] > prices[i - 1])
                    res += prices[i] - prices[i - 1];
            return res;
        }
        dp = new Integer[2][k + 1][n];
        return helper(k, 0, 1, prices);
    }

    public int helper(int k, int d, int buy, int[] prices) {
        if (k == 0 || d == prices.length)
            return 0;

        if (dp[buy][k][d] != null)
            return dp[buy][k][d];
        int x;
        if (buy == 1) {
            x = helper(k, d + 1, 0, prices) - prices[d];
        } else {
            x = helper(k - 1, d + 1, 1, prices) + prices[d];
        }
        x = Math.max(x, helper(k, d + 1, buy, prices));

        return dp[buy][k][d] = x;
    }

}
