package LeetCode.DP.StockBuySell;/*
 * @author: Robert
 * @date:  2020/2/24/024
 * @description:
 */

public class q121 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;

        int[] dp = new int[n];

        int maxval = 0;
        dp[0] = 0;
        int minval = prices[0];
        for (int i = 1; i < n; i++) {
            if (prices[i] < minval) {
                minval = prices[i];
                dp[i] = 0;
            } else {
                dp[i] = prices[i] - minval;
                maxval = Math.max(dp[i], maxval);
            }
        }
        return maxval;
    }

}
