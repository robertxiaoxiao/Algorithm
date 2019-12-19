package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/19/019
 * @description:
 */

public class q746 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++)
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);

        return dp[n];
    }

    // ?
    public int minCostClimbingStairsDescendCompacityDim(int[] cost) {

        // first second : the cost of walking throuth node i ,maybe i+1 or i+2;
        int n = cost.length;
        int first = cost[0];
        int second = cost[1];
        int cur = 0;
        for (int i = 2; i < n; i++) {
            cur = cost[i] + Math.min(first, second);
            first = second;
            second = cur;
        }
        return Math.min(first, second);
    }

}
