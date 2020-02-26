package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/26/026
 * @description:
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class q321 {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int[] best = new int[0];
        for (int i = Math.max(0, k - nums2.length);
             i <= Math.min(k, nums1.length); ++i)
            best = getmax(best, 0, maxNumber(maxNumber(nums1, i),
                    maxNumber(nums2, k - i)), 0);
        return best;

    }

    public int[] getmax(int[] n1, int s1, int[] n2, int s2) {
        int l1 = n1.length;
        int l2 = n2.length;
        int min = Math.min(l1, l2);
        for (int i = s1; i < l1; i++) {
            int j = i - s1 + s2;
            if (j == l2)
                return n1;
            if (n1[i] > n2[j])
                return n1;
            else if (n1[i] < n2[j])
                return n2;
        }
        return n2;
    }

    public int[] maxNumberStack(int[] nums, int k) {
        int to_pop = nums.length - k;
        int[] ans = new int[k];
        if (k == 0)
            return ans;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i : nums) {
            while (!stack.isEmpty() && i > stack.getLast() && to_pop > 0) {
                to_pop--;
                stack.removeLast();
            }
            stack.addLast(i);
        }
        for (int i = 0; i < k; i++)
            ans[i] = stack.get(i);
        return ans;
    }

    public int[] maxNumber(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[k];
        int i = 0;

        for (int j = 0; i < k && j < nums.length; j++) {
            while (n - j > k - i && i > 0 && ans[i - 1] < nums[j]) {
                i--;
            }
            if (i < k)
                ans[i++] = nums[j];
        }
        return ans;
    }

    public int[] maxNumber(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int[] ans = new int[l1 + l2];
        int idx = 0;
        int i = 0;
        int j = 0;
        while (i != l1 || j != l2) {
            if (i != l1 && j != l2)
                ans[idx++] = getmax(nums1, i, nums2, j) == nums1 ? nums1[i++] : nums2[j++];
            else if (i != l1)
                ans[idx++] = nums1[i++];
            else
                ans[idx++] = nums2[j++];
        }
        return ans;
    }
}
