package LeetCode.BinarySearch;

public class q240 {

    public boolean searchMatrix(int[][] matrix, int target) {

        int n = matrix.length;
        if (n == 0)
            return false;
        int m = matrix[0].length;
        int i = 0;
        int j = m - 1;
        while (i < n && j >= 0) {
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] > target)
                j--;
            else
                i++;
        }
        return false;
    }

}
