package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/25/025
 * @description:
 */


public class q343 {

    public int integerBreak(int n) {
        if (n == 2)
            return 1;
        int[] dp = new int[n + 1];
        // dp[n]= i*dp[n/i];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], (i - j) * j);
                dp[i] = Math.max(dp[i], dp[i - j] * j);
            }
            System.out.println(dp[i]);
        }
        return dp[n];
    }
}
