package LeetCode.TwoPointers;

import java.util.Arrays;

public class q16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int less = Integer.MIN_VALUE / 2;
        int large = Integer.MAX_VALUE / 2;
        for (int i = 0; i + 2 < n; i++) {
            int rest = target - nums[i];
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int cur = nums[j] + nums[k];
                if (cur < rest) {
                    less = Math.max(less, cur - rest);
                    j++;
                } else if (cur > rest) {
                    large = Math.min(large, cur - rest);
                    k--;
                } else if (cur == rest)
                    return target;
            }
        }

        return Math.abs(less) < large ? less : large + target;
    }

}
