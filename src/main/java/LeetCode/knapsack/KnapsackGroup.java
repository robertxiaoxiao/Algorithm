package LeetCode.knapsack;

import java.util.Scanner;

public class KnapsackGroup {

    static int N;
    static int[] dp = new int[N];
    static int[] ws = new int[N];
    static int[] vs = new int[N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            sc.nextLine();
            int group = sc.nextInt();

            for (int g = 0; g < group; g++) {
                sc.nextLine();
                int vi = sc.nextInt();
                int wi = sc.nextInt();
                vs[g] = vi;
                ws[g] = wi;
            }

            for (int v = m; v >= 0; v--)
                 /*
              select in group
             */
                for (int g = 1; g < group; g++)
                    if (v >= vs[g])
                        dp[v] = Math.max(dp[v], dp[v - vs[g]] + ws[g]);
        }

        System.out.println(dp[m]);
    }


}
