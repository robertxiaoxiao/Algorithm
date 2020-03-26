package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/20/020
 * @description:
 */

import java.util.Arrays;

public class q583 {
    public int minDistance(String word1, String word2) {
        //keep[i]
        return helper(word1, word2);
    }

    public int minDistanceUsingdp(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] mem = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(mem[i], Integer.MAX_VALUE);
        return memDp(a, b, n - 1, m - 1, mem);
    }

    public int memDp(String a, String b, int i, int j, int[][] mem) {

        if (i == -1)
            return j + 1;

        if (j == -1)
            return i + 1;

        if (mem[i][j] != Integer.MAX_VALUE)
            return mem[i][j];

        if (a.charAt(i) == b.charAt(j))
            return mem[i][j] = backT(a, b, i - 1, j - 1);

        int removeA = backT(a, b, i - 1, j);
        int removeB = backT(a, b, i, j - 1);

        return mem[i][j] = Math.min(removeA, removeB) + 1;

    }

    public int helper(String a, String b) {
        if (a.length() == 0)
            return b.length();
        if (b.length() == 0)
            return a.length();
        if (a.equalsIgnoreCase(b))
            return 0;

        if (a.charAt(0) == b.charAt(0)) {
            return helper(a.substring(1), b.substring(1));
        } else {
            int removeA = helper(a.substring(1), b) + 1;
            int removeB = helper(a, b.substring(1)) + 1;
            return Math.min(removeA, removeB);
        }
    }


    public int backT(String a, String b, int i, int j) {

        if (i == -1)
            return j + 1;

        if (j == -1)
            return i + 1;

        if (a.charAt(i) == b.charAt(j))
            return backT(a, b, i - 1, j - 1);

        int removeA = backT(a, b, i - 1, j);
        int removeB = backT(a, b, i, j - 1);
        return Math.min(removeA, removeB) + 1;
    }


    public int dp(String a, String b) {

        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }
            /*        t  e  a
                   0  1  2  3
                 e 1  2  1  2
                 a 2  3  2  2
                 c 3  4  3  2
             */

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }

        return dp[n][m];
    }

}
