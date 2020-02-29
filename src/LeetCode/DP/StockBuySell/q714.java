package LeetCode.DP.StockBuySell;

public class q714 {

    // shareGrow [0,p1[1]-p[0]]
    public int maxProfit(int[] prices, int fee) {
        int total = 0;
        if (prices.length > 0) {
            int min = prices[0];
            int max = prices[0];
            for (int i = 1; i < prices.length; ++i) {
                if (prices[i] < min || max - fee > prices[i]) {
                    total += Math.max(0, max - min - fee);
                    min = prices[i];
                    max = prices[i];
                } else {
                    max = Math.max(max, prices[i]);
                }
            }
            total += Math.max(0, max - min - fee);
        }

        return total;
    }


    /*
             S0
            /   \
        BUY      SELL
            \    /
              S1
     */

    public int maxProfitStateMachine(int[] prices, int fee) {
        int n = prices.length;
        int[] dp0 = new int[n];
        int[] dp1 = new int[n];
        dp0[0] = 0;
        dp1[0] = -prices[0] - fee;
        for (int i = 1; i < n; i++) {
            dp0[i] = Math.max(dp0[i - 1], dp1[i - 1] + prices[i]);
            dp1[i] = Math.max(dp1[i - 1], dp0[i - 1] - prices[i] - fee);
        }
        return dp0[n - 1];
    }

}
