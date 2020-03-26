package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/7/007
 * @description:
 */

import java.util.HashSet;

public class q733 {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        HashSet<Integer> seen = new HashSet<>();
        int rows = image.length;
        if (rows == 0)
            return image;
        int cols = image[0].length;
        seen.add(sr * 100 + cols);
        fill(sr, sc, rows, cols, image[sr][sc], newColor, seen, image);
        return image;
    }

    static int[] dirs = {0, -1, 0, 1, 0};

    public void fill(int cr, int cc, int rows, int cols, int targetcolor, int newcolor, HashSet<Integer> seen, int[][] image) {
        image[cr][cc] = newcolor;
        for (int i = 0; i < 4; i++) {
            int nr = cr + dirs[i];
            int nc = cc + dirs[i + 1];
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || image[nr][nc] != targetcolor)
                continue;
            int next = nr * 100 + nc;
            if (seen.contains(next))
                continue;
            seen.add(next);
            fill(nr, nc, rows, cols, targetcolor, newcolor, seen, image);

        }
    }

}
