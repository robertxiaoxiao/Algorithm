package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/23/023
 * @description:
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class q673 {

    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        // nums dp[i][2]:  maxLen contains dp[i][0];
        // exclusive : dp[i][1];

        int n = nums.length;
        int[] dp = new int[n + 1];
        int[] count = new int[n + 1];
        Arrays.fill(count, 1);
        Arrays.fill(dp, 1);
        int maxlen = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int cur = nums[i - 1];
            for (int j = 1; j < i; j++)
                if (cur > nums[j - 1]) {
                    if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    } else if (dp[j] >= dp[i]) {
                        //update
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                }
            maxlen = Math.max(maxlen, dp[i]);
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i] == maxlen)
                cnt += count[i];
        }

        return cnt;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        // nums dp[i][2]:  maxLen contains dp[i][0];
        // exclusive : dp[i][1];

        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int maxlen = dp[1];
        int cnt = 1;
        for (int i = 2; i <= n; i++) {
            int cur = nums[i - 1];
            int premax = Integer.MIN_VALUE;
            for (int j = 1; j < i; j++)
                if (cur > nums[j - 1]) {
                    premax = Math.max(premax, dp[j]);
                }

            dp[i] = (premax == Integer.MIN_VALUE ? 0 : premax) + 1;
            maxlen = Math.max(maxlen, dp[i]);
        }

        return cnt;
    }


}
