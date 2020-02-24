package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/24/024
 * @description:
 */

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class q264 {

    public int nthUglyNumber(int n) {

        if (n == 0)
            return 0;
        int[] nums = new int[n];

        nums[0] = 1;
        int l = 0;
        int k = 0;
        int m = 0;
        for (int i = 1; i < n; i++) {
            int c1 = nums[l] * 2;
            int c2 = nums[k] * 3;
            int c3 = nums[m] * 5;

            int c = Math.min(c1, Math.min(c2, c3));
            nums[i] = c;
            if (c == c1)
                l++;
            if (c == c2)
                k++;
            if (c == c3)
                m++;
        }

        return nums[n - 1];
    }
}
