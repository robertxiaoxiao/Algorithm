package leetcodeTest.Week22;


import java.util.*;

public class Solution {

    public List<Integer> minSubsequence(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> ans = new LinkedList<>();
        if (n == 1) {
            ans.add(nums[0]);
            return ans;
        }
        if (n == 0)
            return ans;

        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        int maxi = 0;
        for (int k = 1; k <= n; k++) {
            int p = pre[k];
            int rest = pre[n] - pre[k];
            if (rest > p) {
                maxi = Math.max(maxi, k);
            }
        }

        for (int i = n; i >= maxi; i--) {
            ans.add(nums[i - 1]);
        }
        return ans;
    }

    static String temp;

    public int numSteps(String s) {
        if (s.length() == 1)
            return 0;
        int cnt = 0;
        temp = s;
        while (temp.length() > 1) {
            String t = temp;
            if (t.charAt(temp.length() - 1) == '1')
                t = add(temp);
            else
                t = div(temp);
            temp = t;
            System.out.println(t);
            cnt++;
        }

        return cnt;
    }

    public String add(String s) {
        StringBuffer sb = new StringBuffer();
        int carry = 1;
        for (int i = s.length() - 1; i >= 0; i++) {
            int cur = s.charAt(i) - '0' + carry;
            if (cur == 2) {
                sb.append('0');
                carry = 1;
            } else {
                carry = 0;
                if (cur == 1)
                    sb.append('1');
                else
                    sb.append('0');
            }

        }
        if (carry == 1)
            sb.append('1');
        return sb.reverse().toString();
    }

    public String div(String s) {
        return s.substring(0, s.length() - 1);
    }


    String generate(int a, int b, int c, String aa, String bb, String cc) {
        if (a < b)
            return generate(b, a, c, bb, aa, cc);
        if (b < c)
            return generate(a, c, b, aa, cc, bb);
        if (b == 0)
            return aa.repeat(Math.min(2, a));
        int use_a = Math.min(2, a), use_b = a - use_a >= b ? 1 : 0;
        return aa.repeat(use_a) + bb.repeat(use_b) +
                generate(a - use_a, b - use_b, c, aa, bb, cc);
    }

    public String longestDiverseStringUsingrec(int a, int b, int c) {
        return generate(a, b, c, "a", "b", "c");
    }

    static List<Integer> idx;
    static int[] cnt;
    static char[] chars = {'a', 'b', 'c'};

    public String longestDiverseString(int a, int b, int c) {

        idx = new LinkedList<>();
        cnt = new int[3];
        idx.add(0);
        idx.add(1);
        idx.add(2);
        cnt[0] = a;
        cnt[1] = b;
        cnt[2] = c;

        StringBuffer sb = new StringBuffer();
        sb.append('t');
        boolean flag = true;
        while (flag) {
            Collections.sort(idx, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return cnt[o2] - cnt[o1];
                }
            });

            flag = false;
            for (int i = 0; i < 3 && !flag; i++) {
                int t = idx.get(i);
                /// NOTICE THERE : CHAR TO INTEGER
                if (sb.charAt(sb.length() - 1) - '0' != t && cnt[t] > 0) {
                    cnt[t]--;
                    sb.append(t);
                    flag = true;
                }
            }
        }

        int n = sb.length();
        StringBuffer ans = new StringBuffer();
        for (int i = 1; i < n; i++) {
            int t = sb.charAt(i) - '0';
            ans.append(chars[t]);
            if (cnt[t] > 0) {
                ans.append(chars[t]);
                cnt[t]--;
            }
        }
        return ans.toString();
    }

    static int[] f;

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        f = new int[n];
        Arrays.fill(f, Integer.MIN_VALUE);
        int key = dp(0, stoneValue, n);
        if (key > 0)
            return "Alice";
        else if (key == 0)
            return "Tie";
        else
            return "Bob";
    }

    public int dp(int i, int[] stoneValue, int n) {
        if (i == n)
            return 0;
        if (f[i] != Integer.MIN_VALUE)
            return f[i];
        int max = Integer.MIN_VALUE;
        if (i <= n - 3) {
            max = Math.max(stoneValue[i] - dp(i + 1, stoneValue, n), Math.max(stoneValue[i] + stoneValue[i + 1] - dp(i + 2, stoneValue, n), stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp(i + 3, stoneValue, n)));
        } else if (i <= n - 2) {
            max = Math.max(stoneValue[i] - dp(i + 1, stoneValue, n), stoneValue[i] + stoneValue[i + 1] - dp(i + 2, stoneValue, n));
        } else {
            max = stoneValue[i] - dp(i + 1, stoneValue, n);
        }

        return f[i] = max;
    }


}
