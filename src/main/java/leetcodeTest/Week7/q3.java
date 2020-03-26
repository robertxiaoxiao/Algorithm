package leetcodeTest.Week7;/*
 * @author: Robert
 * @date:  2019/12/22/022
 * @description:
 */

import java.util.HashMap;
import java.util.HashSet;

public class q3 {

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        int n = s.length();
        int max = 0;
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i + minSize < n - 1; i++)
            for (int l = minSize; l <= maxSize; l++) {
                if (i + l > n)
                    continue;
                String str = s.substring(i, i + l);
                if (hm.containsKey(str) || check(str, maxLetters)) {
                    int cur = hm.getOrDefault(str, 0) + 1;
                    max = Math.max(cur, max);
                    hm.put(str, cur);
                }
            }

        for (int time : hm.values())
            max = Math.max(time, max);

        return max;
    }

    // check the count in O(32),we can use the bit wise count to lessen the time ;
    public boolean check(String s, int maxletters) {
        int f = 0;
        for (char c : s.toCharArray()) {
            f |= 1 << c - 'a';
        }
        return Integer.bitCount(f) <= maxletters;
    }

    public static void main(String[] args) {
        char c = 'a';
        System.err.println(1 << 0);
        System.err.println(1 << 'a');
        System.err.println(1 << 1);

    }


}

