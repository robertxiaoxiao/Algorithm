package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/17/017
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        dfs(0, s, temp, res);
        return res;
    }

    public void dfs(int start, String s, List<String> temp, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new LinkedList<>(temp));
            return;
        }

        for (int j = start + 1; j <= s.length(); j++) {
            String cur = s.substring(start, j);
            if (!check(cur))
                continue;
            temp.add(cur);
            dfs(j, s, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    private boolean check(String cur) {
        int i = 0;
        int j = cur.length() - 1;
        while (i < j) {
            if (cur.charAt(i) != cur.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }


}
