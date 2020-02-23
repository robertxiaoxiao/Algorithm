package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/21/021
 * @description:
 */

public class Q32 {

    public int longestValidParentheses(String s) {
        // dp[i][l]
        //dp[i][l]= dp[i-1][l-1]+1 ,dp[i][l+1]
        int n = s.length();
        if (n == 0)
            return 0;
        int[] dp = new int[n];
        //dp[i]   end at i with the max length of valid parent
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (i - 1 >= 0 && s.charAt(i - 1) == '(')
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;

                if (i - 1 >= 0 && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(')
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);

                maxlen = Math.max(maxlen, dp[i]);
            }
        }
        return maxlen;
    }


}
