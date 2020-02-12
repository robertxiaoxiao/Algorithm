package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/10/010
 * @description:
 */

import java.util.Arrays;

public class q410 {

    // using dp
    public int splitArrayUsingdp(int[] nums, int m) {

        // dp[i][j]  : the   min of front j elements in i groups
        int n = nums.length;
        int[][] dp = new int[m + 1][n];
        for (int i = 0; i <= m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        dp[1][0] = nums[0];
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[1][i] = dp[1][i - 1] + nums[i];
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 2; i <= m; i++)
            for (int j = i - 1; j < n; j++) {
                for (int k = 0; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k], sums[j] - sums[k]));
                }
            }
        return dp[m][n - 1];
    }

    public int splitArrayUsingmemrec(int[] nums, int m) {
        int n = nums.length;
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++)
            sums[i] = sums[i - 1] + nums[i];
        int[][] dp = new int[m + 1][n];
        return memrec(sums, m, m, n - 1, dp);
    }

    public int memrec(int[] sums, int m, int i, int j, int[][] dp) {
        if (i == 0)
            return Integer.MAX_VALUE;
        if (i == 1)
            return dp[i][j] = sums[j];

        if (dp[i][j] != 0)
            return dp[i][j];

        int ans = Integer.MAX_VALUE;

        for (int k = 0; k < j; k++)
            ans = Math.min(ans, Math.max(memrec(sums, m, i - 1, k, dp), sums[j] - sums[k]));

        return dp[i][j] = ans;
    }

    public int splitArrayUsingBS(int[] nums, int m) {
        int n = nums.length;
        int max = -1;
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(nums[i], max);
            sums[i] = sums[i - 1] + nums[i];
        }
        int l = max;
        int r = sums[n - 1] + 1;
        while (l < r) {
            int mid = (l + r) / 2;
            int g = divide(mid, nums, m);
            if (g > m)
                l = mid + 1;
            else
                r = mid;
            /*
            if (g<=m)
                r= mid;
            else
                l = mid+1;
             */
        }
        return l;
    }

    public int divide(int mid, int[] nums, int m) {
        int g = 0;
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i + 1 < n && sum <= mid && sum + nums[i + 1] > mid) {
                sum = 0;
                g++;
            }
            if (sum <= mid && i == n - 1)
                g++;
        }

        return g;
    }


}
