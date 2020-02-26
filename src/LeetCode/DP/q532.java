package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/26/026
 * @description:
 */

import java.util.HashMap;
import java.util.Map;

public class q532 {

    public boolean checkSubarraySumON2(int[] nums, int k) {

        int n = nums.length;
        if (n < 2)
            return false;
        int[] dp = new int[n + 1];
        if (k == 0) {
            for (int i = 0; i < n; i++)
                if (i + 1 < n && nums[i] == 0 && nums[i] == nums[i + 1])
                    return true;
            return false;
        }
        k = Math.abs(k);
        dp[1] = nums[0];
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }

        for (int i = 1; i <= n; i++)
            for (int j = i + 1; j <= n; j++) {
                int temp = dp[j] - dp[i - 1];
                if (temp % k == 0 && temp != 0)
                    return true;
            }
        return false;
    }


    // Congruence theorem  ：
    // 给定一个正整数m，如果两个整数a和b满足a-b能够被m整除，
    // 即(a-b)/m得到一个整数，
    // 那么就称整数a与b对模m同余，记作a≡b(mod m)。
    // 对模m同余是整数的一个等价关系。
    public static boolean checkSubarraySum(int[] nums, int k) {

        int sum = 0;

        Map<Integer, Integer> table = new HashMap<>();

        table.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (k != 0)
                sum %= k;

            if (table.containsKey(sum)) {
                if (i - table.get(sum) > 1)
                    return true;
            } else table.put(sum, i);
        }

        return false;
    }
}
