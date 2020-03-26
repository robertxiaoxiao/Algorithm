package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/2/002
 * @description:
 */

public class q1278 {
    public static void print(String s) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int l = 2; l <= n; ++l)
            for (int i = 0, j = l - 1; j < n; ++i, ++j)
                /*
                 ab bc cd de ef
                 abc bcd cde def
                 abcd bcde cdef
                 abcde bcdef
                 abcdef
                 a b c d
                 0 1
                 0 0 1
                 0 0 0 1
                 0 0 0 0
                */
                cost[i][j] = (s.charAt(i) != s.charAt(j) ? 1 : 0) + cost[i + 1][j - 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(cost[i][j] + "   ");
            System.out.println();
        }

    }

    public static void main(String[] args) {

        print("abcdef");

    }

    public int palindromePartitionPre(String s, int k) {

        int n = s.length();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++)
            dp[i][1] = changeString(s, 0, i);

        for (int i = 2; i <= n; i++)
            for (int j = 2; j <= k; j++) {
                dp[i][j] = dp[i - 1][j - 1];
                for (int m = j - 1; m <= i - 2; m++)
                    dp[i][j] = Math.min(dp[i][j], dp[m][j - 1] + changeString(s, m, i));
            }
        return dp[n][k];
    }

    private int changeString(String s, int beginIndex, int endIndex) {
        String temp = s.substring(beginIndex, endIndex);
        int n = temp.length();
        int left = 0;
        int right = n - 1;
        int ans = 0;
        while (left <= right) {
            if (temp.charAt(left) != temp.charAt(right))
                ans++;
            left++;
            right--;
        }
        return ans;
    }

    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int l = 2; l <= n; ++l)
            for (int i = 0, j = l - 1; j < n; ++i, ++j)
                /*
                 ab bc cd de ef
                 abc bcd cde def
                 abcd bcde cdef
                 abcde bcdef
                 abcdef
                 a b c d
                 0 1
                 0 0 1
                 0 0 0 1
                 0 0 0 0

                */
                cost[i][j] = (s.charAt(i) != s.charAt(j) ? 1 : 0) + cost[i + 1][j - 1];

        // dp[i][j]=dp[i+1][j-1]+1;
        for (int len = 2; len <= n; len++)
            for (int i = 0; i < n; i++) {
                int j = i + len - 1;
                // i  i+len-1  fill the array in  diagonal
                cost[i][j] = (s.charAt(i) != s.charAt(j) ? 1 : 0) + cost[i + 1][j - 1];
            }

        int[][] dp = new int[n][k];


        for (int i = 0; i < n; i++)
            dp[i][0] = cost[0][i];

        for (int tk = 1; tk < k; tk++)
            for (int i = 0; i < n; i++) {
                dp[i][tk] = Integer.MAX_VALUE / 2;
                for (int cut = 0; cut + 1 <= i; cut++)
                    dp[i][tk] = Math.min(dp[i][tk], dp[cut][tk - 1] + cost[cut + 1][i]);
            }
        return dp[n - 1][k - 1];
    }

    public int dp(String s, int st, int end) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // dp[i][j]=dp[i+1][j-1]+1;
        for (int len = 2; len <= n; len++)
            for (int i = 0; i < n; i++) {
                int j = i + len - 1;
                // i  i+len-1  fill the array in  diagonal
                dp[i][j] = (s.charAt(i) != s.charAt(j) ? 1 : 0) + dp[i + 1][j - 1];
            }

        return dp[0][n - 1];
    }

}
