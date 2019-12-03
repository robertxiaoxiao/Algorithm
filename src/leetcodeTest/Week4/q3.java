package leetcodeTest.Week4;/*
 * @author:
 * @date:  2019/12/1/001
 * @description:
 */

/*
  leetcode   221    Maximal Square
  leetcode   85    Maximal Rectangle
  Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 */
public class q3 {

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        if (n == 0)
            return 0;
        int m = matrix[0].length;

        int[][] dplength = new int[n][m];
        int[][] dpwidth = new int[n][m];
        for (int k = 0; k < n; k++) {
            if (matrix[k][0] == '0')
                dplength[k][0] = 0;
            else {
                dplength[k][0] = 1;
                dpwidth[k][0] = 1;
            }
        }
        for (int k = 0; k < m; k++) {
            if (matrix[0][k] == '0')
                dpwidth[0][k] = 0;
            else {
                dpwidth[0][k] = 1;
                dplength[0][k] = 1;
            }
        }
        int max = 0;
        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    dplength[i][j] = dplength[i - 1][j] + 1;
                    dpwidth[i][j] = dpwidth[i][j - 1] + 1;
                    int curlength = dplength[i][j];
                    int curwidth = dpwidth[i][j];
                    for (int k = i; k >= i - curwidth && k >= 0; k--)
                        dplength[i][j] = Math.min(dplength[i][j], dplength[k][j]);
                    curlength = dplength[i][j];
                    for (int k = j; k >= j - curlength && k >= 0; k--)
                        dpwidth[i][j] = Math.min(dpwidth[i][j], dpwidth[i][k]);
                } else {
                    dplength[i][j] = 0;
                    dpwidth[i][j] = 0;
                }

                if (dplength[i][j] * dpwidth[i][j] > max)
                    max = dplength[i][j] * dpwidth[i][j];
            }
        return max;

    }


    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        if (n == 0)
            return 0;
        int m = matrix[0].length;
        // dp[i][j] : (i,j) is the right bottom corner of the matrix ,dp[i][j] is the max side length  of the matrix ;
        int[][] dp = new int[n][m];
        int max = Integer.MIN_VALUE;
        for (int k = 0; k < n; k++) {
            if (matrix[k][0] == '0')
                dp[k][0] = 0;
            else {
                dp[k][0] = 1;
                max = 1;
            }
        }
        // notice the edge condition
        for (int k = 0; k < m; k++) {
            if (matrix[0][k] == '0')
                dp[0][k] = 0;
            else {
                dp[0][k] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1')
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                if (dp[i][j] > max)
                    max = dp[i][j];
            }
        return max * max;
    }

    public static int countSquares(int[][] matrix) {
        int row = matrix.length;
        if (row == 0)
            return 0;
        int col = matrix[0].length;
        int minLen = Math.min(row, col);
        int ans = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                for (int l = 1; l <= minLen; l++) {
                    if (judge(matrix, i, j, l)) {
                        System.out.printf("%d %d %d\r\n", i, j, l);
                        ++ans;
                    }
                }
        return ans;
    }

    private static boolean judge(int[][] mat, int i, int j, int l) {
        int k = 0;
        int m = 0;
        for (k = i - l + 1; k <= i; k++)
            for (m = j - l + 1; m <= j; m++)
                if (k < 0 || m < 0 || mat[k][m] == 0)
                    return false;
        return true;
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        /*
            NOTICE :not the same
         */
        int i = 0;
        int j = 0;
        for (; i < 10; i++)
            for (; j < 5; j++)
                System.out.printf("%d   %d\r\n", i, j);

        for (int l = 0; l < 10; l++)
            for (int k = 0; k < 5; k++)
                System.out.printf("%d   %d\r\n", l, k);
        //  System.out.println(countSquares(arr));
    }
}
