package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/19/019
 * @description:
 */

public class HouseRobber {
    // house in line
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int[] dp = new int[n + 1];
        //dp[i] : the maxvalue after robbing  the i house (i should be robbed)
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }

    // house at circle
    public int robInCircle(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        if (n == 1)
            return nums[0];

        int[] p1 = new int[n - 1];
        int[] p2 = new int[n - 1];
        // 1 2 3 4 5
        // 1 2 3 4  house 4 should be robbed
        // 2 3 4 5  house 5 should be robbed
        // that is the constrain the first line robbery algorithm  needs
        for (int i = 0; i < n - 1; i++) {
            p1[i] = nums[i];
            p2[i] = nums[i + 1];
        }
        return Math.max(rob(p1), rob(p2));
    }
}
