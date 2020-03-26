package LeetCode.SlidingWindows;

import java.util.PriorityQueue;

public class q76 {

    public String minWindowTemple(String s, String t) {
        char[] s_array = s.toCharArray();
        char[] t_array = t.toCharArray();
        int[] map = new int[256];
        int end = 0;
        int start = 0;
        int min_length = Integer.MAX_VALUE;
        for (int i = 0; i < t_array.length; i++) // store t character and its count ----< means the lack number
            map[t_array[i]]++;
        int count = t_array.length;
        int min_start = 0;

        // for those Characters t doesn't have, map[thisCharacter] are at most 0
        while (end < s_array.length) {
            if (map[s_array[end]] > 0)   // t has this character
            {
                count--;
            }
            map[s_array[end]]--;
            if (count == 0)   //window matches
            {
                if ((end - start + 1) < min_length) {
                    min_length = end - start + 1;
                    min_start = start;
                }
                map[s_array[start]]++;     // start go left
                if (map[s_array[start]] > 0) {    // remove a character which t has
                    count++;           //Cause for those Characters t doesn't have, map[thisCharacter] are at most 0
                }
                start++;
            }
            end++;
        }

        if (min_start + min_length > s_array.length)
            return "";
        return s.substring(min_start, min_start + min_length);
    }

    // Two Points property : monotonicity
    //ASCII code : 128(int) to represent all english character
    public static String minWindow(String s, String t) {
        // using count to record the request of each character
        int sl = s.length();
        int tl = t.length();
        int[] map = new int[256];

        for (int i = 0; i < tl; i++) {
            map[t.charAt(i)]++;
        }

        int count = tl;
        int min_start = -1;
        int min_len = Integer.MAX_VALUE;
        int ce = 0;
        int cs = 0;
        while (ce < sl) {
            if (map[s.charAt(ce)] > 0) {
                count--;
            }
            map[s.charAt(ce)]--;
            while (count == 0 && cs <= ce) {
                if (ce - cs + 1 < min_len) {
                    min_len = ce - cs + 1;
                    min_start = cs;
                }
                map[s.charAt(cs)]++;
                if (map[s.charAt(cs)] > 0) {
                    count++;
                }
                cs++;
            }
            ce++;
        }

        if (min_len == Integer.MAX_VALUE)
            return "";

        return s.substring(min_start, min_start + min_len);
    }

    public static void main(String[] args) {
        String S = "ADOBECODEBANC", T = "ABC";
        System.out.println(minWindow(S, T));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

    }

}
