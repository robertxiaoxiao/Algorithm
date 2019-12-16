package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/14/014
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
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

    public static List<String> removeInvalidParentheses(String s) {
        min = Integer.MAX_VALUE;
        List<String> res = new LinkedList<>();
        HashSet<String> hashSet = new HashSet<>();
        dfs(0, res, s, s.length(), hashSet);
        return res;
    }

    public static void dfs(int cnt, List<String> res, String cur, int size, HashSet<String> set) {
        if (cnt > min || cnt >= size)
            return;

        if (check(cur)) {
            if (cnt < min) {
                res.clear();
                //
                set.add(cur);
                res.add(cur);
                min = cnt;
                System.out.println(cur);
            } else {
                if (set.contains(cur))
                    return;
                set.add(cur);
                res.add(cur);
                System.out.println(cur);
            }
        }
        int n = cur.length();
        for (int i = 0; i < n; i++) {
            // meet the character
            if (cur.charAt(i) != '(' && cur.charAt(i) != ')')
                continue;

            String next = cur.substring(0, i) + cur.substring(i + 1);
            dfs(cnt + 1, res, next, size, set);
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
        i++;

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
