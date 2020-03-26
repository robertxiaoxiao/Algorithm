package LeetCode.DP;

public class q376 {


    public int wiggleMaxLengthON3(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return n;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                //dp[i][j]   dp[j][k] +1
                int dif = nums[i] - nums[j];
                if (dif == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = 2;
                for (int k = 0; k < j; k++) {
                    if ((nums[j] - nums[k]) * dif < 0)
                        dp[i][j] = Math.max(dp[i][j], dp[j][k] + 1);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public int wiggleMaxLength(int[] nums) {

        if (nums.length == 0) return 0;

        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        up[0] = 1;
        down[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }

        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
}
