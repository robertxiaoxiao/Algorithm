package LeetCode.knapsack;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KnapsackMixed {


    static int[] dp;
    static int N = 1010;

    static class Good {
        int t, w, v;

        public Good(int t, int w, int v) {
            this.t = t;
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

        for (int i = 0; i < n; i++) {
            sc.nextLine();
            int vi = sc.nextInt();
            int wi = sc.nextInt();
            int si = sc.nextInt();

            if (si > 0) {
                for (int k = 1; k <= si; k *= 2) {
                    si -= k;
                    goods.add(new Good(0, k * wi, k * vi));
                }
                if (si > 0)
                    goods.add(new Good(0, si * wi, si * vi));
            } else if (si == 0)
                goods.add(new Good(1, wi, vi));
            else
                goods.add(new Good(0, wi, vi));
        }

        for (Good good : goods) {
            if (good.t == 0) {
                for (int v = m; v >= good.v; v--)
                    dp[v] = Math.max(dp[v], dp[v - good.v] + good.w);
            } else {
                for (int v = good.v; v <= m; v++)
                    dp[v] = Math.max(dp[v], dp[v - good.v] + good.w);
            }
        }

        System.out.println(dp[m]);
    }

}
