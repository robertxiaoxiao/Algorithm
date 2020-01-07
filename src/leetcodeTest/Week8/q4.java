package leetcodeTest.Week8;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.*;

public class q4 {

    static HashMap<Character, Integer> hm = new HashMap<>();
    int[] left = new int[15];
    int[] right = new int[15];
    static boolean[] canzero = new boolean[26];
    boolean[] visited = new boolean[15];

    public boolean isSolvable(String[] words, String result) {
        hm.clear();
        Arrays.fill(visited, false);
        Arrays.fill(canzero, true);
        Arrays.fill(left, 0);
        Arrays.fill(right, 0);

        int numc = 0;
        // encode
        for (String word : words) {
            canzero[word.charAt(0) - 'A'] = false;
            for (char c : word.toCharArray())
                if (!hm.containsKey(c))
                    hm.put(c, numc++);
        }
        canzero[result.charAt(0) - 'A'] = false;
        for (char c : result.toCharArray())
            if (!hm.containsKey(c))
                hm.put(c, numc++);

        for (String word : words)
            calcuateWeight(word, left);
        calcuateWeight(result, right);
        return dfs(0, numc, 0, 0);
    }

    public boolean dfs(int cur, int n, int l, int r) {
        if (cur >= n) {
            return l == r;
        }

        for (int i = 0; i <= 9; i++) {
            if (!canzero[cur] && i == 0)
                continue;

            if (visited[i])
                continue;

            visited[i] = true;
            boolean flag = dfs(cur + 1, n, l + i * left[cur], r + i * right[cur]);
            if (flag)
                return true;
            visited[i] = false;
        }
        return false;
    }

    public void calcuateWeight(String s, int[] arr) {
        int w = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            arr[hm.get(s.charAt(i))] += w;
            w *= 10;
        }
    }

}
