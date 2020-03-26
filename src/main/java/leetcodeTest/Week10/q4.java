package leetcodeTest.Week10;/*
 * @author: Robert
 * @date:  2020/1/12/012
 * @description:
 */

import java.text.DateFormatSymbols;
import java.util.Arrays;

public class q4 {

    public int minimumDistanceUsingDP(String word) {
        int n = word.length();
        int[][][] state = new int[n][26][26];

        char first = word.charAt(0);
        for (char i = 'A'; i <= 'Z'; i++)
            for (char j = 'A'; j <= 'Z'; j++)
                state[0][i - 'A'][j - 'A'] = Math.min(getDitance(first, i), getDitance(first, j));

        for (int k = 1; k < n; k++) {
            for (int i = 0; i < 26; i++)
                Arrays.fill(state[k][i], Integer.MAX_VALUE / 2);
            char c = word.charAt(k);
            for (char i = 'A'; i <= 'Z'; i++)
                for (char j = 'A'; j <= 'Z'; j++) {
                    state[k][c - 'A'][j - 'A'] = Math.min(state[k][c - 'A'][j - 'A'], state[k - 1][i - 'A'][j - 'A'] + getDitance(c, i));
                    state[k][i - 'A'][c - 'A'] = Math.min(state[k][i - 'A'][c - 'A'], state[k - 1][i - 'A'][j - 'A'] + getDitance(j, c));
                }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++)
            for (int j = 0; j < 26; j++)
                res = Math.min(res, state[n - 1][i][j]);
        return res;
    }

    static int min;

    public static int minimumDistance(String word) {
        char a = word.charAt(0);
        char b = word.charAt(1);
        min = Integer.MAX_VALUE;
        int n = word.length();
        for (char f = 'A'; f <= 'Z'; f++)
            for (char s = 'A'; s <= 'Z'; s++) {
                dfs(word, 0, f, s, 0);
            }
        return min;
    }

    public static void dfs(String word, int i, char cf, char cs, int cnt) {
        if (cnt > min)
            return;
        if (i == word.length()) {
            min = Math.min(min, cnt);
            return;
        }
        char c = word.charAt(i);
        int df = getDitance(c, cf);
        int ds = getDitance(c, cs);
        dfs(word, i + 1, c, cs, cnt + df);
        dfs(word, i + 1, cf, c, cnt + ds);
    }

    public static int getDitance(char a, char b) {
        int ca = a - 'A';
        int cb = b - 'A';
        if (ca == cb)
            return 0;
        int xa = ca / 6;
        int ya = ca % 6;
        int xb = cb / 6;
        int yb = cb % 6;
        return Math.abs(xa - xb) + Math.abs(ya - yb);
    }

    public static void main(String[] args) {
        System.out.println(minimumDistance("NEW"));
    }

}
