package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/28/028
 * @description:
 */

import java.util.Arrays;
import java.util.HashMap;

public class q5 {
    public String longestPalindrome(String s) {
        HashMap<String, String> mem = new HashMap<>();
        return memHelper(s, 0, s.length() - 1, "", mem);
    }

    public String dp(String s) {
        int len = s.length();
        int[][] dp = new int[len + 1][len];
        if (len == 0)
            return "";
        if (check(s))
            return s;
        // dp[l+1][i] =dp[l][i+1]  dp[l][i];
        // dp[i][j] =dp[i+1][j]  dp[i][j-1]
        // dp[i][l]
        Arrays.fill(dp[1], 1);
        for (int l = 2; l <= len; l++)
            for (int i = 0; i + l <= len; i++) {
                String temp = s.substring(i, i + l);
                if (check(temp))
                    dp[l][i] = l;
                else {
                    dp[l][i] = Math.max(dp[l - 1][i], dp[l - 1][i + 1]);
                }
            }

        int maxlen = Integer.MIN_VALUE;
        for (int num : dp[len])
            if (num > maxlen)
                maxlen = num;
        int i = 0;
        int cur = len;
        int pre = len - 1;
        while (dp[cur][i] == dp[pre][i] || dp[cur][i] == dp[pre][i + 1]) {
            cur = pre;
            pre = pre - 1;
            if (dp[cur][i] == dp[pre][i])
                i = i;
            if (dp[cur][i] == dp[pre][i + 1])
                i = i + 1;
        }
        return s.substring(i, i + cur);
    }

//    public String longestPalindromeDir(String s) {
//
//        if (s == null || s.length() == 0)
//            return "";
//
//        int maxlen = 0;
//        int maxstart = 0;
//        int maxend = 0;
//        for (int i = 1; i < s.length() / 2; i++) {
//            int mid = expandAroundCenter(s, i, i);
//            if (mid > maxlen) {
//                maxlen = mid;
//                maxstart = i - mid / 2 + 1;
//                maxend = i + mid / 2 - 1;
//            }
//
//            int lm = expandAroundCenter(s, i - 1, i);
//            if (lm > maxlen) {
//                maxlen = lm;
//                maxstart = i - lm / 2;
//                maxend = i + lm / 2;
//            }
//        }
//        return s.substring(maxstart, maxend + 1);
//    }
//
//    public int expandAroundCenter(String s, int left, int right) {
//        int l = left;
//        int r = right;
//        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
//            l--;
//            r++;
//        }
//        if(l<0||r>=s.length())
//            return
//        return r - l + 1;
//    }

    public String memHelper(String s, int start, int end, String curstr, HashMap<String, String> mem) {
        if (start == end)
            return s.substring(start, end + 1);
        String temp = s.substring(start, end + 1);
        if (mem.containsKey(temp))
            return mem.get(temp);

        if (check(temp)) {
            if (curstr == "" || curstr.length() < temp.length())
                curstr = temp;
        } else {
            String rstr = memHelper(s, start + 1, end, curstr, mem);
            String lstr = memHelper(s, start, end - 1, curstr, mem);
            curstr = rstr.length() > lstr.length() ? rstr : lstr;
        }
        mem.put(temp, curstr);
        return curstr;
    }

    public String helper(String s, int start, int end, String curstr) {
        if (start == end)
            return s.substring(start, end + 1);
        String temp = s.substring(start, end + 1);
        if (check(temp)) {
            if (curstr == "" || curstr.length() < temp.length())
                curstr = temp;
        } else {
            String rstr = helper(s, start + 1, end, curstr);
            String lstr = helper(s, start, end - 1, curstr);
            curstr = rstr.length() > lstr.length() ? rstr : lstr;
        }
        return curstr;
    }

    public boolean check(String s) {

        int i = 0;
        int j = s.length() - 1;
        if (j == 0)
            return true;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    static class node {
        int plen;
        int pi;

        public node(int plen, int pi) {
            this.plen = plen;
            this.pi = pi;
        }
    }


}
