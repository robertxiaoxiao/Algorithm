package LeetCode.Array;

public class q55 {

    public boolean canJump(int[] nums) {

        int pmax = nums[0];
        for (int i = 0; i <=pmax && i < nums.length; i++)
            pmax = Math.max(pmax, i + nums[i]);

        return pmax >= nums.length - 1;

        //   return helper(0, nums.length, nums);
    }

    public boolean helper(int i, int n, int[] nums) {
        if (i >= n - 1)
            return true;

        if (nums[i] + i >= n - 1)
            return true;

        for (int k = 1; k <= nums[i]; k++)
            if (helper(i + k, n, nums))
                return true;
        return false;
    }

}
