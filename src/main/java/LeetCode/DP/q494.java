package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/25/025
 * @description:
 */

import java.util.HashMap;

public class q494 {
    public int findTargetSumWays(int[] nums, int S) {

        return helper(nums, S, 0, 0);
    }

    public int findTargetSumWaysUsingdp(int[] nums, int s) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(nums[0], 1);
        hm.put(-nums[0], hm.getOrDefault(-nums[0], 0) + 1);
        for (int i = 2; i <= n; i++) {
            HashMap<Integer, Integer> next = new HashMap<>();
            for (int k : hm.keySet()) {
                int key = nums[i - 1];
                next.put(k + key, hm.get(k) + next.getOrDefault(k + key, 0));
                next.put(k - key, hm.get(k) + next.getOrDefault(k - key, 0));
            }
            hm = new HashMap<>(next);
            // dp[i-1][cur+adder]+dp[i-1][cur-adder]
        }
        return hm.getOrDefault(s, 0);
    }

    public int helperI(int[] nums, int target, int idx, int sum) {

        if (idx == nums.length && sum == target)
            return 1;

        if (idx == nums.length)
            return 0;

        int pos = helper(nums, target, idx + 1, sum + nums[idx]);
        int neg = helper(nums, target, idx + 1, sum - nums[idx]);

        return pos + neg;

    }

    public int helper(int[] nums, int target, int idx, int sum) {

        if (idx < 0 && sum == target)
            return 1;

        if (idx < 0)
            return 0;

        int pos = helper(nums, target, idx - 1, sum + nums[idx]);
        int neg = helper(nums, target, idx - 1, sum - nums[idx]);

        return pos + neg;
    }
}
