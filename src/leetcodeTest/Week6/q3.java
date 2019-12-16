package leetcodeTest.Week6;/*
 * @author: Robert
 * @date:  2019/12/15/015
 * @description:
 */

import java.util.Arrays;

public class q3 {
    static int[][] dp = new int[300][300];

    public static int cal(int x1, int y1, int len) {
        int x2 = x1 + len - 1;
        int y2 = y1 + len - 1;
        // it cannot remain the edge
        // so we will replace the dp[x1][y1] with dp[x1-1][y1-1]
        return dp[x2][y2] + dp[x1 - 1][y1 - 1] - dp[x1 - 1][y2] - dp[x2][y1 - 1];
    }

    public int maxSideLengthUsingStaticArr(int[][] mat, int threshold) {
         // clear the static array
        for (int i = 0; i < mat.length; i++)
            Arrays.fill(dp[i], 0);
        int n = mat.length;
        int m = mat[0].length;
        dp[1][1] = mat[0][0];


        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i - 1][j - 1];

        for (int len = Math.min(n, m); len >= 1; len--)
            // and we get the node  from (1,1) to (n,m) because the first node is dp[1][1];
            for (int i = 1; i + len - 1 <= n; i++)
                for (int j = 1; j + len - 1 <= m; j++) {
                    if (cal(i, j, len) <= threshold)
                        return len;
                }
        return 0;
    }

    public static int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n + 1][m + 1];
        //int[][] dp =new int[n][m];
//        for (int i = 0; i < n+1; i++)
//            Arrays.fill(dp[i], 0);
        dp[0][0] = mat[0][0];
        print(mat);
        System.out.println();
        // if we append the dp arr ,we can process the division in  uniformed method
//        for (int i = 1; i <n; i++)
//                dp[i][0] = dp[i - 1][0]  + mat[i][0];
//
//        for (int i = 1; i <m; i++)
//            dp[0][i] = dp[0][i - 1]  + mat[0][i-1];
//
//        for (int i = 1; i <n; i++)
//            for (int j = 1; j <m; j++)
//                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i][j];

        print(dp);
        return 0;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        maxSideLength(mat, 1);
    }


    public int maxSideLengthTOL(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;
        int maxlen = Math.min(n, m);
        for (int len = 1; len < maxlen; len++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    if (judge(mat, i, j, len, threshold))
                        return len;

        return 0;

    }

    public static void print(int[][] mat) {

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++)
                System.out.print(mat[i][j] + "   ");
            System.out.println();
        }


    }

    public boolean judge(int[][] mat, int start, int end, int len, int threshold) {
        int sum = 0;
        for (int i = start; i < mat.length && i < start + len; i++)
            for (int j = end; j < mat[0].length && j < end + len; j++) {
                sum += mat[i][j];
                if (sum > threshold)
                    return false;
            }
        System.out.printf("%d %d %d %d", start, end, len, threshold);
        return true;

    }
}
