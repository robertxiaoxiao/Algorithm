package LeetCode.Array;

import java.util.HashMap;
import java.util.HashSet;

public class m30 {

    HashSet<Integer> hset = new HashSet<>();

    public boolean isHappy(int n) {
        hset = new HashSet<>();
        return helper(n);
    }

    public boolean helper(int n) {
        if (hset.size() > 100)
            return false;
        if (hset.contains(n))
            return false;

        hset.add(n);
        int ans = 0;
        while (n != 0) {
            int d = n % 10;
            n = (n - d) / 10;
            ans += d * d;
        }

        if (ans == 1)
            return true;

        return helper(ans);
    }

    public int maxSubArray(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = Math.max(nums[0], 0);
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
