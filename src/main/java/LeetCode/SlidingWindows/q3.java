package LeetCode.SlidingWindows;

import java.util.Arrays;
import java.util.HashSet;

public class q3 {

    public int lengthOfLongestSubstring(String s) {
        int[] chars = new int[128];
        Arrays.fill(chars, -1);
        int i = 0;
        int j = 0;
        int n = s.length();
        if (n == 0)
            return 0;
        int len = 1;
        for (; i < n && j < n; ) {
            char c = s.charAt(j);
            if (chars[c] == -1) {
                chars[c] = j;
            } else {
                // the position might update
                i = Math.max(i, chars[c] + 1);
                chars[c] = j;
            }
            j++;
            len = Math.max(len, j - i);
        }
        return len;
    }
}
