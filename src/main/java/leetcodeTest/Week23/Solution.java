package leetcodeTest.Week23;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<String> stringMatching(String[] words) {
        HashSet<String> ans = new HashSet<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String t = words[i];
            for (int j = 0; j < n; j++) {
                if (j != i && words[j].indexOf(t) != -1)
                    ans.add(t);
            }
        }

        return new LinkedList<>(ans);
    }

    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        int[] ans = new int[n];
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= m; i++)
            list.add(i);
        int idx = 0;
        for (int q : queries) {
            int curidx = list.indexOf(q);
            ans[idx++] = curidx;
            list.remove(curidx);
            list.add(0, q);
        }
        return ans;
    }

    public String entityParser(String text) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("&quot;", "\"");
        hm.put("&apos;", "'");
        hm.put("&amp;", "&");
        hm.put("&gt;", ">");
        hm.put("&lt;", "<");
        hm.put("&frasl;", "/");

        StringBuffer sb = new StringBuffer();
        int n = text.length(), i = 0;
        while (i < n) {
            char c = text.charAt(i);
            if (c == '&') {
                int len = 0;
                StringBuffer temp = new StringBuffer();
                while (i < n && len <= 5 && text.charAt(i) != ';') {
                    c = text.charAt(i++);
                    temp.append(c);
                    len++;
                }
                if (len > 5 || i >= n) {
                    sb.append(temp);
                    i--;
                } else {
                    String t = temp.toString();
                    if (hm.containsKey(t))
                        sb.append(hm.get(t));
                    else
                        sb.append(t);
                }
            } else {
                sb.append(c);
            }
            i++;
        }

        return sb.toString();
    }


    static int mod = (int) (1e9 + 7);

    public int numOfWays(int n) {

        //  flower : 1 2 4
        long[][][][] dp = new long[n + 1][3][3][3];
        long res = 0;
        for (int j1 = 0; j1 < 3; j1++)
            for (int j2 = 0; j2 < 3; j2++)
                for (int j3 = 0; j3 < 3; j3++) {
                    if (j1 == j2 || j2 == j3)
                        continue;
                    dp[1][j1][j2][j3] = 1;
                    if (n == 1)
                        res += dp[1][j1][j2][j3];
                }

        for (int i = 2; i <= n; i++) {
            for (int j1 = 0; j1 < 3; j1++)
                for (int j2 = 0; j2 < 3; j2++)
                    for (int j3 = 0; j3 < 3; j3++) {
                        if (j1 == j2 || j2 == j3)
                            continue;
                        for (int pj1 = 0; pj1 < 3; pj1++)
                            for (int pj2 = 0; pj2 < 3; pj2++)
                                for (int pj3 = 0; pj3 < 3; pj3++) {
                                    if (pj1 == j1 || pj2 == j2 || pj3 == j3)
                                        continue;
                                    else
                                        dp[n][j1][j2][j3] = (dp[n][j1][j2][j3] % mod + dp[n - 1][pj1][pj2][pj3] % mod) % mod;
                                }

                        if (i == n)
                            res = (res + dp[n][j1][j2][j3] % mod) % mod;
                    }
        }

        return (int) (res % mod);
    }

}
