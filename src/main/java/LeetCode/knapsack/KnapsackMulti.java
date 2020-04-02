package LeetCode.knapsack;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KnapsackMulti {


    static int[] dp;
    static int N = 1010;

    static class Good {
        int w, v;

        public Good(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        dp = new int[N];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Good> goods = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            sc.nextLine();
//            int vi = sc.nextInt();
//            int wi = sc.nextInt();
//            int si = sc.nextInt();
//            for (int v = m; v >= vi; v--)
//                for (int k = 1; k * vi <= v && k <= si; k++)
//                    dp[v] = Math.max(dp[v], dp[v - k * vi] + k * wi);
//        }
        for (int i = 0; i < n; i++) {
            sc.nextLine();
            int vi = sc.nextInt();
            int wi = sc.nextInt();
            int si = sc.nextInt();

            for (int k = 1; k <= si; k *= 2) {
                si -= k;
                goods.add(new Good(k * wi, k * vi));
            }
            if (si > 0)
                goods.add(new Good(si * wi, si * vi));
        }

        for (Good good : goods) {
            for (int v = m; v >= good.w; v--)
                if (v >= good.v)
                    dp[v] = Math.max(dp[v], dp[v - good.v] + good.w);
        }

        System.out.println(dp[m]);
    }

    // to optimize using binary deviation

    public int solve(int n, int m, int[] ws, int[] vs, int[] ss) {

        dp = new int[N];
        for (int i = 0; i < n; i++)
            for (int v = m; v >= vs[i]; v--) {
                for (int k = 1; k * vs[i] <= v; k++) {
                    dp[v] = Math.max(dp[v], dp[v - k * vs[i]] + k * ws[i]);
                    //  dp[i][v]= max(dp[i-1][v] ,dp[i-1][v-v[i]]+w[i])
                }
            }
        return dp[m];
    }

}
