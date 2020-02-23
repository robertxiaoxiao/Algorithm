package leetcodeTest.BiWeek1;/*
 * @author: Robert
 * @date:  2020/2/22/022
 * @description:
 */

public class q4 {

    public int countOrders(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * (getCom(2 * (i - 1) + 1)) % (long) (1e9 + 7);
        }
        return (int) (dp[n] % (long) (1e9 + 7));
    }


    public long getCom(int n) {
        long res = (long) (n) * (long) (n - 1);
        return res / 2 + n;
    }
}
