package LeetCode.GameTherory;

public class q486 {

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;

        return helper(nums, 0, n - 1) > 0;
    }

    public boolean PredictTheWinnerDP(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1)
                    dp[i][j] = nums[i];
                else
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                // dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
                // it can be reduced to O(N)
            }
        }

        return dp[0][n - 1] >= 0;
    }

    public int helper(int[] nums, int i, int j) {
        if (i == j)
            return nums[i];
        int left = nums[i] - helper(nums, i + 1, j);
        int right = nums[j] - helper(nums, i, j - 1);
        return Math.max(left, right);
    }
}
