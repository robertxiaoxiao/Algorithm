package LeetCode.Array;

public class q75 {

    public void sortColors(int[] nums) {
        int c0 = 0, c1 = 0, c2 = nums.length - 1;

        while (c1 <= c2) {
            if (nums[c1] == 0) {
                swap(nums, c0, c1);
                c0++;
                c1++;
            } else if (nums[c1] == 1) {
                c1++;
                continue;
            } else {
                swap(nums, c1, c2);
                c2--;
            }
        }

    }

    public void swap(int[] n, int i, int j) {
        int temp = n[i];
        n[i] = n[j];
        n[j] = temp;
    }
}
