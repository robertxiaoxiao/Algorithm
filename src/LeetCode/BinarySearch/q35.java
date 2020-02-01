package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

public class q35 {
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        if (target <= nums[0])
            return 0;
        if (target > nums[j])
            return j + 1;

        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                i = mid + 1;
            else
                j = mid - 1;
        }

        return -1;
    }
}
