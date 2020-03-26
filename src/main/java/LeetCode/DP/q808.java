package LeetCode.DP;

public class q808 {

    double[][] dp;

    public double soupServings(int N) {
        if (N > 10000)
            return 1.0;
        int n = (N+24) / 25;
        dp = new double[n + 1][n + 1];
        return helper(n, n);
    }

    public double helper(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1;
        if (b <= 0) return 0;

        if (dp[a][b] > 0)
            return dp[a][b];

        double p1 = helper(a - 4, b);
        double p2 = helper(a - 3, b - 1);
        double p3 = helper(a - 2, b - 2);
        double p4 = helper(a - 1, b - 3);

        return dp[a][b] = 0.25 * (p1 + p2 + p3 + p4);
    }

}
