package LeetCode.DP.区间DP;

import java.util.*;

public class Ac283 {

    static int N = 150;
    static int[][][] dp;

    /*
        In the precess of dividing the gap,we will get the maxval or the minval using both
        sides'value ,it will included by computing all pairs and update current gap;
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();

        int[] nodes = new int[2 * n];
        char[] edges = new char[2 * n];
        String s = in.nextLine();
        String[] t = s.split(" ");
        int idx = 0;
        for (int i = 0; i + 1 < t.length; ) {
            edges[idx] = t[i].charAt(0);
            nodes[idx] = Integer.parseInt(t[i + 1]);
            idx++;
            i += 2;
        }

        for (idx = n; idx < 2 * n; idx++) {
            edges[idx] = edges[idx % n];
            nodes[idx] = nodes[idx % n];
        }

        dp = new int[N][N][2];
        int max = Integer.MIN_VALUE;
        TreeSet<Integer> list = new TreeSet<>();
        for (int len = 1; len <= n; len++)
            for (int i = 0; i + len - 1 < 2 * n; i++) {
                int j = i + len - 1;
                // 0 maxval 1 minval
                dp[i][j][0] = Integer.MIN_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
                if (len == 1) {
                    dp[i][j][0] = nodes[i];
                    dp[i][j][1] = nodes[i];
                } else {
                    for (int k = i; k < j; k++)
                        if (edges[k + 1] == 'x') {
                            int maxl = dp[i][k][0];
                            int minl = dp[i][k][1];
                            int maxr = dp[k + 1][j][0];
                            int minr = dp[k + 1][j][1];
                            int l1 = maxl * maxr;
                            int l2 = maxl * minr;
                            int l3 = minl * maxr;
                            int l4 = minl * minr;
                            int curmax = Math.max(Math.max(l1, l2), Math.max(l3, l4));
                            int curmin = Math.min(Math.min(l1, l2), Math.min(l3, l4));
                            dp[i][j][0] = Math.max(dp[i][j][0], curmax);
                            dp[i][j][1] = Math.min(dp[i][j][1], curmin);
                        } else {
                            dp[i][j][0] = Math.max(dp[i][j][0], dp[i][k][0] + dp[k + 1][j][0]);
                            dp[i][j][1] = Math.min(dp[i][j][1], dp[i][k][1] + dp[k + 1][j][1]);
                        }
                }

                if (len == n) {
                    if (max > dp[i][j][0])
                        continue;
                    else if (max == dp[i][j][0])
                        list.add(i % n + 1);
                    else {
                        max = dp[i][j][0];
                        list.clear();
                        list.add(i % n + 1);
                    }
                }
            }

        System.out.println(max);
        StringBuffer sb = new StringBuffer();
        for (int i : list) {
            sb.append(i + " ");
        }
        String ans = sb.toString();
        System.out.printf("%s\n", ans.substring(0, ans.length() - 1));
    }

}
