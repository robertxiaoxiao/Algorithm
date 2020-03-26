package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/25/025
 * @description:
 */

public class q44 {

    // ?
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        int[][] mem = new int[n + 1][m + 1];
        return helper(s, p, 0, 0);
    }

    //top-down iteration in more simple mode
    public boolean SimpleHelper(String s, String p, int i, int j, int[][] mem) {

        if (mem[i][j] != 0)
            return mem[i][j] == 1;

        if (i == s.length() && j == p.length())
            return true;

        if (i == s.length()) {
            if (p.charAt(j) == '*') {
                // 1: true   2 :false
                return helper(s, p, i, j + 1, mem);
            }
            return false;
        }

        if (j == p.length())
            return false;

        boolean firstmatch = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            firstmatch |= helper(s, p, i + 1, j + 1, mem);
        }
        if (p.charAt(j) == '*') {
            firstmatch |= helper(s, p, i, j + 1, mem) | helper(s, p, i + 1, j, mem);
        }
        mem[i][j] = firstmatch ? 1 : 2;
        return firstmatch;
    }


    // top-down iteration
    public boolean helper(String s, String p, int i, int j, int[][] mem) {
        if (mem[i][j] != 0)
            return mem[i][j] == 1;

        if (i == s.length() && j == p.length())
            return true;

        if (i == s.length()) {
            if (p.charAt(j) == '*') {
                // 1: true   2 :false
                mem[i][j + 1] = helper(s, p, i, j + 1) ? 1 : 2;
                return mem[i][j + 1] == 1;
            }
            return false;
        }

        if (j == p.length())
            return false;

        boolean firstmatch = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            mem[i + 1][j + 1] = helper(s, p, i + 1, j + 1) ? 1 : 2;
            firstmatch |= mem[i + 1][j + 1] == 1;
        }
        if (p.charAt(j) == '*') {
            mem[i][j + 1] = helper(s, p, i, j + 1) ? 1 : 2;
            mem[i + 1][j] = helper(s, p, i + 1, j) ? 1 : 2;
            firstmatch |= (mem[i][j + 1] == 1) | (mem[i + 1][j] == 1);
        }
        mem[i][j] = firstmatch ? 1 : 2;
        return firstmatch;
    }

    public boolean helper(String s, String p, int i, int j) {

        if (i >= s.length() && j >= p.length())
            return true;

        if (i >= s.length()) {
            if (p.charAt(j) == '*')
                return helper(s, p, i, j + 1);
            return false;
        }
        if (j >= p.length())
            return false;

        boolean firstmatch = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
            firstmatch |= helper(s, p, i + 1, j + 1);

        if (p.charAt(j) == '*')
            firstmatch |= helper(s, p, i, j + 1) | helper(s, p, i + 1, j);

        return firstmatch;
    }


}
