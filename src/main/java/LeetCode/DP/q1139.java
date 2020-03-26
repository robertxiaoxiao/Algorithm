package LeetCode.DP;

public class q1139 {


    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        int min = Math.min(n, m);
        int max = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    max = Math.max(max, 1);
                for (int len = 2; len <= min; len++) {
                    int cur = getSquare(i, j, dp, len);
                    int inner = getSquare(i + 1, j + 1, dp, len - 2);
                    if (cur - inner == 4 * len - 4)
                        max = Math.max(max, len * len);
                }
            }
        return max;
    }

    public int getSquare(int i, int j, int[][] dp, int len) {
        if (len <= 0)
            return 0;

        int ri = i + len - 1;
        int rj = j + len - 1;
        i++;
        j++;
        ri++;
        rj++;

        if (ri >= dp.length || rj >= dp[0].length)
            return 0;

        int cur = dp[ri][rj] + dp[i - 1][j - 1] - dp[i - 1][rj] - dp[ri][j - 1];

        return cur;
    }

    /*
    Preprocess the data: create 2 additional matrices.
    In left[][], for each cell, we store the number of ones immediately at left (and itself)
    In up[][], for each cell, we store the number of ones immediately above (and itself)
    grid[3][3] = 1 1 1    left[3][3] = 1 2 3    up[3][3] = 1 1 1
             1 0 1                 1 0 1               2 0 2
             1 1 1                 1 2 3               3 1 3
  Search for largest squares first. Loop through our grid.
  Treat each cell as a top-left corner of a square,
  and check if a valid square can be made from there.
     */
    public int largest1BorderedSquareDP(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] left = new int[rows][cols];
        int[][] up = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    left[r][c] = (c == 0) ? 1 : left[r][c - 1] + 1;
                    up[r][c] = (r == 0) ? 1 : up[r - 1][c] + 1;
                }
            }
        }

        for (int side = Math.min(rows, cols); side > 0; side--) {
            for (int r = 0; r + side - 1 < rows; r++) {
                for (int c = 0; c + side - 1 < cols; c++) {
                    if (left[r][c + side - 1] >= side
                            && left[r + side - 1][c + side - 1] >= side
                            && up[r + side - 1][c] >= side
                            && up[r + side - 1][c + side - 1] >= side) {
                        return side * side;
                    }
                }
            }
        }
        return 0;
    }

}
