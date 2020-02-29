package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/22/022
 * @description:
 */

public class q87 {

    public boolean isScrambleDpSelf(String s1, String s2) {
        /*
         s1   i    i+q  i+k;
         s2   j    j+q  j+k;
                     j+k-q    j+k
        dp[i][j][k] =dp[i][j][q]&&dp[i+q][j+q][k-q]
        dp[i][j][k] =dp[i][j+k-q][q]&&dp[i+q][j][k-q]
         */
        if (s1.length() != s2.length())
            return false;

        if (s1.equals(s2))
            return true;
        int n = s1.length();

        int[] chars = new int[26];
        for (int i = 0; i < n; i++) {
            chars[s1.charAt(i) - 'a']++;
            chars[s2.charAt(i) - 'a']--;
        }
        for (int i : chars)
            if (i != 0)
                return false;

        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int k = 1; k <= n; k++)
            for (int i = 0; i + k <= n; i++)
                for (int j = 0; j + k <= n; j++) {
                    if (k == 1) {
                        dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        // if found available , then break;
                        for (int q = 1; q < k && !dp[i][j][k]; q++) {
                            dp[i][j][k] |= dp[i][j][q] && dp[i + q][j + q][k - q];
                            dp[i][j][k] |= dp[i][j + k - q][q] && dp[i + q][j][k - q];
                        }
                    }
                }

        return dp[0][0][n];
    }

    public boolean isScramble(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n != m) return false;
        if (n == 0) return true;
        if (s1.equals(s2)) return true;

        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s1.charAt(i) - 97]++;
            freq[s2.charAt(i) - 97]--;
        }
        // if freq[i] != 0  => different characters, hence node equality condition is violated.
        for (int i = 0; i < n; i++)
            if (freq[s1.charAt(i) - 97] != 0) return false;

        // The following loop has conditions similar to what we use for mirror trees.
        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if (isScramble(s1.substring(0, i), s2.substring(n - i))
                    && isScramble(s1.substring(i), s2.substring(0, n - i)))
                return true;
        }
        return false;
    }

    public boolean isScrambleDp(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int len = s1.length();
        /**
         * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
         * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
         * Let q be the length of a cut (hence, q < k), then we are in the following situation:
         *
         * S1 [   x1    |         x2         ]
         *    i         i + q                i + k - 1
         *
         * here we have two possibilities:
         *
         * S2 [   y1    |         y2         ]
         *    j         j + q                j + k - 1
         *
         * or
         *
         * S2 [       y1        |     y2     ]
         *    j                 j + k - q    j + k - 1
         *
         * which in terms of F means:
         *
         * F(i, j, k) = for some 1 <= q < k we have:
         *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
         *
         * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal
         * */
        boolean[][][] F = new boolean[len][len][len + 1];
        for (int k = 1; k <= len; ++k)
            for (int i = 0; i + k <= len; ++i)
                for (int j = 0; j + k <= len; ++j)
                    if (k == 1)
                        F[i][j][k] = s1.charAt(i) == s2.charAt(j);
                    else for (int q = 1; q < k && !F[i][j][k]; ++q) {
                        F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
                    }
        return F[0][0][len];
    }

}
