package LeetCode.DP.区间DP;


import java.util.Scanner;

public class Ac284 {


    /*
     dfs serials property  :  2*n-1=m (n is the nodes num and m is the serials size)
     */
    static int N = 310;
    static long[][] dp;
    static int mod = (int) 1e9;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        dp = new long[N][N];
        System.out.println(solve(s));

    }

    public static int solve(String s) {
        int n = s.length();
        if (n % 2 == 0)
            return 0;

        for (int len = 1; len <= n; len += 2)
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                if (len == 1)
                    dp[l][r] = 1;
                else if (s.charAt(l) == s.charAt(r)) {
                    for (int k = l; k < r; k += 2) {
                        if (s.charAt(k) == s.charAt(r))
                            dp[l][r] = (dp[l][r] + dp[l][k] * dp[k + 1][r - 1]) % mod;
                    }
                }
            }
        return (int) dp[0][n - 1];
    }
}
