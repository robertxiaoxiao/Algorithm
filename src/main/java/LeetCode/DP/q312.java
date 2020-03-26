package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/31/031
 * @description:
 */

import java.util.HashMap;

public class q312 {

    public int maxCoins(int[] nums) {
        //  notice  the num is not just one digit
        StringBuffer sb = new StringBuffer();
        HashMap<String, Integer> mem = new HashMap<>();
        for (int i : nums)
            sb.append(i);
        return helper(sb.toString(), mem);
    }

    public int dp(int[] nums) {

        int n = nums.length + 2;
        int[] narr = new int[n];
        narr[0] = 1;
        narr[n - 1] = 1;
        int t = 1;
        for (int num : nums)
            narr[t++] = num;

        int[][] dp = new int[n][n];
        for (int len = 1; len <= n - 2; len++)
            for (int left = 1; left <= n - len - 1; left++) {
                int right = left + len - 1;
                for (int k = left; k <= right; k++)
                    dp[left][right] = Math.max(dp[left][right], dp[left][k - 1] + narr[left - 1] * narr[k] * narr[right + 1] + dp[k + 1][right]);
            }

        // dp[i][j] =dp[i][k-1]+res[k]+dp[k+1][right]
        // res[k]= nums[k]*nums[right+1]*nums[left-1]    burst the right and left ballon and then burst it
        return dp[1][n - 2];
    }

    public int getScores(int[] s, int i, int st, int end) {
        int res = s[i];
        if (i - 1 >= st)
            res *= s[i - 1];
        if (i + 1 <= end)
            res *= s[i + 1];
        return res;
    }

    public int helper(String s, HashMap<String, Integer> mem) {
        if (s.length() == 0)
            return 0;
        if (mem.containsKey(s))
            return mem.get(s);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int temp = getScores(s, i) + helper(s.substring(0, i) + s.substring(i + 1), mem);
            max = Math.max(max, temp);
        }
        mem.put(s, max);
        return max;
    }

    public int getScores(String s, int i) {
        int res = s.charAt(i) - '0';
        if (i - 1 >= 0)
            res *= s.charAt(i - 1) - '0';
        if (i + 1 < s.length())
            res *= s.charAt(i + 1) - '0';

        return res;
    }


}
