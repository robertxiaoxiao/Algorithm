package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/20/020
 * @description:
 */

public class q583 {
    public int minDistance(String word1, String word2) {
        //keep[i]
        return helper(word1, word2);
    }

    public int minDistanceUsingdp(String a, String b) {
        if (a.equalsIgnoreCase(b))
            return 0;
        if (a.length() == 0)
            return b.length();
        if (b.length() == 0)
            return a.length();

        int lena = a.length();
        int lenb = b.length();
        // dp[i][j]  a[i,lena] b[j,lenb]

        // a[i-1] a[i]
        // b[j-1] b[j]
        int[][] dp = new int[lena + 1][lenb + 1];
        for (int j = lenb - 1; j >= 0; j--)
            dp[lena][j] = lenb - j - 1;
        for (int j = lena - 1; j >= 0; j--)
            dp[j][lenb] = lena - j - 1;
        dp[lena][lenb] = 0;
        for (int i = lena - 2; i >= 0; i--)
            for (int j = lenb - 2; j >= 0; j--) {
                if (a.charAt(i) == b.charAt(j))
                    dp[i][j] = Math.min(Math.min(dp[i + 1][j] + 1, dp[i][j + 1] + 1), dp[i + 1][i + 1]);
                else
                    dp[i][j] = Math.min(dp[i + 1][j] + 1, dp[i][j + 1] + 1);
            }

        return dp[0][0];
    }

    public int helper(String a, String b) {
        if (a.length() == 0)
            return b.length();
        if (b.length() == 0)
            return a.length();
        if (a.equalsIgnoreCase(b))
            return 0;

        if (a.charAt(0) == b.charAt(0)) {
            return helper(a.substring(1), b.substring(1));
        } else {
            int removeA = helper(a.substring(1), b) + 1;
            int removeB = helper(a, b.substring(1)) + 1;
            return Math.min(removeA, removeB);
        }
    }

    public int memDp(String a,String b ,int m,int n,int[][] mem)
    {




    }

}
