package LeetCode.DP;

import java.util.ArrayList;

public class q466 {


    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (!ableToObtain(s1, s2))
            return 0;

        String s = s1;
        int cnt = 0, k = -1;
        ArrayList<String> ss = new ArrayList<>();
        ArrayList<Integer> cs = new ArrayList<>();
        ss.add("");
        cs.add(0);
        for (int i = 0; i <= n1; i++) {
            StringBuilder remainBuilder = new StringBuilder();
            cnt += getRemain(s, s2, remainBuilder);
            String remain = remainBuilder.toString();
            if ((k = ss.indexOf(remain)) != -1)
                break;
            ss.add(remain);
            cs.add(cnt);
            s = remain + s1;
        }

        if (k == -1)
            return cnt / n2;

        int cntofloop = cnt - cs.get(k), looplength = ss.size() - k;
        cnt = cs.get(k);
        n1 -= k;
        cnt += cntofloop * (n1 / looplength);

        // the remain of loop
        n1 %= looplength;
        cnt += cs.get(n1 + k) - cs.get(k);
        return cnt / n2;
    }

    // check if [s1. ∞] obtains s2
    private boolean ableToObtain(String s1, String s2) {
        boolean[] cnt = new boolean[26];
        for (char c : s1.toCharArray()) cnt[c - 'a'] = true;
        for (char c : s2.toCharArray()) {
            if (!cnt[c - 'a']) return false;
        }
        return true;
    }

    public int getRemain(String s, String s2, StringBuilder remain) {
        int cnt = 0;
        int pre = -1;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (s.charAt(i) == s2.charAt(j))
                j++;
            if (j == s2.length() && i < s.length()) {
                cnt++;
                j = 0;
                pre = i;
            }
        }
        remain.append(s.substring(pre + 1));
        return cnt;
    }
    
}
