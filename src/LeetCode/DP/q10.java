package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/24/024
 * @description:
 */

public class q10 {
    public boolean isMatch(String s, String p) {

        return helper(s, p, 0, 0);
    }

    public boolean MatchUsingdp(String s, String p) {

        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        // dp [i][j]  : [m-i,m] [n-j,n] whether it imatch

        // i the length  of remaining of s
        // j the length  of remaining of p
        dp[0][0] = true;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                boolean first_match = (p.charAt(n - j) == '.' || (p.charAt(n - j) == s.charAt(m - i)));
                if (n - j + 1 >= 0 && p.charAt(n - j + 1) == '*') {
                    dp[i][j] = (first_match && dp[i][j - 1]) || dp[i][j - 2];
                } else
                    dp[i][j] = first_match && dp[i - 1][j - 1];
            }

        return dp[m][n];
    }

    // backtracking
    public boolean helper(String s, String p, int i, int j) {

        if (i >= s.length() && j >= p.length())
            return true;

        if (i >= s.length()) {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*')
                return helper(s, p, i, j + 2);
            else
                return false;
        }

        if (j >= p.length())
            return false;

        if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*')
                return helper(s, p, i, j + 2) || helper(s, p, i + 1, j);
            else
                return helper(s, p, i + 1, j + 1);
        } else if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            return helper(s, p, i, j + 2);
        }
        return false;
    }
}