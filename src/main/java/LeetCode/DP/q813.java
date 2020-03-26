package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

public class q813 {

    public double largestSumOfAverages(int[] A, int K) {
        double max = -1;
        int n = A.length;

        for (int m = 0; m < K; m++) {
            double[][][] dp = new double[n][n][K];
            max = Math.max(max, helper(A, m, 0, 0, A.length - 1, dp));
        }
        return max;
    }

    public double helper(int[] A, int k, int curk, int start, int end, double[][][] mem) {
        if (start > end)
            return 0;

        if (curk == k) {
            return getave(A, start, end);
        }

        if (mem[start][end][k] != 0)
            return mem[start][end][k];

        double max = getave(A, start, start) + helper(A, k, curk + 1, start + 1, end, mem);
        for (int cut = start + 1; cut <= end; cut++)
            max = Math.max(max, getave(A, start, cut) + helper(A, k, curk + 1, cut + 1, end, mem));

        return mem[start][end][k] = max;
    }

    private double getave(int[] a, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++)
            sum += a[i];
        return (double) sum / (end - start + 1);
    }
}
