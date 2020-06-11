package LeetCode.DP;

public class q1420 {

    static int N = 60;
    static int M = 110;
    static int K = 60;
    static int mod = (int) (1e9 + 7);

    public int numOfArrays(int n, int m, int k) {

        long[][][] dp = new long[N][M][K];
        for (int i = 1; i <= m; i++)
            dp[1][i][1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++)
                for (int tk = 1; tk <= k; tk++) {
                    for (int v = 1; v <= m; v++) {
                        int nv = j;
                        int nk = tk;
                        if (v > j) {
                            nv = v;
                            nk++;
                        }
                        dp[i][nv][nk] = (dp[i][nv][nk] + dp[i - 1][j][tk]) % mod;
                    }
                }
        }
        long ans = 0;
        for (int v = 1; v <= m; v++)
            ans = (ans + dp[n][v][k]) % mod;

        return (int) (ans);

    }
}
