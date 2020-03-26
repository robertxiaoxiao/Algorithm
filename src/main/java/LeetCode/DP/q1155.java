package LeetCode.DP;

public class q1155 {

    public int numRollsToTarget(int d, int f, int target) {
        // dp[d][target]
        long exp = (long) Math.pow(10, 9) + 7;
        long[][] dp = new long[d + 1][target + 1];
        for (int i = 1; i <= f && i <= target; i++)
            dp[1][i] = 1;
        if (target > d * f)
            return 0;
        if (target == d * f)
            return 1;
        for (int i = 2; i <= d; i++)
            for (int t = 2; t <= i * f && t <= target; t++) {
                for (int j = 1; j <= f; j++) {
                    if (t >= j)
                        dp[i][t] += dp[i - 1][t - j];
                }
                dp[i][t] = dp[i][t] % exp;
            }
        return (int) (dp[d][target] % (1e9 + 7));
    }


}
