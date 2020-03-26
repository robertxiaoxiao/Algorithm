package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/25/025
 * @description:
 */

import java.util.HashMap;

public class q115 {

    public int numDistinct(String s, String t) {

        HashMap<String, Integer> hm = new HashMap<>();
        return helper(s, t, hm);
    }

    /*

    dp[i][j]   :   [0,i-1]  contains [0,j]  count;

     */
    public int numDistinctUsingdp(String s, String t) {

        int n = s.length();
        int m = t.length();
        //pattern string
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                else
                    dp[i][j] = dp[i - 1][j];
            }

        return dp[n][m];
    }


    public int helper(String s, String t, HashMap<String, Integer> hm) {
        String temp = s + "_" + t;
        if (hm.containsKey(temp))
            return hm.get(temp);

        if (s.length() == 0 && t.length() != 0)
            return 0;

        if (t.length() == 0)
            return 1;

        int cnt = 0;
        for (int i = 0; i <= s.length() - t.length(); i++) {
            if (s.charAt(i) == t.charAt(0))
                cnt += helper(s.substring(i + 1), t.substring(1), hm);
        }
        hm.put(temp, cnt);
        return cnt;
    }
}
