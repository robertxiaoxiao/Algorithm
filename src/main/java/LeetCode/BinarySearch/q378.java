package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class q378 {

    // binary search by value
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        if (n == 0)
            return -1;
        int i = matrix[0][0];
        int j = matrix[n - 1][n - 1];
        while (i < j) {
            int mid = i + (j - i) / 2;
            int cnt = calculate(matrix, mid);
            if (cnt < k)
                i = mid + 1;
            else
                j = mid;
        }
        return i;
    }

    private int calculate(int[][] matrix, int num) {
        int i = 0;
        int j = matrix[0].length - 1;
        int cnt = 0;
        while (i < matrix.length && j >= 0) {
            if (num < matrix[i][j]) {
                j--;
            } else {
                i++;
                cnt += j + 1;
            }
        }

        return cnt;

    }


}
