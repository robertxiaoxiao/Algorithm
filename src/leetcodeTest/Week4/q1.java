package leetcodeTest.Week4;/*
 * @author:
 * @date:  2019/12/1/001
 * @description:
 */

public class q1 {

    public String tictactoe(int[][] moves) {
        int n = moves.length;
        int[][] grid = new int[3][3];
        int i = 0;
        for (i = 0; i < n; i++) {
            int x = moves[i][0];
            int y = moves[i][1];
            if (i % 2 == 0)
                grid[x][y] = 1;
            else
                grid[x][y] = -1;
            if (i >= 4) {
                if (judge(grid, x, y) == 1)
                    return new String("A");
                else if (judge(grid, x, y) == 0)
                    return new String("B");
                else
                    continue;
            }
        }
        if (n < 9)
            return new String("Pending");

        return new String("Draw");
    }

    private static int judge(int[][] grid, int x, int y) {
        int rowsum = 0;
        int colsum = 0;
        int diasum1 = 0;
        int diasum2 = 0;
        for (int i = 0; i < 3; i++) {
            rowsum += grid[x][i];
            colsum += grid[i][y];
            diasum1 += grid[i][i];
            diasum2 += grid[i][2 - i];
        }

        if (rowsum == 3 || colsum == 3 || diasum1 == 3 || diasum2 == 3)
            return 1;
        if (rowsum == -3 || colsum == -3 || diasum1 == -3 || diasum2 == -3)
            return 0;

        return -1;

    }
}
