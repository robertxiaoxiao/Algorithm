package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

import java.util.*;

public class q658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new LinkedList<>();

        int n = arr.length;
        int idx = find(arr, x);

        int left = idx;
        int right = idx + 1;
        if (left < 0) {
            right = -left - 1;
            left = right - 1;
        }
        for (int i = 0; i < k; i++) {
            if (left < 0)
                right++;
            else if (right >= n)
                left--;
            else if (x - arr[left] > arr[right] - x)
                right++;
            else
                left--;
        }
        for (int i = left + 1; i < right; i++)
            res.add(arr[i]);
        return res;
    }

    public int find(int[] arr, int x) {
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int mid = (i + j) >> 1;
            if (arr[mid] == x)
                return mid;
            else if (arr[mid] > x)
                j = mid - 1;
            else
                i = mid + 1;
        }
        return -i - 1;
    }

}
