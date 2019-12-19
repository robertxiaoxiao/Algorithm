package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/19/019
 * @description:
 */

public class q121 {
    public int maxProfit(int[] prices) {

        //  it can be seen clearly that the final result determined by the two variety minPrice and maxValue;
        //  at one iteration we can get the dp[i]  the min price of  i day
        //  at another  iteration we can get the dp[i]  the max profit of  i day (sold at i day)
        int n = prices.length;
        if (prices.length == 0)
            return 0;
        int minprices = prices[0];
        int maxprofit = 0;
        for (int i = 1; i <= n; i++) {
            if (prices[i] < minprices)
                minprices = prices[i];
            else if (prices[i] - minprices > maxprofit)
                maxprofit = prices[i] - minprices;
        }
        return maxprofit;
    }

    public int maxProfitUsingDParr(int[] prices) {
        int n = prices.length;
        if (prices.length == 0)
            return 0;
        int[] minPrice = new int[n + 1];
        int[] maxProfit = new int[n + 1];
        minPrice[0] = Integer.MAX_VALUE;
        maxProfit[0] = 0;
        for (int i = 1; i <= n; i++) {
            minPrice[i] = Math.min(minPrice[i - 1], prices[i - 1]);
            maxProfit[i] = Math.max(maxProfit[i - 1], prices[i-1] - minPrice[i]);
        }
        return maxProfit[n];
    }

}

