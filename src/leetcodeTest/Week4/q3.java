package leetcodeTest.Week4;/*
 * @author:
 * @date:  2019/12/1/001
 * @description:
 */

public class q3 {
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
