package LeetCode.DP;

public class q920 {


    //    public int numMusicPlaylists(int N, int L, int K) {
//
//        // dp[i][l][j]  using i songs to construct l ending ai j;
//        //dp[i][l][j]=dp[i][l-k][j]+dp[i][l-1][&j]
//        long[][][] dp = new long[N][L + 1][N];
//        for (int i = 0; i < N; i++) {
//            dp[i][1][i] = 1;
//        }
//
//        for (int i = 0; i < N; i++) {
//            for (int l = 2; l <= L; l++)
//                for (int j = 0; j < i; j++) {
//                    if (l - K >= 0)
//                        dp[i][l][j] = dp[i][l - K][j];
//                    for (int t = 0; t < i; t++)
//                        dp[i][l][j] += dp[i][l - 1][t];
//                    dp[i][l][j] -= dp[i][l - 1][j];
//                }
//        }
//        long ans = 0;
//        for (long i : dp[N][L])
//            ans += i % (1e9 + 7);
//
//        return (int) ans;
//    }
//
    public int numMusicPlaylists(int n, int p, int m) {
        long[][] f = new long[p + 1][n + 1];
        int mod = (int) (1e9 + 7);
        f[0][0] = 1;
        for (int i = 1; i <= p; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i - 1][j - 1] * (n - j + 1) % mod;
                if (j > m) {
                    //  dp[i][j] : using j songs to construct the i length of list
                    f[i][j] = (f[i][j] + (f[i - 1][j] * (j - m) % mod)) % mod;
                }
            }
        }
        return (int) f[p][n];
    }
}
