package leetcodeTest.Week9;/*
 * @author: Robert
 * @date:  2020/1/5/005
 * @description:
 */

import java.util.HashMap;

public class q4 {

    public int minInsertions(String s) {
        if (judge(s))
            return 0;
        int n = s.length();
        int[][] dp = new int[n][n];

        // dp[i][j] =dp[i+1][j-1]
        // dp[i][j] =dp[i][j+1] dp[i+1][j];
        for (int len = 2; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                String temp = s.substring(i, j + 1);
                if (judge(temp))
                    dp[i][j] = 0;

                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1];
                else {
                    dp[i][j] = j - i;
                    if (j - 1 >= 0)
                        dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i][j]);
                    if (i + 1 < n)
                        dp[i][j] = Math.min(dp[i + 1][j] + 1, dp[i][j]);
                }
            }
        return dp[0][n - 1];
    }

    public int helper(String curs, int cur, int n, HashMap<String, Integer> mem) {
        if (judge(curs)) {
            return cur;
        }

        if (cur >= n)
            return n;

        if (mem.containsKey(curs))
            return mem.get(curs);

        int len = curs.length();
        int i = 0;
        int j = len - 1;
        int min = curs.length();
        if (curs.charAt(i) == curs.charAt(j))
            min = Math.min(min, helper(curs.substring(1, len - 1), cur, n, mem));
        else {
            int next = Math.min(helper(curs.substring(0, len - 1), cur + 1, n, mem), helper(curs.substring(1, len), cur + 1, n, mem));
            min = Math.min(min, next);
        }

        System.out.println(min);
        mem.put(curs, min);
        return min;
    }

    public boolean judge(String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

}
