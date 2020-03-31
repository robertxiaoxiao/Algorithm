package LeetCode.UsefulDS;

import java.util.LinkedList;
import java.util.List;

class KMP {
    static int[] next;
    static String pattern;

    public static void build(String p) {
        pattern = p;
        int n = pattern.length();
        next = new int[n + 1];
        int i = 0;
        int k = -1;
        next[0] = -1;
        while (i < n) {
            if (k == -1 || pattern.charAt(i) == pattern.charAt(k)) {
                i++;
                k++;
                next[i] = k;
            } else {
                k = next[k];
            }
        }
    }

    public static List<Integer> match(String s) {
        int n = s.length();
        int m = pattern.length();
        List<Integer> ans = new LinkedList<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != pattern.charAt(j))
                j = next[j];

            if (s.charAt(i) == pattern.charAt(j))
                j++;

            if (j == m) {
                ans.add(i - m + 1);
                j = next[j];
            }
        }
        return ans;
    }
    
}