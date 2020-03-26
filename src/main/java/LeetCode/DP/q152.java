package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/21/021
 * @description:
 */

public class q152 {

    public int maxProductMOL(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            dp[i][i] = nums[i];
        }
        for (int len = 2; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = dp[i][j - 1] * nums[j];
                max = Math.max(max, dp[i][j]);
            }
        return max;
    }

    //    public int maxProduct(int[] nums) {
//        int n = nums.length;
//        int[][] dp = new int[n][2];
//        int max = Integer.MIN_VALUE;
//        if (nums[0] > 0) {
//            dp[0][0] = nums[0];
//            dp[0][1] = Integer.MIN_VALUE;
//        } else {
//            dp[0][0] = Integer.MIN_VALUE;
//            dp[0][1] = nums[0];
//        }
//
//        for (int i = 1; i < n; i++) {
//            if (nums[i] < 0) {
//                if (dp[i - 1][0] == Integer.MIN_VALUE)
//                    dp[i][1] = nums[i];
//                else
//                    dp[i][1] = dp[i - 1][0] * nums[i];
//
//                if (dp[i - 1][1] == Integer.MIN_VALUE)
//                    dp[i][0] = Integer.MIN_VALUE;
//                else
//                    dp[i][0] = dp[i - 1][1] * nums[i];
//            } else if (nums[i] == 0) {
//                dp[i][0] = Integer.MIN_VALUE;
//                dp[i][1] = Integer.MIN_VALUE;
//                max = Math.max(max, 0);
//            } else {
//                if (dp[i - 1][0] == Integer.MIN_VALUE)
//                    dp[i][0] = nums[i];
//                else
//                    dp[i][0] = dp[i - 1][0] * nums[i];
//
//                if (dp[i - 1][1] == Integer.MIN_VALUE)
//                    dp[i][1] = Integer.MIN_VALUE;
//                else
//                    dp[i][1] = dp[i - 1][1] * nums[i];
//            }
//            max = Math.max(max, dp[i][0]);
//        }
//        max = Math.max(max, nums[0]);
//        return max;
//    }
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];
        int temp;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                temp = max;
                max = min;
                min = temp;
            }
            max = Integer.max(nums[i], nums[i] * max);
            min = Integer.min(nums[i], nums[i] * min);
            ans = Integer.max(ans, max);
        }
        return ans;
    }
}
