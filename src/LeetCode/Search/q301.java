package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/14/014
 * @description:
 */

import java.util.*;
import java.util.zip.CheckedInputStream;

public class q301 {
    public static void main(String[] args) {
        String a = "()((((((()l(";
        List<String> res = removeInvalidParentheses(a);
        System.out.println(res.size());
        //   System.out.println(set.contains(b));
        // System.out.println(check(a));
//        System.out.println(check(b));
    }

    static int min = Integer.MAX_VALUE;
    // first find the essence and those agitators(infringement the rule )
    /*
       we first need to find the deleted number of Parentheses and then we search for all solution to find
       the correct solution (the minsteps is the number of displaced parentheses)
     */
    public static List<String> removeInvalidParenthesesUsingdfs(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                left++;
            else if (s.charAt(i) == ')') {
                if (left == 0)
                    right++;
                else
                    left--;
            }
        }
        HashSet<String> hset = new HashSet<String>();
        dfs(0, left, right, s, hset, "");
        return new LinkedList<>(hset);

    }

    public static void dfs(int start, int left, int right, String s, HashSet<String> set, String cur) {
        if (start == s.length()) {
            if (left == 0 && right == 0 && check(cur))
                set.add(cur);
            return;
        }

        if (s.charAt(start) == '(' && left > 0)
            dfs(start + 1, left - 1, right, s, set, cur);
        else if (s.charAt(start) == ')' && right > 0)
            dfs(start + 1, left, right - 1, s, set, cur);

        // cur character ( or )  insert
        dfs(start + 1, left, right, s, set, cur + s.charAt(start));
    }

    public static List<String> removeInvalidParenthesesUsingbfs(String s) {
        min = Integer.MAX_VALUE;
        List<String> res = new LinkedList<>();
        HashSet<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                if (steps > min)
                    return res;
                --size;
                String cur = queue.poll();
                if (check(cur)) {
                    res.add(cur);
                    return res;
                }
                int n = cur.length();
                if (n == 0) {
                    res.add("");
                    return res;
                }
                for (int i = 0; i < n; i++) {
                    // meet the character
                    if (cur.charAt(i) != '(' && cur.charAt(i) != ')')
                        continue;

                    String next = cur.substring(0, i) + cur.substring(i + 1);

                    if (seen.contains(next))
                        continue;

                    if (!check(next)) {
                        queue.add(next);
                        continue;
                    }
                    if (steps + 1 < min) {
                        res.clear();
                        min = steps + 1;
                    }
                    res.add(next);
                    seen.add(next);
                }
            }
            ++steps;
        }
        return res;
    }

    public static List<String> removeInvalidParentheses(String s) {
        min = Integer.MAX_VALUE;
        List<String> res = new LinkedList<>();
        HashMap<String, Integer> hm = new HashMap<>();
        dfs(0, res, s, s.length(), hm);
        return res;
    }

    public static void dfs(int cnt, List<String> res, String cur, int size, HashMap<String, Integer> hm) {
        if (cnt > min || cnt > size)
            return;

        if (hm.containsKey(cur) && hm.get(cur) < cnt)
            return;

        if (check(cur)) {
            if (cnt < min) {
                res.clear();
                min = cnt;
                System.out.println(cur);
            }
            hm.put(cur, cnt);
            res.add(cur);
            //     System.out.println(cur);
        }

        int n = cur.length();
        for (int i = 0; i < n; i++) {
            // meet the character
            if (cur.charAt(i) != '(' && cur.charAt(i) != ')')
                continue;
            String next = cur.substring(0, i) + cur.substring(i + 1);
            if (hm.containsKey(next))
                continue;
            dfs(cnt + 1, res, next, size, hm);
        }
    }

    public static boolean check(String A) {

        char[] chars = A.toCharArray();
        int i = 0;
        while (i < A.length() && chars[i] != '(') {
            if (chars[i] == ')')
                return false;
            i++;
        }
        // whole string only contains characters
        if (i == A.length())
            return true;

        int cnt = 0;
        while (i < A.length()) {
            if (chars[i] == '(')
                cnt++;
            else if (chars[i] == ')') {
                if (cnt == 0)
                    return false;
                cnt--;
            }
            ++i;
        }

        if (cnt == 0)
            return true;
        return false;
    }
}
