package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/25/025
 * @description:
 */

import java.util.HashMap;

public class q712 {

    public int minimumDeleteSum(String s1, String s2) {

        HashMap<String, Integer> mem = new HashMap<>();
        return helper(s1, s2, mem);
    }

    public int minimumDeleteSumUsingdp(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        dp[0][0] = 0;
        for (int i = 1; i <= n; i++)
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);

        for (int i = 1; i <= m; i++)
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                if (s1.charAt(i - 1) != s2.charAt(j - 1))
                    dp[i][j] = Math.min(dp[i][j - 1] + s2.charAt(j - 1), dp[i - 1][j] + s1.charAt(i - 1));
                else
                    dp[i][j] = dp[i - 1][j - 1];
        return dp[n][m];


    }

    public int helper(String s1, String s2, HashMap<String, Integer> mem) {
        String temp = s1 + '_' + s2;
        if (mem.containsKey(temp))
            return mem.get(temp);

        if (s1.equalsIgnoreCase(s2))
            return 0;
        int cnt = 0;
        if (s1.length() == 0) {
            for (char c : s2.toCharArray())
                cnt += c;
            return cnt;
        }

        if (s2.length() == 0) {
            for (char c : s1.toCharArray())
                cnt += c;
            return cnt;
        }

        if (s1.charAt(0) == s2.charAt(0))
            cnt = helper(s1.substring(1), s2.substring(1), mem);
        else {
            cnt = Math.min(helper(s1, s2.substring(1), mem) + s2.charAt(0), helper(s1.substring(1), s2, mem) + s1.charAt(0));
        }
        mem.put(temp, cnt);
        return cnt;

    }
}
