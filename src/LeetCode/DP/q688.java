package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

public class q688 {
    static int[][] dirs = {
            {1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    };

    /*
      notice the data overflow
      type    bytes   bits
      double  8        64
      float   4        32
      int     4        32
      long    8        64
      byte    1        8
     */

    public double knightProbability(int N, int K, int r, int c) {
        double[][][] mem = new double[N][N][K + 1];
        double target = helper(0, K, N, r, c, mem);

        return (double) target;
    }

    public double helper(int cursteps, int k, int n, int r, int c, double[][][] mem) {
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return 0;
        }

        if (cursteps == k) {
            return 1;
        }

        if (mem[r][c][cursteps] != 0)
            return mem[r][c][cursteps];

        long cnt = 0;
        for (int d = 0; d < 8; d++) {
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            cnt += 0.125 * helper(cursteps + 1, k, n, nr, nc, mem);
        }
        return mem[r][c][cursteps] = cnt;
    }
}
