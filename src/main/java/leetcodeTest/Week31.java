package leetcodeTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Week31 {

    public int[] getStrongest(int[] arr, int k) {

        Arrays.sort(arr);
        int n = arr.length;
        int median = arr[(n - 1) / 2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                if (t1[0] == t2[0])
                    return t2[1] - t1[1];
                return t2[0] - t1[0];
            }
        });

        for (int i : arr) {
            pq.add(new int[]{Math.abs(i - median), i});
        }

        int[] res = new int[k];
        int idx = 0;
        while (!pq.isEmpty() && idx < k) {
            int[] cur = pq.poll();
            res[idx++] = cur[1];
        }

        return res;
    }

    class BrowserHistory {

        class Node {
            String url;
            Node pre;
            Node next;

            public Node(String url) {
                this.url = url;
            }
        }

        Node head;
        Node cur;

        public BrowserHistory(String homepage) {
            head = new Node("");
            head.next = new Node(homepage);
            cur = head.next;
            cur.pre = head;
        }

        public void visit(String url) {
            Node next = new Node(url);
            cur.next = next;
            next.pre = cur;
            cur = cur.next;
        }

        public String back(int steps) {
            Node temp = cur;
            while (temp != null && steps > 0) {
                temp = temp.pre;
                steps--;
                if (temp == head) {
                    cur = head.next;
                    return cur.url;
                }
            }
            return cur.url;
        }

        public String forward(int steps) {
            Node temp = cur;
            while (temp != null && steps > 0) {
                temp = temp.next;
                steps--;
            }
            if (temp == null)
                return cur.url;
            return temp.url;
        }

    }


    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {

        int[][][] dp = new int[m + 1][target + 1][n + 1];

        // dp[k][t][c] =  min(dp[m][t-1][~c] +val(m,k,c))
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= target; j++)
                Arrays.fill(dp[i][j], Integer.MAX_VALUE / 2);

        for (int i = 0; i <= n; i++)
            dp[0][0][i] = 0;

        for (int i = 1; i <= m; i++)
            for (int t = 1; t <= target; t++) {
                for (int c = 1; c <= n; c++) {
                    int curc = c;
                    for (int j = 0; j < i; j++) {

                        int val = getSum(houses, cost, j, i - 1, curc);

                        if (val == Integer.MAX_VALUE / 2)
                            continue;

                        int pre = Integer.MAX_VALUE;

                        for (int pc = 1; pc <= n; pc++) {
                            if (pc == curc)
                                continue;
                            pre = Math.min(dp[j][t - 1][pc], pre);
                        }

                        dp[i][t][c] = Math.min(dp[i][t][c], pre + val);
                    }

                }
            }

        int ans = Integer.MAX_VALUE;
        for (int c = 1; c <= n; c++)
            ans = Math.min(ans, dp[m][target][c]);

        if (ans >= Integer.MAX_VALUE / 2)
            return -1;

        return ans;
    }

    private int getSum(int[] houses, int[][] cost, int i, int j, int curc) {
        int sum = 0;
        for (int idx = i; idx <= j; idx++)
            if (houses[idx] == 0)
                sum += cost[idx][curc - 1];
            else if (houses[idx] == curc)
                continue;
            else
                return Integer.MAX_VALUE / 2;
        return sum;
    }


}
