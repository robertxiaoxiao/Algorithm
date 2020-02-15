package LeetCode.DivedeConquer;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q493 {

    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        return mergesort(nums, 0, nums.length - 1);
    }




    int[] temp;

    public int mergesort(int[] nums, int l, int r) {
        if (l >= r)
            return 0;
        int mid = (l + r) / 2;
        int l1 = mergesort(nums, l, mid);
        int r1 = mergesort(nums, mid + 1, r);

        int cnt = l1 + r1;

        int j = mid + 1; //start of right subarray
        for (int i = l; i <= mid; i++) { // for every element of left subarray
            while (j <= r && (double) nums[i] > (double) 2 * nums[j]) // To handle integer max case
                j++;
            //now we found less than half element for ith element
            cnt += j - (mid + 1);
        }

        int i = l;
        j = mid + 1;
        int idx = i;
        while (i <= mid && j <= r) {
            if (nums[i] < nums[j]) {
                temp[idx++] = nums[i++];
            } else {
                temp[idx++] = nums[j++];
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


    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
