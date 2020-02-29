package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/28/028
 * @description:
 */


//
public class q664 {

    public int strangePrinter(String s) {
        if (s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        /*

Two basic points:
    1, When we print a string, we may have lots of optimal solutions.
     Printing the whole line with the first character at the first step can always
     yield one of the optimal solutions. My understanding is that,
     although we can print other characters first, we still need one step coming back
     to print the first character. So printing other characters first and
     coming back printing the first character with one extra step is equivavlent to
     print the first character in first step and then printing other characters.
     Since in the first step, printing one character is equivalent to printing whole
     line with that character, we can print the whole line with the first character
     in the first step. And this will always give us one optimal solution.

    2, When we print s[i..j], characters from i to j in the line,
     if some character s[k] in the middle same as s[i], base on point 1,
     one optimal soltution is we print whole s[i..j] with s[i] first,
     this will print s[k] too. So the DP equation dp[i][j] = min(dp[i][j],
     dp[i][k-1] + dp[k+1][j]) holds, becase s[k] already printed when we print
     s[i] at the first step.
         */
        for (int len = 1; len <= n; len++) {
            for (int L = 0; L + len - 1 < n; L++) {
                int R = L + len - 1;

                // it always starts at print s[l:r] s[l]
                dp[L][R] = dp[L + 1][R] + 1;
                for (int k = L + 1; k <= R; k++) {
                    if (s.charAt(k) == s.charAt(L)) {
                        dp[L][R] = Math.min(dp[L][R], dp[L][k - 1] + dp[k + 1][R]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

//    public int strangePrinter(String s) {
//
//        // dp : i start  i end at most  len seq/pre
//        return helper(s, 0,    s.length() - 1);
//    }

}
