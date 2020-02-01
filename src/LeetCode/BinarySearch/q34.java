package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

public class q34 {

    public int[] searchRange(int[] nums, int target) {
        return new int[]{findrangeleft(nums, target), findrangeright(nums, target)};
    }

    public int findrangeleft(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == target) {
                int k = mid - 1;
                while (k >= 0 && nums[k] == target)
                    k--;
                return k + 1;
            } else if (nums[mid] < target)
                i = mid + 1;
            else
                j = mid - 1;
        }
        return -1;
    }

    public int findrangeright(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;

            if (nums[mid] == target) {
                int k = mid + 1;
                while (k < nums.length && nums[k] == target)
                    k++;
                return k - 1;
            } else if (nums[mid] < target)
                i = mid + 1;
            else
                j = mid - 1;
        }
        return -1;
    }

}
