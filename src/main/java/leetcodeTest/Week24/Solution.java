package leetcodeTest.Week24;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Solution {

    public int findMinFibonacciNumbers(int k) {

        TreeSet<Integer> tset = new TreeSet();
        int f1 = 1;
        int f2 = 1;
        while (f1 <= k) {
            tset.add(f1);
            int temp = f1 + f2;
            f1 = f2;
            f2 = temp;
        }


        int cnt = 0;
        while (k > 0) {
            if (tset.contains(k))
                return cnt + 1;
            int cur = tset.lower(k);
            k = k - cur;
            cnt++;
        }
        return cnt;
    }

    public String getHappyString(int n, int k) {
        List<String> list = new LinkedList<>();
        helper(list, new StringBuffer(), 0, n);
        Collections.sort(list);
        if (list.size() < k)
            return "";
        else
            return list.get(k - 1);
    }

    char[] chs = {'a', 'b', 'c'};

    public void helper(List<String> list, StringBuffer sb, int i, int n) {
        if (i == n) {
            list.add(sb.toString());
            return;
        }

        for (int k = 0; k < 3; k++) {
            if (sb.length() == 0) {
                sb.append(chs[k]);
                helper(list, sb, i + 1, n);
                sb.deleteCharAt(sb.length() - 1);
            } else {
                char pre = sb.charAt(sb.length() - 1);
                if (chs[k] == pre)
                    continue;
                else {
                    sb.append(chs[k]);
                    helper(list, sb, i + 1, n);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    static long mod = (long) 1e9 + 7;

    public int numberOfArraysback(String s, int k) {
        int n = s.length();
        long[] dp = new long[n + 1];
        //dp[i] : s[i:n]

        // "": means 1
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i++) {
            if (s.charAt(i) == '0')
                continue;
            long sum = 0;
            for (int j = i + 1; j <= n; j++) {
                sum = sum * 10 + s.charAt(j - 1) - '0';
                if (sum > k)
                    break;
                dp[i] = (dp[i] + dp[j]) % mod;
            }
        }
        return (int) dp[0];
    }

    public int numberOfArrays(String s, int k) {
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            long base = 1;
            long cur = 0;
            for (int j = i; j > 0 && i - j < 10; j--) {
                cur = cur + base * (s.charAt(j - 1) - '0');
                base *= 10;
                if (cur > k)
                    break;
                if (s.charAt(j - 1) == '0')
                    continue;

                dp[i] = (dp[i] + dp[j - 1]) % mod;
                //System.out.println(dp[j]);
            }
        }
        return (int) (dp[n] % mod);
    }

    public int numberOfArraysTOL(String s, int k) {

        int n = s.length();
        long[][] dp = new long[n][n];
        int mb = 0;
        int temp = k;
        while (temp > 0) {
            temp /= 10;
            mb++;
        }

        for (int i = 0; i < n; i++) {
            int cur = s.charAt(i) - '0';
            if (cur >= 1 && cur <= k)
                dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == '0')
                    continue;

                if (len <= mb) {
                    int cur = Integer.parseInt(s.substring(i, j + 1));
                    if (cur <= k)
                        dp[i][j] = 1;
                }

                for (int t = i; t < j; t++) {
                    if (t - i + 1 <= mb) {
                        int cur = Integer.parseInt(s.substring(i, t + 1));
                        if (cur <= k && s.charAt(t + 1) != '0')
                            dp[i][j] = (dp[i][j] + dp[t + 1][j]) % mod;
                    }
                }
            }

        return (int) (dp[0][n - 1] % mod);
    }



}
