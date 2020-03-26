package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/24/024
 * @description:
 */

import java.util.HashMap;

public class q279 {

    HashMap<Integer, Integer> hm;

    public int numSquaresDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = n + 1;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public int numSquares(int n) {
        hm = new HashMap<>();
        return helper(n);
    }

    public int helper(int n) {
        if (hm.containsKey(n))
            return hm.get(n);
        if (n < 0)
            return Integer.MAX_VALUE / 2;
        if (n == 0)
            return 0;
        int min = Integer.MAX_VALUE / 2;
        for (int i = 1; i * i <= n; i++) {
            min = Math.min(min, helper(n - i * i) + 1);
        }
        hm.put(n, min);
        return min;
    }

}
