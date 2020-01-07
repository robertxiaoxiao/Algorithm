package leetcodeTest.Tricks;/*
 * @author: Robert
 * @date:  2019/12/15/015
 * @description:
 */

public class SuffixNumCalMatSquere {

    static int[][] dp;

    // one dimension ,we can  go O(n) to prepare the sum of (0,i) ,and then we can get the sum of arr interval[x,y];
    public int calArr(int x, int y, int[] arr) {
        int[] dp = new int[arr.length];
        if (arr.length == 0)
            return -1;
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++)
            dp[i] = dp[i - 1] + arr[i];

        return dp[x] - dp[y] + arr[y];

    }

    /*    (0,0)
     *
     *                (i-1,j-1)
     *                           (i,j)
     *       dp[i][j]: the area of squares  left-top point(0,0) and the right-down point(i,j)
     *       dp[i][j] =dp[i-1,j]+dp[i][j-1]-dp[i-1][j-1]+mat[i][j] ;
     */
    public int calMat(int x, int y, int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        dp = new int[n+1][m+1];
        for (int i = 1; i <=n; i++)
            dp[i][1] = dp[i - 1][0] + mat[i-1][0];
        for (int j = 1; j <=m; j++)
            dp[1][j] = dp[0][j - 1] + mat[0][j-1];

        for (int i = 1; i <n; i++)
            for (int j = 1; j <m; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i-1][j-1];
            /*
       (0,0)

                  (x1,y1)            (x1,y2)


                  (x2,y1)             (x2,y2)
        //  dp[x1][y2]+dp[x2][y1]-dp[x1][y1]+ans=dp[x2][y2];
        ans = dp[x2][y2] + dp[x1-1][y1-1] - dp[x1-1][y2] - dp[x2][y1-1];
        return ans;
     */
        return dp[x][y];
    }

    public int calcuMatrix(int x1, int y1, int x2, int y2) {
        x1++;
        x2++;
        y1++;
        y2++;
        return dp[x2][y2] + dp[x1 - 1][y1 - 1] - dp[x1 - 1][y2] - dp[x2 - 1][y1];
    }

}
