package leetcodeTest.Week19;

import BasicDStructure.Tree.TreeNode;

import java.util.*;

public class Solution {

    public List<Integer> luckyNumbers(int[][] matrix) {

        List<Integer> ans = new LinkedList<>();
        int n = matrix.length;
        if (n == 0)
            return ans;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int lastj = -1;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    lastj = j;
                }
            }
            boolean flag = true;
            for (int ti = 0; ti < n && flag; ti++) {
                if (ti != i && matrix[ti][lastj] >= min)
                    flag = false;
            }
            if (flag)
                ans.add(min);
        }
        return ans;
    }

    class CustomStack {

        int msize;
        LinkedList<Integer> stack;

        public CustomStack(int maxSize) {
            msize = maxSize;
            stack = new LinkedList<Integer>();
        }

        public void push(int x) {
            if (stack.size() < msize) {
                stack.addLast(x);
            }
        }

        public int pop() {
            if (stack.isEmpty())
                return -1;
            return stack.pollLast();
        }

        public void increment(int k, int val) {
            for (int i = 0; i < k && i < stack.size(); i++)
                stack.set(i, stack.get(i) + val);
        }
    }

    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        getNode(root, res);
        if (res.size() == 0)
            return null;
        return balanceBST(res, 0, res.size() - 1);
    }

    public TreeNode balanceBST(List<TreeNode> res, int l, int r) {
        if (l > r)
            return null;
        if (l == r)
            return res.get(l);

        int mid = (l + r) / 2;
        int right = mid + 1;
        int left = mid - 1;
        if (right <= r)
            res.get(right).left = null;
        if (left >= 0)
            res.get(left).right = null;
        TreeNode curroot = res.get(mid);
        curroot.left = balanceBST(res, l, mid - 1);
        curroot.right = balanceBST(res, mid + 1, r);
        return curroot;
    }

    public void getNode(TreeNode root, List<TreeNode> res) {
        if (root == null)
            return;
        getNode(root.left, res);
        res.add(root);
        getNode(root.right, res);
    }


    int MOD = (int) 1e9 + 7;

    public int maxPerformanceError(int n, int[] speed, int[] efficiency, int k) {

        List<int[]> persons = new LinkedList<>();
        for (int i = 0; i < n; i++)
            persons.add(new int[]{speed[i], efficiency[i]});

        Collections.sort(persons, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        //  dp[i][k] : maxtime
        // dp[i][k]=dp[i-1][k] ,(dp[i-1][k-1]+speed[i])*mineficient;

        long[][][] dp = new long[n + 1][k + 1][2];
        int max = 0;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= i && j < k; j++) {
                System.out.println(dp[i][j][1]);
                dp[i][j][1] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1]) + persons.get(i - 1)[0];
                dp[i][j][0] = Math.max(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]);
            }

        int curmax = -1;
        for (int ti = n; ti >= 1; ti--)
            for (int tj = k; tj >= 1; tj--) {
                curmax = (int) Math.max(curmax, dp[ti][tj][1] * persons.get(ti - 1)[1]);
                max = Math.max(max, curmax);
            }
        return max % MOD;
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<int[]> persons = new LinkedList<>();
        for (int i = 0; i < n; i++)
            persons.add(new int[]{speed[i], efficiency[i]});
        Collections.sort(persons, (a, b) -> b[1] - a[1]);
        long sum = 0;
        long max = -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] person : persons) {
            pq.add(person[0]);
            sum += person[0];
            if (pq.size() > k)
                sum -= pq.poll();
            max = Math.max(max, sum * person[1]);
        }
        return (int) (max % MOD);
    }

    public int maxPerformancePQ(int n, int[] speed, int[] efficiency, int k) {
        int[][] ess = new int[n][2];
        for (int i = 0; i < n; ++i)
            ess[i] = new int[]{efficiency[i], speed[i]};
        Arrays.sort(ess, (a, b) -> b[0] - a[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);
        long res = 0, sumS = 0;
        for (int[] es : ess) {
            pq.add(es[1]);
            sumS = (sumS + es[1]);
            if (pq.size() > k) sumS -= pq.poll();
            res = Math.max(res, (sumS * es[0]));
        }
        return (int) (res % (long) (1e9 + 7));
    }

    public int maxPerformanceTreeMap(int n, int[] speed, int[] efficiency, int k) {
        Integer index[] = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (a, b) -> efficiency[b] - efficiency[a]);
        long max = 0, sum = 0, count = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            map.put(speed[index[i]], 1 + map.getOrDefault(speed[index[i]], 0));
            sum += speed[index[i]];
            count++;
            if (count > k) {
                count--;
                sum -= map.firstKey();
                map.put(map.firstKey(), map.get(map.firstKey()) - 1);
                if (map.get(map.firstKey()) == 0) {
                    map.remove(map.firstKey());
                }
            }
            max = Math.max(max, sum * efficiency[index[i]]);
        }
        return (int) (max % 1000000007);
    }

}
