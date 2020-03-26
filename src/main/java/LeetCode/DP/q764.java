package LeetCode.DP;

public class q764 {

    public int orderOfLargestPlusSign(int N, int[][] mines) {

        int[][] left = new int[N + 2][N + 2];
        int[][] right = new int[N + 2][N + 2];
        int[][] up = new int[N + 2][N + 2];
        int[][] down = new int[N + 2][N + 2];
        int[][] arr = new int[N][N];
        for (int[] t : mines) {
            arr[t[0]][t[1]] = 1;
        }
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                if (arr[i - 1][j - 1] != 1) {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                }
            }

        for (int i = N; i >= 1; i--)
            for (int j = N; j >= 1; j--) {
                if (arr[i - 1][j - 1] != 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        int max = 0;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                System.out.printf("%d %d %d %d", right[i][j], left[i][j], down[i][j], up[i][j]);
                int mlr = Math.min(right[i][j], left[i][j]);
                int mud = Math.min(up[i][j], down[i][j]);
                int temp = Math.min(mlr, mud);
                max = Math.max(max, temp);
            }
        return max;
    }

}
