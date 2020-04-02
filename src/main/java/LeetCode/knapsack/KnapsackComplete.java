package LeetCode.knapsack;

public class KnapsackComplete {
    static int N;
    static int[] dp = new int[N];

    public int solve(int n, int[] ws, int[] vs, int m) {
        // init : dp[i]=0;

        for (int i = 1; i <= n; i++) {
            for (int v = ws[i]; v <= m; v++)
                dp[v] = Math.max(dp[v], dp[v - ws[i]] + vs[i]);
            /*
             1 naive method:
                for (int v = ws[i]; v <= m; v++)
                  for (int k=0;k*w[i]<=v;k++)
                    dp[v] = Math.max(dp[v], dp[v - k *ws[i]] + k* vs[i]);
             2 optimization
                dp[i][x + kvi] = max(dp[i-1][x+kvi],dp[i-1][x+(k-1)vi] + wi,.., dp[i-1][x] + kwi)
                dp[i][x + (k-1)vi] = max(dp[i-1][x+(k-1)vi],.., dp[i-1][x] + （k-1）wi)
                dp[i][x + k * vi] = max(dp[i-1][x + kvi],wi + dp[i][x + (k-1)vi])
            */
        }

        return dp[m];
        /*
          for (int i = 1; i <= n; i++)
          dp[i]=-inf;
          for (int v=1;v<=m;v++)
          ans= max(ans,dp[v]);
         */
    }

}
