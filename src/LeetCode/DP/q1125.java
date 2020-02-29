package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/24/024
 * @description:
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class q1125 {

    public int[] smallestSufficientTeamUsinghashmap(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        int m = people.size();
        int[] peopleCompress = new int[m];
        for (int i = 0; i < peopleCompress.length; i++)
            peopleCompress[i] = compress(people.get(i), req_skills);

        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        hm.put(0, new LinkedList<>());

        for (int i = 1; i <= m; i++) {

            int k = peopleCompress[i - 1];
            // temp : dp[i]
            //  hm : dp[i-1]
            HashMap<Integer, List<Integer>> temp = new HashMap<>(hm);
            for (int pre : hm.keySet()) {
                int cur = pre | k;
                if (temp.get(cur) == null || temp.get(pre).size() + 1 < temp.get(cur).size()) {
                    temp.put(cur, new LinkedList<>(temp.get(pre)));
                    temp.get(cur).add(i - 1);
                }
            }
            hm = new HashMap<>(temp);
        }
        int endstate = (1 << n) - 1;
        List<Integer> res = hm.get(endstate);
        int size = res.size();
        int[] ans = new int[size];
        int i = 0;
        for (int num : res)
            ans[i++] = num;
        return ans;
    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        int n = req_skills.length;
        int m = people.size();
        int[] peopleCompress = new int[m];
        for (int i = 0; i < peopleCompress.length; i++)
            peopleCompress[i] = compress(people.get(i), req_skills);

        int[] dp = new int[1 << n];
        // HashMap<Integer, List<Integer>> path = new HashMap<>();
        state[] parent = new state[1 << n];


        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 1; i <= m; i++) {

            int k = peopleCompress[i - 1];

            if (k == 0)
                continue;
            for (int j = (1 << n) - 1; j >= 0; j--) {
                if (dp[j] + 1 < dp[j | k]) {
                    {
                        dp[j | k] = dp[j] + 1;
                        parent[j | k] = new state(j, i - 1);
                    }
                }
            }
        }
        int endstate = (1 << n) - 1;
        List<Integer> res = new LinkedList<>();
        while (endstate > 0) {
            state par = parent[endstate];
            res.add(par.ps);
            endstate = par.pre;
        }

        int size = res.size();
        int[] ans = new int[size];
        int i = 0;
        for (int num : res)
            ans[i++] = num;
        return ans;
        //  return  dp[m][1<<n-1];
    }

    // TOM
    public int[] smallestSufficientTeamTTE(String[] req_skills, List<List<String>> people) {

        int n = req_skills.length;
        int m = people.size();
        int[] peopleCompress = new int[m];
        for (int i = 0; i < peopleCompress.length; i++)
            peopleCompress[i] = compress(people.get(i), req_skills);

        int[] dp = new int[1 << n];
        // HashMap<Integer, List<Integer>> path = new HashMap<>();
        state[] parent = new state[1 << n];

        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 1; i <= m; i++) {

            int k = peopleCompress[i - 1];
            // cost of copying arr is expensive
            // compress ---> O(2^16*60)
            // compress ---->O(2^16*60    copy 2^16)
//            for (int j = (1 << n) - 1; j >= 0; j--)
//                dp[i][j] = dp[i - 1][j];
            int[] temp = dp.clone();
            if (k == 0)
                continue;
            for (int j = (1 << n) - 1; j >= 0; j--) {
                // dp[j] + 1 < temp[j|k] (it may update)
                if (dp[j] + 1 < temp[j | k] && dp[j] + 1 < dp[j | k]) {
                    {
                        temp[j | k] = dp[j] + 1;
                        parent[j | k] = new state(j, i - 1);
                    }
                }
            }
            dp = temp.clone();
        }
        int endstate = (1 << n) - 1;
        List<Integer> res = new LinkedList<>();
        while (endstate > 0) {
            state par = parent[endstate];
            res.add(par.ps);
            endstate = par.pre;
        }

        int size = res.size();
        int[] ans = new int[size];
        int i = 0;
        for (int num : res)
            ans[i++] = num;
        return ans;
        //  return  dp[m][1<<n-1];
    }

    public void print(int[][] dp) {

        int n = dp.length;
        int m = dp[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(dp[i][j] + "  ");
            System.out.println();
        }
    }

    private int compress(List<String> strings, String[] req_skills) {
        int mask = 0;
        for (String str : strings) {
            mask |= 1 << find(str, req_skills);
        }
        return mask;
    }

    public int find(String s, String[] req_skills) {
        for (int i = 0; i < req_skills.length; i++)
            if (req_skills[i].equalsIgnoreCase(s))
                return i;
        return -1;
    }

    static class state {
        int pre;
        int ps;

        public state(int cur, int ps) {
            this.pre = cur;
            this.ps = ps;
        }
    }
}
