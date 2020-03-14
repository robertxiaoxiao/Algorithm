package leetcodeTest.Week18;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class solutions {

    public String generateTheString(int n) {
        if (n == 0)
            return "";
        if (n % 2 == 0) {
            int i = 0;
            StringBuilder sb = new StringBuilder();
            while (i < n - 1) {
                sb.append('a');
                i++;
            }
            sb.append('b');
            return sb.toString();
        } else {

            int mid = n / 2;
            int odd = -1;
            int even = -1;
            if (mid % 2 == 0) {
                even = mid;
                odd = even + 1;
            } else {
                odd = mid;
                even = odd + 1;
            }

            int i = 0;
            StringBuilder sb = new StringBuilder();
            while (i < odd) {
                sb.append('a');
                i++;
            }
            i = 0;
            while (i < even - 1) {
                sb.append('b');
                i++;
            }
            sb.append('c');
            return sb.toString();
        }
    }

    public class BinaryIndexedTree {

        // mutable sum range
        int[] nums;

        public BinaryIndexedTree(int n) {
            nums = new int[n + 1];
        }

        public void update(int i, int delta) {
            while (i < nums.length) {
                nums[i] += delta;
                i += lowbits(i);
            }
        }

        public int query(int x) {
            int sum = 0;
            while (x > 0) {
                sum += nums[x];
                x -= lowbits(x);
            }
            return sum;
        }

        public int lowbits(int x) {
            return (x & (-x));
        }


    }

    public int numTimesAllBlue(int[] light) {
        int n = light.length;

        BinaryIndexedTree bst = new BinaryIndexedTree(n);
        int cnt = 0;
        int curMax = light[0];
        for (int i : light) {
            bst.update(i, 1);
            curMax = Math.max(curMax, i);
            if (bst.query(curMax) == curMax)
                cnt++;
        }
        return cnt;
    }

    class Node {
        public Node(int ID, int managerID, int infortime) {
            this.ID = ID;
            this.managerID = managerID;
            this.subs = new LinkedList<>();
            this.infortime = infortime;
        }

        int ID;
        int managerID;
        List<Node> subs;
        int infortime;
        int maxtime;
    }

    public int numOfMinutesNodes(int n, int headID, int[] manager, int[] informTime) {
        Node boss = new Node(headID, -1, informTime[headID]);
        Node eboss = buildrelations(boss, n, manager, informTime);
        return eboss.maxtime;
    }

    public int numOfMinutesI(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] edges = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            if (i != headID) {
                if (edges[manager[i]] == null)
                    edges[manager[i]] = new LinkedList();
                edges[manager[i]].add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(headID);
        int[] dist = new int[n];
        int max = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int cost = informTime[cur];
            if (edges[cur] != null) {
                for (int i : edges[cur]) {
                    dist[i] = dist[cur] + cost;
                    max = Math.max(dist[i], max);
                    queue.add(i);
                }
            }
        }
        return max;
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (informTime[headID] == 0)
            return 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (manager[i] == headID) {
                max = Math.max(max, numOfMinutes(n, i, manager, informTime));
            }
        }
        return max + informTime[headID];
    }

    public Node buildrelations(Node boss, int n, int[] manager, int[] inforTime) {
        int bossid = boss.ID;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (manager[i] == bossid) {
                Node curnode = new Node(i, bossid, inforTime[i]);
                Node nnode = buildrelations(curnode, n, manager, inforTime);
                max = Math.max(max, nnode.maxtime);
                boss.subs.add(nnode);
            }
        }

        if (boss.infortime == 0)
            boss.maxtime = 0;
        else
            boss.maxtime = max + boss.infortime;
        return boss;
    }

    public int helper(Node boss) {
        if (boss.infortime == 0)
            return 0;
        int max = 0;
        for (Node sub : boss.subs)
            max = Math.max(max, helper(sub));
        return max + boss.infortime;
    }

    List<Integer>[] lists;
    double[][] dp;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        double[][] dp = new double[150][t + 1];
        if (n == 1)
            return 1.0;
        List<Integer>[] lists = new LinkedList[n + 1];
        for (int[] edge : edges) {
            int e1 = edge[0];
            int e2 = edge[1];
            if (lists[e1] == null)
                lists[e1] = new LinkedList<>();
            lists[e1].add(e2);
            if (lists[e2] == null)
                lists[e2] = new LinkedList<>();
            lists[e2].add(e1);
        }
        dp[1][0] = 1.0;
        for (int i = 1; i <= t; i++)
            dfs(0, 1, t);
        return dp[target][t];
    }

    public void dfs(int x, int p, int T) {
        int cnt = 0;
        for (int i : lists[x]) {
            if (i != p)
                cnt++;
        }

        if (cnt == 0)
            dp[x][T] += dp[x][T - 1];

        for (int i : lists[x]) {
            dp[i][T] += dp[x][T - 1] / cnt;
            dfs(i, x, T + 1);
        }
    }

   // standard dfs
   class Solution {
        LinkedList<Integer> adjListArray[];
        public double frogPosition(int n, int[][] edges, int t, int target) {
            if (n == 1) return 1.0;
            adjListArray = new LinkedList[n + 1];
            for(int i = 0; i <= n ; i++) adjListArray[i] = new LinkedList<>();
            for (int[] edge : edges) {
                adjListArray[edge[0]].add(edge[1]);
                adjListArray[edge[1]].add(edge[0]);
            }

            return dfs(1, t, target, new boolean[n + 1]);
        }

        private double dfs(int node, int t, int target, boolean[] visited) {
            if (node != 1 && adjListArray[node].size() == 1 || t == 0) {
                if (node == target)
                    return 1;
                else return 0;
            }
            visited[node] = true;
            double res = 0.0;
            for (int child : adjListArray[node]) {
                if (visited[child]) continue; // skip visited children
                res += dfs(child, t - 1, target, visited);
            }
            if (node != 1)
                return res * 1.0 / (adjListArray[node].size() - 1);
            else
                return res * 1.0 / (adjListArray[node].size());
        }
    }

}
