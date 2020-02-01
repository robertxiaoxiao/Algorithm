package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

public class q74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0)
            return false;
        if (n == 0)
            return false;
        int i = 0;
        int j = m - 1;

        while (i <= j) {
            int mid = (i + j) >> 1;
            int left = matrix[mid][0];
            int right = matrix[mid][n - 1];
            if (target < left) {
                j = mid - 1;
            } else if (target > right) {
                i = mid + 1;
            } else {
                int rl = 0;
                int rr = n - 1;
                while (rl <= rr) {
                    int rm = (rl + rr) >> 1;
                    int key = matrix[mid][rm];
                    if (key == target)
                        return true;
                    else if (key < target)
                        rl = rm + 1;
                    else
                        rr = rm - 1;
                }
                return false;
            }
        }
        return false;
    }


}
