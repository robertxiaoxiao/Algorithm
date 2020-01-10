package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/10/010
 * @description:
 */

public class q684 {

    class UninFindSet {
        int[] parent;
        int[] ranks;

        public UninFindSet(int n) {
            parent = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                ranks[i] = 0;
            }
        }
        // not pathcompressed
        public void union(int k, int j) {
            int pk = findParent(k);
            int pj = findParent(j);
            if (ranks[pk] < ranks[pj]) {
                parent[pj] = pk;
                ranks[pj]++;
            } else {
                parent[pk] = pj;
                ranks[pk]++;
            }
        }

        public int findParent(int k) {
            if (k != parent[k])
                parent[k] = findParent(parent[k]);
            return parent[k];
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UninFindSet uset = new UninFindSet(n);
        for (int[] edge : edges) {
            int n1 = edge[0] - 1;
            int n2 = edge[1] - 1;
            int p1 = uset.findParent(n1);
            int p2 = uset.findParent(n2);
            if (p1 == p2)
                return edge;
            else
                uset.union(n1, n2);
        }
        return new int[0];
    }
}
