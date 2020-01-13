package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/13/013
 * @description:
 */

public class q685 {
    class UninFindSet {
        int[] parent;

        public UninFindSet(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // not pathcompressed
        public void union(int k, int j) {
            int pk = findParent(k);
            int pj = findParent(j);
            parent[pj] = pk;
        }

        public int findParent(int k) {
            if (k != parent[k])
                parent[k] = findParent(parent[k]);
            return parent[k];
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        return new int[0];
    }

}
