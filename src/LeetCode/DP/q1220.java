package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/24/024
 * @description:
 */

public class q1220 {
    static int MOD = (int) 1e9 + 7;

    // NOTICE  numerical overflow  Using long to prevent it and each add transaction should be followed by %mod
    public int countVowelPermutation(int n) {

        //dp[i][v] : nums of string valid ending with v
        long[][] dp = new long[n + 1][5];
        for (int i = 0; i < 5; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= n; i++) {
            long t1 = dp[i - 1][0];
            long t2 = dp[i - 1][1];
            long t3 = dp[i - 1][2];
            long t4 = dp[i - 1][3];
            long t5 = dp[i - 1][4];
            /*
            dp[i] =   0 1 1 0 1    1
                      1 0 1 0 0    1
                      0 1 0 1 0    1
                      0 0 0 1 0    1
                      0 0 0 1 1    1
                      dp[i]=M^(n-1)*dp[1]; using fast_pow
             */
            dp[i][0] = (t2 + t3 + t5) % MOD;
            dp[i][1] = (t1 + t3) % MOD;
            dp[i][2] = (t2 + t4) % MOD;
            dp[i][3] = t3;
            dp[i][4] = (t3 + t4) % MOD;
        }
        long ans = 0;
        for (long num : dp[n])
            ans = (ans + num) % MOD;
        return (int) ans % MOD;
    }

    public int countVowelPermutationCompress(int n) {

        //dp[i][v] : nums of string valid ending with v
        long[] dp = new long[5];
        for (int i = 0; i < 5; i++)
            dp[i] = 1;

        for (int i = 2; i <= n; i++) {
            long t1 = dp[0];
            long t2 = dp[1];
            long t3 = dp[2];
            long t4 = dp[3];
            long t5 = dp[4];
            dp[0] = (t2 + t3 + t5) % MOD;
            dp[1] = (t1 + t3) % MOD;
            dp[2] = (t2 + t4) % MOD;
            dp[3] = t3;
            dp[4] = (t3 + t4) % MOD;
        }
        long ans = 0;
        for (long num : dp)
            ans = (ans + num) % MOD;
        return (int) ans % MOD;
    }
}
