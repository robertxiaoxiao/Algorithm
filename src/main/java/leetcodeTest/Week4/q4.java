package leetcodeTest.Week4;/*
 * @author:
 * @date:  2019/12/1/001
 * @description:
 */

public class q4 {
    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++)
            dp[i][0] = changeString(s, 0, i);

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j - 1];
                for (int m = j - 1; m <= i - 2; m++)
                    dp[i][j] = Math.min(dp[i][j], dp[m][j - 1] + changeString(s, m, i));
            }
        return dp[n][k];
    }

    private int changeString(String s, int beginIndex, int endIndex) {
        String temp = s.substring(beginIndex - 1, endIndex + 1);
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

//    private boolean judge(String s, int i) {
//        String temp = s.substring(0, i);
//        int n = temp.length();
//        if (n == 1)
//            return true;
//        int mid = n / 2;
//        int left = 0;
//        int right = n - 1;
//        while (left <= right) {
//            if (temp.charAt(left) != temp.charAt(right))
//                return false;
//            left++;
//            right--;
//        }
//        return true;
//    }
}
