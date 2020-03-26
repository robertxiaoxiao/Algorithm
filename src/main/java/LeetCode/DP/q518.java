package LeetCode.DP;

public class q518 {

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++)
            for (int j = 0; j <= amount; j++) {
                for (int k = 0; k * coins[i - 1] < j; k++)
                    dp[i][j] += dp[i - 1][j - k * coins[i - 1]];
            }

        return dp[amount][n];
    }

    public int changeON(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++)
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[j] += dp[j - coins[i - 1]];
            }
        return dp[amount];
    }

}
