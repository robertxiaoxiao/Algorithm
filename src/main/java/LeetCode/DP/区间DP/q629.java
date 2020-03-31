package LeetCode.DP.区间DP;

public class q629 {

    static int N = 1010;
    static int mod = (int) 1e9 + 7;

    // simplification by  deduction
    // dp[n][k] = dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k+1-n+1]+dp[n-1][k-n+1]
    //dp[n][k+1] = dp[n][k]+dp[n-1][k+1]-dp[n-1][k+1-n]
    // dp[i][j]=dp[i][j-1]+dp[i-1][j]-dp[i-1][j-i]
    public int kInversePairs(int n, int k) {
        int max = n * (n - 1) / 2;
        if (k > max || k < 0)
            return 0;
        if (k == max || k == 0)
            return 1;
        long[][] dp = new long[N][k + 1];
        dp[2][0] = 1;
        dp[2][1] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                long ans = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                if (j >= i)
                    // if subtract operation exists,the result may be negative so we must add mod before %mod;
                    ans = (ans - dp[i - 1][j - i] + mod) % mod;
                dp[i][j] = ans;
            }
        }
        return (int) dp[n][k] % mod;
    }
}
