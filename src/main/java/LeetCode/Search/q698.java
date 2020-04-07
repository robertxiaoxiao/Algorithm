package LeetCode.Search;/*
 * @author: Robert
 * @date:  2020/1/5/005
 * @description:
 */

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class q698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += nums[i];
        if (sum % k != 0)
            return false;
        int target = sum / k;
        Arrays.sort(nums);
        boolean[] visited = new boolean[n];
        return dfs(k, 0, target, nums, visited, 0);
    }

    public boolean dfs(int k, int cursum, int target, int[] nums, boolean[] visited, int idx) {
        if (k == 0)
            return true;
        if (cursum == target)
            return dfs(k - 1, 0, target, nums, visited, 0);

        for (int i = idx; i < nums.length; i++) {
            if (visited[i])
                continue;
            if (cursum + nums[i] > target)
                continue;
            visited[i] = true;
            // using it
            if (dfs(k, cursum + nums[i], target, nums, visited, i))
                return true;

            visited[i] = false;
        }
        return false;
    }

    public boolean explicitSol(int[] nums, int k) {

        Arrays.sort(nums);
        int sum = 0;
        for (int i : nums)
            sum += i;

        if (sum % k != 0)
            return false;
        int target = sum / k;
        int row = nums.length - 1;
        if (nums[row] > target)
            return false;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }

        int[] groups = new int[k];
        return search(groups, row, target, nums);
    }

    public boolean search(int[] groups, int row, int target, int[] nums) {
        if (row < 0)
            return true;
        int v = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, target, nums))
                    return true;
                groups[i] -= v;
            }
            if (groups[i] == 0)
                break;
        }
        return false;
    }

}
