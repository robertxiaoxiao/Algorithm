package LeetCode.knapsack;

import java.util.Scanner;

public class Knapsack01 {

    static int N;
    static int[] dp = new int[N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            sc.nextLine();
            int vi = sc.nextInt();
            int wi = sc.nextInt();
            for (int v = m; v >= vi; v--)
                dp[v] = Math.max(dp[v], dp[v - vi] + wi);
        }
        System.out.println(dp[m]);

        /*
         for (int i = 0; i < n; i++) {
            sc.nextLine();
            int vi = sc.nextInt();
            int mi = sc.nextInt();
            int wi = sc.nextInt();
            for (int v = V; v >= vi; v--)
            for (int w = m; w >= mi; w--)
                dp[v][w] = Math.max(dp[v][w], dp[v - vi][w-mi] + wi);
        }
        System.out.println(dp[V][m]);
         */
    }

    public int solve(int n, int[] ws, int[] vs, int m) {
        // init : dp[i]=0;
        for (int i = 1; i <= n; i++) {
            for (int v = m; v >= ws[i]; v--)
                //dp[i][v] = max(dp[i][v],d[i-1][v-v[i]]+w[i])
                dp[v] = Math.max(dp[v], dp[v - ws[i]] + vs[i]);
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
