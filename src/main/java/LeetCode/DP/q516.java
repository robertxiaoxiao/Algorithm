package LeetCode.DP;

public class q516 {

    public int longestPalindromeSubseq(String s) {

        // dp[i][j] = max( dp[i+1][j-1]+2,dp[i][j-1],dp[i+1][j]
        int n = s.length();
        if (n == 1)
            return 1;
        if (n == 2)
            return s.charAt(0) == s.charAt(1) ? 2 : 1;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i + 1 < n)
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
        }

        for (int len = 3; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        return dp[0][n - 1];
    }

    public int longestPalindromeSubseqUsingLCS(String s) {
        String t = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[dp.length - 1][dp.length - 1];
    }


}
