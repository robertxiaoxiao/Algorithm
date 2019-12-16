package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/14/014
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class NQueen {
    static int res = 0;

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> res = new LinkedList<>();
        LinkedList<Integer> temp = new LinkedList<>();
        HashSet<String> seen = new HashSet<>();
        boolean[] used = new boolean[n];
        dfs(0, temp, res, seen, used, n);
        return res;
    }



    // it didn't need to maintain a record when backtracking
    public void dfs(int cnt, LinkedList<Integer> temp, List<List<String>> res, HashSet<String> seen, boolean[] used, int size) {
        if (cnt == used.length) {
            StringBuffer sb = new StringBuffer();
            for (int i : temp)
                sb.append(i);
            String str = sb.toString();
//            if (seen.contains(str))
//                return;
//            seen.add(str);
            res.add(buildfromString(str));
        }
        for (int i = 0; i < size; i++) {
            if (used[i])
                continue;

            if (!check(cnt, i, temp))
                continue;
            used[i] = true;
            temp.add(i);
            dfs(cnt + 1, temp, res, seen, used, size);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    private boolean check(int cnt, int i, LinkedList<Integer> temp) {

        int j = 0;
        boolean flag = true;
        for (int num : temp) {
            if (cnt - j == i - num || cnt - j == num - i)
                flag = false;
            j++;
        }
        return flag;
    }

    private List<String> buildfromString(String str) {
        List<String> temp = new LinkedList<>();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            StringBuffer sb = new StringBuffer();
            int j = 0;
            while (j < n) {
                if (j == str.charAt(i) - '0')
                    sb.append('Q');
                else
                    sb.append('.');
                ++j;
            }
            temp.add(sb.toString());
        }
        return temp;
    }

}
