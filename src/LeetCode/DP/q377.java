package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/25/025
 * @description:
 */

import java.util.Arrays;
import java.util.HashMap;

public class q377 {

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        HashMap<Integer, Integer> mem = new HashMap<>();
        return helper(nums, target, mem);
    }

    public int combinationSum4Usingdp(int[] nums, int target) {

        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = nums[0]; i <= target; i++) {
            for (int k : nums)
                if (i - k >= 0)
                    dp[i] += dp[i - k];
        }

        return dp[target];
    }

    public int helper(int[] nums, int target, HashMap<Integer, Integer> mem) {

        if (mem.containsKey(target))
            return mem.get(target);

        if (target == 0)
            return 1;

        int cnt = 0;
        for (int i : nums) {
            if (i <= target)
                cnt += helper(nums, target - i, mem);
        }
        mem.put(target, cnt);
        return cnt;
    }

}
