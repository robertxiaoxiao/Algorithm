package LeetCode.DivedeConquer;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q775 {
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        temp = new int[n];
        int local = 0;
        for (int i = 0; i < n; i++)
            if (i + 1 < n && nums[i] > nums[i + 1])
                local++;
        int global = mergesort(nums, 0, nums.length - 1);
        return global == local;
    }

    public boolean isIdealPermutationProperty(int[] nums){
        for (int i = 0; i < nums.length; i++)
            if (Math.abs(nums[i] - i) > 1)
                return false;
        return true;
    }

    int[] temp;

    public int mergesort(int[] nums, int l, int r) {
        if (l >= r)
            return 0;
        int mid = (l + r) / 2;
        int cnt = mergesort(nums, l, mid) + mergesort(nums, mid + 1, r);
        int i = l;
        int j = mid + 1; //start of right subarray
        int idx = i;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                temp[idx++] = nums[i++];
            } else {
                /* cnt += j - mid;
                 *  that's error
                 * [1 2 5][0 3 4]
                 */
                temp[idx++] = nums[j++];
                cnt += mid - i + 1;
            }
        }

        while (i <= mid)
            temp[idx++] = nums[i++];
        while (j <= r)
            temp[idx++] = nums[j++];

        for (int k = l; k <= r; k++)
            nums[k] = temp[k];

        return cnt;
    }


}
