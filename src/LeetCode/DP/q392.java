package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/26/026
 * @description:
 */

public class q392 {
    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0, k = 1; i < t.length(); i++) {
            if (k <= n && t.charAt(i) == s.charAt(k - 1)) {
                dp[k] = dp[k - 1];
                k++;
            }
        }
        return dp[n];
    }
}
