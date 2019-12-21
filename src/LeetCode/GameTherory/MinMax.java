package LeetCode.GameTherory;/*
 * @author: Robert
 * @date:  2019/12/20/020
 * @description:
 */

import java.util.Arrays;

public class MinMax {

    public boolean stoneGame(int[] arr) {

        int n = arr.length;
        int[][] mem = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(mem[i], Integer.MIN_VALUE);
        return scoresMem(arr, mem, 0, n - 1) > 0;
    }


    // scores : to get more score at that step to make my opponent get into min situation
    public int scores(int[] arr, int begin, int end) {
        if (begin > end)
            return 0;
        if (begin == end)
            return arr[begin];

        return Math.max(arr[begin] - scores(arr, begin + 1, end), arr[end] - scores(arr, begin, end - 1));
    }

    // we can solve the subproblems accordingly ,and classify the states of subproblem
    /*
         i
      j  x  y   y
         y  x   y
         y  y   x
               there exists three ways to calculate the 2-dimension dp arr ,
               a. from left to right   dp[i][j] =dp[i-1][j] dp[i][j-1] dp[i-1][j-1] ,usually padding the array
               b. diagonal ,  l= the len ,i = begin point ,so we can get dp[i][j] according to len growth
     */
    public int scoresDp(int[] arr) {

        int n = arr.length;

        //dp[i][j] :  max relative scores between i and j
        int[][] dp = new int[n][n];

        //  len(1)
        for (int i = 0; i < n; i++)
            dp[i][i] = arr[i - 1];
        //  len(2 - n)
        for (int l = 2; l <= n; l++)
            for (int i = 0; i < n; i++) {
                // the right end
                int j = i + l - 1;
                if (j >= n)
                    continue;
                dp[i][j] = Math.max(arr[i] - dp[i + 1][j], arr[j] - dp[i][j - 1]);
            }
        return dp[0][n - 1];
    }

    public int scoresDpCompress(int[] arr) {

        int n = arr.length;
        //dp[i][j] :  max relative scores between i and j
        // dp[i] : start with i at curLen ,because
        // dp[i][j] =dp[i] ;dp[i+1][j] =dp[i+1] ,dp[i][j-1]=dp[i]
        // dp[i] =Math.max(dp[i+1],dp[i](previous dp[i]))
        int[] dp = new int[n];
        //  len(1)
        for (int i = 0; i < n; i++)
            dp[i] = arr[i];
        //  len(2 - n)
        for (int l = 2; l <= n; l++)
            for (int i = 0; i < n; i++) {
                // the right end
                int j = i + l - 1;
                if (j >= n)
                    continue;
                dp[i] = Math.max(arr[i] - dp[i + 1], arr[j] - dp[i]);
            }
        return dp[n - 1];
    }


    public int scoresMem(int[] arr, int[][] mem, int begin, int end) {
        if (begin > end)
            return 0;
        if (begin == end)
            return arr[begin];
        if (mem[begin][end] == Integer.MIN_VALUE)
            mem[begin][end] = Math.max(arr[begin] - scoresMem(arr, mem, begin + 1, end), arr[end] - scoresMem(arr, mem, begin, end - 1));

        return mem[begin][end];
    }

}
