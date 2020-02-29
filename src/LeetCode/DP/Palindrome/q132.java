package LeetCode.DP.Palindrome;/*
 * @author: Robert
 * @date:  2020/2/24/024
 * @description:
 */

import java.util.Arrays;

public class q132 {

    public static void main(String[] args) {
        System.out.println(15 * 15 * 15);
    }

    public int minCutON3(String s) {
        //dp[i][j] : [i,j] is substring
        int n = s.length();
        if (n == 0 || n == 1)
            return 0;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        boolean[][] isPalindrome = new boolean[n][n];
        for (int len = 1; len <= n; len++)
            for (int t = 0; t + len - 1 < n; t++) {
                int j = t + len - 1;
                if (t == j)
                    isPalindrome[t][j] = true;
                else if (t + 1 == j) {
                    isPalindrome[t][j] = s.charAt(t) == s.charAt(j);
                } else {
                    isPalindrome[t][j] = s.charAt(t) == s.charAt(j) && isPalindrome[t + 1][j - 1];
                }
            }

        for (int len = 2; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (isPalindrome[i][j])
                    dp[i][j] = 0;
                else {
                    for (int cut = i; cut < j; cut++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][cut] + dp[cut + 1][j] + 1);
                    }
                }
            }
        return dp[0][n - 1];
    }

    public int minCutON(String s) {
        int n = s.length();
        if (n == 0 || n == 1)
            return 0;

        int[] dp = new int[n];
        boolean[][] isPalindrome = new boolean[n][n];

        for (int len = 1; len <= n; len++)
            for (int t = 0; t + len - 1 < n; t++) {
                int j = t + len - 1;
                if (t == j)
                    isPalindrome[t][j] = true;
                else if (t + 1 == j) {
                    isPalindrome[t][j] = s.charAt(t) == s.charAt(j);
                } else {
                    isPalindrome[t][j] = s.charAt(t) == s.charAt(j) && isPalindrome[t + 1][j - 1];
                }
            }

        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = i;
            for (int j = 0; j <= i; j++) {
                if (isPalindrome[j][i]) {
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

}
