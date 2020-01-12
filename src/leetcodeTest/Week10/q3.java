package leetcodeTest.Week10;/*
 * @author: Robert
 * @date:  2020/1/12/012
 * @description:
 */

import LeetCode.Graph.UFS.UninFindSet;

public class q3 {
    public int makeConnected(int n, int[][] connections) {
        // we should use the component group size  cSize-1 to connect all components
        UninFindSet uset = new UninFindSet(n);
        int cnt = 0;
        for (int[] connection : connections) {
            int t1 = connection[0];
            int t2 = connection[1];
            if (uset.findParent(t1) == uset.findParent(t2))
                cnt++;
            else
                uset.union(connection[0], connection[1]);
        }
        int[] parent = new int[n];
        int max = Integer.MIN_VALUE;
        int j = -1;
        for (int i = 0; i < n; i++) {
            int k = uset.findParent(i);
            parent[k]++;
            if (parent[k] > max) {
                max = parent[k];
                j = k;
            }
        }
        int ops = 0;
        for (int i = 0; i < n; i++) {
            if (uset.findParent(i) != j) {
                uset.union(i, j);
                ops++;
            }
        }
        return ops <= cnt ? ops : -1;
    }

}
