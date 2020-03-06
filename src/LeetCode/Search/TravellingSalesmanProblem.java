package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/13/013
 * @description:
 */

import java.util.Arrays;
import java.util.Stack;

public class TravellingSalesmanProblem {
    int nodes;
    int[][] cost;

    public TravellingSalesmanProblem(int nodes, int[][] cost) {
        this.nodes = nodes;
        this.cost = cost;
    }

    //  1  2 3 4 5
    // dp[i][j]:  the min cost  travelling  through nodes set(i)  and end at node j ;
    // path[i][j] : the order in set for the result backtracking
    // i = 10001

    public int minCostTraverse() {
        int n = nodes;
        int[][] dp = new int[1 << n][n];
        int[][] path = new int[1 << n][n];
        int min = Integer.MAX_VALUE;
        int last = -1;
        /*
         traverse from node 0;
         the  minimal vertex order is the only one in the solution of TSP ,so we set the begin node at node 0
        */
        for (int i = 1; i < (1 << n); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                // traverse all possible nodes except itself
                if ((i & (1 << j)) > 0) {
                    int prenodes = i - (1 << j);
                    if (prenodes == 0)
                        // if set is empty ,we should initiate the first cost of node i to node j
                        dp[i][j] = cost[j][0];
                    else {
                        //  {1,2,3,4} ,4   {1} 1
                        for (int k = 0; k < n; k++) {
                            if (dp[prenodes][k] < Integer.MAX_VALUE && dp[prenodes][k] + cost[k][j] < dp[i][j]) {
                                dp[i][j] = dp[prenodes][k] + cost[k][j];
                                path[i][j] = k;
                                System.out.printf("%d  %d\r\n", dp[i][j], k);
                            }
                        }
                    }
                }
                if (i == (1 << n) - 1 && dp[i][j] + cost[j][0] < min) {
                    min = dp[i][j] + cost[j][0];
                    last = j;
                }
            }
        }

        int endnode = last;
        int pathend = (1 << n) - 1;

        Stack<Integer> stack = new Stack<>();
        while (pathend > 0) {
            stack.push(endnode);
            int temp = pathend;
            System.out.println("currnet push into stack :  " + endnode);
            pathend = pathend - (1 << endnode);
            endnode = path[temp][endnode];
        }

        // print path
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "   ");
        }

        return min;
    }

    public static void main(String[] args) {
        //System.out.println(1 << 0);
        int nodes = 4;
        int[][] cost = {
                {0, 3, 6, 7}, {5, 0, 2, 3}, {6, 4, 0, 2}, {3, 7, 5, 0}
        };

        TravellingSalesmanProblem tsp = new TravellingSalesmanProblem(nodes, cost);
        System.out.println(tsp.minCostTraverse());
    }


}
