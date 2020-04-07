package LeetCode.Greedy;

public class q55 {
    public boolean canJump(int[] nums) {

        int pmax = nums[0];
        for (int i = 0; i <= pmax && i < nums.length; i++)
            pmax = Math.max(pmax, i + nums[i]);

        return pmax >= nums.length - 1;
    }
}
