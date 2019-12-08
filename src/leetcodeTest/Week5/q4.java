package leetcodeTest.Week5;/*
 * @author:
 * @date:  2019/12/8/008
 * @description:
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class q4 {

    public int minFlips(int[][] mat) {
        HashMap<Integer, Integer> minsteps = new HashMap<>();
        HashSet<Integer> seen = new HashSet<>();
        int ans = bfs(mat, minsteps, seen);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // min step ,bfs is the first way to solve
    public int bfs(int[][] mat, HashMap<Integer, Integer> minsteps, HashSet<Integer> seen) {
        int hashkey = getkey(mat);
        if (hashkey == 0)
            return 0;

        if (minsteps.containsKey(hashkey))
            return minsteps.get(hashkey);

        if (seen.contains(hashkey))
            return Integer.MAX_VALUE;
        // get rid of circle when using bfs traverse
        seen.add(hashkey);

        // bfs  each iteration we will get the min step to final ;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[0].length; j++) {
                flip(mat, i, j);
                int small = bfs(mat, minsteps, seen);
                if (small != Integer.MAX_VALUE)
                    min = Math.min(small + 1, min);
                flip(mat, i, j);
            }
        minsteps.put(hashkey, min);
        seen.remove(hashkey);
        return min;
    }


    public int getkey(int[][] mat) {
        int sum = 0;
        for (int i = 0; i < mat.length; i++)
            // NOTICE !!!
            for (int j = 0; j < mat[0].length; j++)
                sum = (sum << 1) | mat[i][j];

        return sum;
    }

    public void flip(int[][] mat, int x, int y) {
        int n = mat.length;
        int m = mat[0].length;
        if (x + 1 < n)
            mat[x + 1][y] ^= 1;
        if (x - 1 >= 0)
            mat[x - 1][y] ^= 1;
        if (y + 1 < m)
            mat[x][y + 1] ^= 1;
        if (y - 1 >= 0)
            mat[x][y - 1] ^= 1;
        mat[x][y] = mat[x][y] ^ 1;
    }


    public static void main(String[] args) {
        int[][] mat = {{0, 0, 1}, {0, 0, 1}};

//        System.out.println(0 & 1);
//        System.out.println(0 | 1);
//        System.out.println(0 ^ 1);
//        System.out.println(1 ^ 1);

    }
}
