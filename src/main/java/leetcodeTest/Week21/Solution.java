package leetcodeTest.Week21;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution {


    public int findLucky(int[] arr) {
        int N = 510;
        int[] cnt = new int[N];
        for (int i : arr)
            cnt[i]++;
        int pre = -1;
        for (int i = 1; i <= 500; i++)
            if (cnt[i] == i) {
                pre = i;
            }

        return pre;
    }

    public int numTeams(int[] rating) {

        int n = rating.length;
        int cnt = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++) {
                    long ans = (long) (rating[k] - rating[j]) * (long) (rating[j] - rating[i]);
                    if (ans > 0)
                        cnt++;
                }
        return cnt;
    }

    class UndergroundSystem {
        HashMap<Integer, String> checkin;
        HashMap<Integer, Integer> checkinTime;
        HashMap<String, Integer> tripcnt;
        HashMap<String, Integer> tripcnttime;

        public UndergroundSystem() {
            checkin = new HashMap<>();
            checkinTime = new HashMap<>();
            tripcnt = new HashMap<>();
            tripcnttime = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            checkin.put(id, stationName);
            checkinTime.put(id, t);
        }

        public void checkOut(int id, String stationName, int t) {
            String pre = checkin.get(id);
            StringBuffer sb = new StringBuffer();
            sb.append(pre + '-' + stationName);
            String cur = sb.toString();
            tripcnt.put(cur, tripcnt.getOrDefault(cur, 0) + 1);
            tripcnttime.put(cur, tripcnttime.getOrDefault(cur, 0) + t - checkinTime.get(id));
            checkin.remove(id);
            checkinTime.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            String sb = startStation + '-' + endStation;
            return (double) tripcnttime.get(sb) / (double) tripcnt.get(sb);

        }


    }


    static class KMP {
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

    public int findGoodStrings(int n, String s1, String s2, String e) {
        evil = e;
        KMP.build(evil);
        maxN = 510;
        maxM = 100;
        dp = new long[maxN][maxM];
        for (int i = 0; i < maxN; i++)
            Arrays.fill(dp[i], -1);

        s = s1;
        m = evil.length();
        len = n;

        long ans1 = dfs(0, 0, true);

        for (int i = 0; i < maxN; i++)
            Arrays.fill(dp[i], -1);

        s = s2;
        long ans2 = dfs(0, 0, true);
        long ans = (ans2 - ans1 + mod) % mod;

        if (!s1.contains(evil))
            ans++;
        return (int) ans;
    }

    static long[][] dp;
    String evil;
    String s;
    int len;
    int m;
    int maxN, maxM;
    static int mod = (int) 1e9 + 7;

    public long dfs(int x, int match, boolean flag) {
        if (match >= m)
            return 0;
        if (x >= len)
            return 1;
        if (!flag && dp[x][match] != -1)
            return dp[x][match];

        char lim = 'z';
        if (flag)
            lim = s.charAt(x);
        long res = 0;
        for (char c = 'a'; c <= lim; c++) {
            int nxt = match;
            while (nxt > 0 && evil.charAt(nxt) != c)
                nxt = KMP.next[nxt];

            if (evil.charAt(nxt) == c)
                nxt += 1;
            res = (res + dfs(x + 1, nxt, flag && (c == lim))) % mod;
        }

        //  dp[x][match][flag] < or =
        if (!flag)
            dp[x][match] = res;
        return res;
    }

}
