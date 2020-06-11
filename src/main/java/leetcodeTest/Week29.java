package leetcodeTest;

import BasicDStructure.Tree.TreeNode;

import java.util.*;

public class Week29 {


    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] word = sentence.split(" ");
        int n = word.length;
        for (int i = 0; i < n; i++) {
            if (isprefix(word[i], searchWord))
                return i;
        }
        return -1;
    }

    private boolean isprefix(String s, String searchWord) {
        int n = searchWord.length();
        if (s.length() < searchWord.length())
            return false;
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == searchWord.charAt(i))
                i++;
            else
                return false;
        }
        return true;
    }

    public int maxVowels(String s, int k) {

        Deque<Integer> queue = new LinkedList<>();
        int i = 0;
        int n = s.length();
        int cnt = 0;
        int max = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (isVowel(ch)) {
                queue.addLast(i);
                cnt++;
            }
            while (!queue.isEmpty() && i - queue.getFirst() >= k) {
                cnt--;
                queue.pollFirst();
            }
            max = Math.max(max, cnt);
            i++;
        }
        return max;
    }

    public boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return true;
        return false;
    }


    static int[] arrs;

    public int pseudoPalindromicPaths(TreeNode root) {
        arrs = new int[10];
        if (root == null)
            return 0;
        arrs[root.val]++;
        return search(root);
    }

    public int search(TreeNode root) {
        if (root.left == null && root.right == null) {
            if (check())
                return 1;
            return 0;
        }
        int ans = 0;
        if (root.left != null) {
            arrs[root.left.val]++;
            ans += search(root.left);
            arrs[root.left.val]--;
        }

        if (root.right != null) {
            arrs[root.right.val]++;
            ans += search(root.right);
            arrs[root.right.val]--;
        }

        return ans;
    }

    public static boolean check() {
        int cnt = 0;
        for (int i : arrs)
            if (i % 2 == 1)
                cnt++;

        return cnt < 2;
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int n = Math.min(l1, l2);
        int N = n + 10;
        int[][][] dp = new int[l1][l2][N];
        int max = Integer.MIN_VALUE;

        for (int k = 1; k <= n; k++)
            for (int i = k - 1; i < l1; i++)
                for (int j = k - 1; j < l2; j++) {
                    if (k == 1)
                        dp[i][j][1] = nums1[i] * nums2[j];
                    else {
                        int tempmax = Integer.MIN_VALUE;
                        for (int ti = 0; ti < i; ti++)
                            for (int tj = 0; tj < j; tj++)
                                tempmax = Math.max(tempmax, dp[ti][tj][k - 1]);
                        dp[i][j][k] = tempmax + nums1[i] * nums2[j];
                    }
                    max = Math.max(max, dp[i][j][k]);
                }

        return max;
    }

    public int maxDotProductON2(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int INF = 1000000000;
        int ret = -INF;
        int[][] f = new int[n][m];
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= m; ++j) {
                ret = Math.max(ret, f[i - 1][j - 1] + a[i - 1] * b[j - 1]);
                f[i][j] = Math.max(f[i][j - 1],
                        Math.max(f[i - 1][j], f[i - 1][j - 1] + a[i - 1] * b[j - 1]));
            }
        return ret;
    }

}
