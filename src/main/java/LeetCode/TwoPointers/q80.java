package LeetCode.TwoPointers;

public class q80 {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int len = n;
        int idx = 0;
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && nums[j] == nums[i])
                j++;

            len -= Math.max(0, j - i - 2);

            for (int t = i; t < j && t < i + 2; t++)
                nums[idx++] = nums[t];

            i = j;
        }
        return len;
    }

}
