package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/22/022
 * @description:
 */

import java.util.Arrays;

public class q72 {

    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        int[][] mem = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(mem[i], Integer.MAX_VALUE);

        return memdp(word1, word2, 0, 0, mem);
    }

    public int memdp(String a, String b, int i, int j, int[][] mem) {

        if (i == a.length())
            return b.length() - j;

        // delete or insert
        if (j == b.length())
            return a.length() - i;

        if (mem[i][j] != Integer.MAX_VALUE)
            return mem[i][j];

        if (a.charAt(i) == b.charAt(j))
            return mem[i][j] = memdp(a, b, i + 1, j + 1, mem);
        else {
            int insert = memdp(a, b, i, j + 1, mem);
            int replace = memdp(a, b, i + 1, j + 1, mem);
            int delete = memdp(a, b, i + 1, j, mem);

            return mem[i][j] = Math.min(Math.min(insert, replace), delete) + 1;
        }

    }

    public int helper(String a, String b, int i, int j) {
        if (i == a.length())
            return b.length() - j - 1;

        // delete or insert
        if (j == b.length())
            return a.length() - i - 1;

        if (a.charAt(i) == b.charAt(j))
            return helper(a, b, i + 1, j + 1);
        else {
            int insert = helper(a, b, i, j + 1) + 1;
            int replace = helper(a, b, i + 1, j + 1) + 1;
            int delete = helper(a, b, i + 1, j);

            return Math.min(Math.min(insert, replace), delete) + 1;
        }
    }

}
