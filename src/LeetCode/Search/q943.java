package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/12/012
 * @description:
 */

import org.junit.Test;

import javax.print.DocFlavor;
import java.util.*;

public class q943 {


    public static String shortestSuperstringPermutation(String[] A) {
        boolean[] used = new boolean[A.length];
        LinkedList<String> cur = new LinkedList<>();
        LinkedList<String> res = new LinkedList<>();
        cur.add("");

        dfs(A, cur, res, used, 0);
        return res.peekFirst();
    }

    //if we consider all possible case, it will be n*(n-1)*(n-2)*...*1 = n!
    // possible cases. Obviously it won’t work.
    public static void dfs(String[] A, LinkedList<String> cur, LinkedList<String> res, boolean[] used, int cnt) {
        if (cnt == A.length) {
            String curstring = cur.peek();
            if (res.size() == 0 || curstring.length() < res.peekFirst().length()) {
                if (res.size() != 0)
                    res.remove(0);
//                System.out.println(curstring);
                res.add(new String(curstring));
            }
            return;
        }

        for (int i = 0; i < A.length; i++) {
            if (used[i])
                continue;

            used[i] = true;
            String curstr = cur.peek();
            String curleft = generaS1S2(curstr, A[i]);
            cur.remove(0);
            cur.add(curleft);

            dfs(A, cur, res, used, cnt + 1);
            cur.remove(0);

            if (cnt != 0) {
                String curright = cur + generaS1S2(A[i], curstr);
                cur.add(curright);
                //  System.out.println(curright);
                dfs(A, cur, res, used, cnt + 1);
                cur.remove(0);
            }
            used[i] = false;
            cur.add(curstr);
        }
    }

    // how to judge whether s2 is subarray of s1
    public static boolean checkUsingAPI(String s1, String s2) {
        //  byte a;
        return s1.indexOf(s2) == -1 ? false : true;
        // return s1.LastindexOf(s2)==-1?false:true;
    }

    // a merge b  addedlen =a+b-c
    @Test
    public static int addedchars(String a, String b) {

        for (int i = 0; i < a.length(); i++) {
            if (b.startsWith(a.substring(i)))
                return b.length() - a.length() + i;
        }
        return b.length();
    }

    public static String shortestSuperstring(String[] A) {

        int n = A.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                graph[i][j] = addedchars(A[i], A[j]);
                graph[j][i] = addedchars(A[j], A[i]);
            }

        int[][] dp = new int[1 << n][n];
        int[][] path = new int[1 << n][n];
        int min = Integer.MAX_VALUE;
        int last = -1;
        for (int i = 0; i < (1 << n); i++) {
            // put current state in max_value
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                //  set i contains node j
                if ((i & (1 << j)) > 0) {

                    int prenodes = i - (1 << j);

                    // empty set and we choose the node j as start node
                    if (prenodes == 0)
                        dp[i][j] = A[j].length();
                    else {
                        for (int k = 0; k < n; k++) {
                            if (dp[prenodes][k] < Integer.MAX_VALUE && dp[prenodes][k] + graph[k][j] < dp[i][j]) {
                                dp[i][j] = dp[prenodes][k] + graph[k][j];
                                path[i][j] = k;
                            }
                        }
                    }
                }

                if (i == (1 << n) - 1 && dp[i][j] < min) {
                    min = dp[i][j];
                    last = j;
                }
            }
        }
        System.out.println(last);
        int end = (1 << n) - 1;
        Stack<Integer> stack = new Stack<>();
        while (end != 0) {
            stack.push(last);
            int prenodes = end - (1 << last);
            last = path[end][last];
            end = prenodes;
        }

        StringBuffer sb = new StringBuffer();
        int temp = stack.pop();
        sb.append(A[temp]);
        while (!stack.isEmpty()) {
            int next = stack.pop();
            sb.append(A[next].substring(A[next].length() - graph[temp][next]));
            temp = next;
        }
        return sb.toString();
    }

    public static boolean check(String s1, String s2) {

        if (s1.length() == 0)
            return true;

        if (s2.length() == 0)
            return true;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                int left = i;
                int right = i + s2.length() - 1;
                while (left < s1.length() && left <= right && s1.charAt(left) == s2.charAt(left - i))
                    left++;
                if (left > right)
                    return true;
            }
        }
        return false;
    }

    public static String generaS1S2(String s1, String s2) {
        if (s1.length() == 0)
            return new String(s2);
        if (s2.length() == 0)
            return new String(s1);

        if (check(s1, s2))
            return s1;

        if (check(s2, s1))
            return s2;

        int sl1 = s1.length();
        int sl2 = s2.length();

        int begins1 = 0;
        int ends2 = sl2 - 1;

        // the same prefix
        StringBuilder s2s1 = new StringBuilder();
        while (begins1 + 1 < sl1 && s1.charAt(begins1) == s1.charAt(begins1 + 1) && s1.charAt(begins1) == s2.charAt(ends2)) {
            ends2--;
            begins1++;
        }
        if (begins1 + 1 < sl1) {
            s2s1.append(s2).append(s1.substring(begins1));
            return s2s1.toString();
        }

        begins1 = findinends2endins1(s2.charAt(ends2), s1);
        int temp = begins1;

        while (begins1 >= 0 && ends2 >= 0 && s1.charAt(begins1) == s2.charAt(ends2)) {
            System.out.println(s1.charAt(begins1));
            --ends2;
            --begins1;
        }

        if (begins1 < 0) {
            s2s1.append(s2).append(s1.substring(temp + 1));
            int k = s2.length();
            String temps2s1 = s2s1.toString();

            return s2s1.toString();
        }

        s2s1.append(s2).append(s1);
        return s2s1.toString();
    }

    private static int findinends2endins1(char charAt, String s1) {
        for (int i = 0; i < s1.length(); i++)
            if (charAt == s1.charAt(i))
                return i;
        return -1;
    }

    public static void main(String[] args) {
        String[] A = {"sssv", "svq", "dskss", "sksss"};
        // System.out.println(generaS1S2("sssv","dskss"));
        //  System.out.println(check("dsksssvq", "sksss") == checkUsingAPI("dsksssvq", "sksss"));
        System.out.println(shortestSuperstring(A));
    }
}
