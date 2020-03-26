package leetcodeTest.BiWeek2;

import LeetCode.Tree.TreeNode;

import java.util.Arrays;

public class Solutions {

    public String sortString(String s) {
        int[] chars = new int[26];
        int n = s.length();
        for (char c : s.toCharArray())
            chars[c - 'a']++;
        int cnt = 0;
        StringBuffer sb = new StringBuffer();
        while (cnt < n) {
            int i = 0;
            for (i = 0; i < 26; i++) {
                if (chars[i] > 0) {
                    sb.append((char) ('a' + i));
                    chars[i++]--;
                    cnt++;
                    break;
                }
            }

            while (i < 26) {
                if (chars[i] > 0) {
                    sb.append((char) ('a' + i));
                    chars[i]--;
                    cnt++;
                }
                i++;
            }
            i = 25;
            for (i = 25; i >= 0; i--) {
                if (chars[i] > 0) {
                    sb.append((char) ('a' + i));
                    chars[i--]--;
                    cnt++;
                    break;
                }
            }
            while (i >= 0) {
                if (chars[i] > 0) {
                    sb.append((char) ('a' + i));
                    chars[i--]--;
                    cnt++;
                }
                i--;
            }
        }
        return sb.toString();
    }

    public int[] state = new int[32];

    public int findTheLongestSubstring(String s) {
        int n = s.length();
        Arrays.fill(state, n);
        state[0] = -1;
        int[] os = new int[5];
        int cnt = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a')
                os[0]++;
            if (ch == 'e')
                os[1]++;
            if (ch == 'i')
                os[2]++;
            if (ch == 'o')
                os[3]++;
            if (ch == 'u')
                os[4]++;
            int sum = 0;
            int t = 0;
            for (int k : os) {
                if ((k & 1) == 1)
                    sum += 1 << t;
                t++;
            }
            if (state[sum] != n) {
                max = Math.max(max, i - state[sum] + 1);
            } else
                state[sum] = i;
        }
        return max;
    }

    int max = 0;

    public int maxSumBST(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }

    // min, max ,sum ,isvalid
    public int[] helper(TreeNode root) {
        if (root == null)
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1};
        int[] l = helper(root.left);
        int[] r = helper(root.right);
        boolean isvalid = false;
        if (l[3] == 1 && r[3] == 1 && root.val > l[1] && root.val < r[0])
            isvalid = true;
        int sum = root.val;
        sum += l[2] + r[2];
        if (isvalid && sum > max)
            max = sum;

        int curmin = Math.min(l[0], root.val);
        int curmax = Math.max(r[1], root.val);
        return new int[]{curmin, curmax, sum, isvalid ? 1 : 0};
    }

    public int longestZigZag(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }

    // {{len,dir} } -1 0 1
    public int[] helperZig(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};

        int[] l = helperZig(root.left);
        int[] r = helperZig(root.right);
        int lmax = l[1] + 1;
        int rmax = r[0] + 1;
        int m = Math.max(lmax, rmax);
        if (m > max)
            max = m;
        return new int[]{lmax, rmax};
    }
}
