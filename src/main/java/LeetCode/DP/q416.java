package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/25/025
 * @description:
 */

import java.util.Arrays;

public class q416 {

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int i : nums)
            sum += i;

        if (sum % 2 == 1)
            return false;

        int part = sum / 2;
        Arrays.sort(nums);

        return helper(nums, part, 0, 0);

    }

    //  maybe 0-1package
    public boolean helper(int[] nums, int target, int idx, int sum) {

        if (sum > target)
            return false;
        if (sum == target)
            return true;

        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1])
                continue;
            if (helper(nums, target, i + 1, sum + nums[i]))
                return true;
        }
        return false;
    }


}
