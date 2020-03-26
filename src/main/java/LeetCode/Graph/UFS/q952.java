package LeetCode.Graph.UFS;/*
 * @author: Robert
 * @date:  2020/1/10/010
 * @description:
 */

import java.util.HashMap;

public class q952 {

    public class UninFindSet {
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

        public boolean union(int k, int j) {
            int pk = findParent(k);
            int pj = findParent(j);
            if (pk == pj)
                return false;
            if (ranks[pk] < ranks[pj]) {
                parent[pj] = pk;
                ranks[pj]++;
            } else {
                parent[pk] = pj;
                ranks[pk]++;
            }
            return true;
        }

        public int findParent(int k) {
            if (k != parent[k])
                parent[k] = findParent(parent[k]);
            return parent[k];
        }
    }

    public int largestComponentSize(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int a : A)
            max = Math.max(a, max);

        int mlen = max + 1;
        UninFindSet uset = new UninFindSet(mlen);
        for (int a : A) {
            int ceil = (int) Math.ceil(Math.sqrt(a));
            for (int k = 2; k < ceil; k++)
                if (a % k == 0) {
                    uset.union(k, a);
                    uset.union(k, a / k);
                }
        }
        HashMap<Integer, Integer> hm = new HashMap<>();
        int maxlen = Integer.MIN_VALUE;
        for (int a : A) {
            int p = uset.findParent(a);
            int k = hm.getOrDefault(p, 0) + 1;
            hm.put(p, k);
            if (k > maxlen)
                maxlen = k;
        }
        return maxlen;
    }

    //All Pairs are TOL
    public int largestComponentSizeTOL(int[] A) {

        int n = A.length;
        int[][] graph = new int[n][n];
        UninFindSet uset = new UninFindSet(n);
        int[] size = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                if (!uset.union(i, j))
                    continue;
                if (getFactor(A[i], A[j]) != 1)
                    uset.union(i, j);
            }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int p = uset.findParent(i);
            size[p]++;
            if (size[p] > max)
                max = size[p];
        }
        return max;
    }

    public int getFactor(int x, int y) {

        if (x > y) {
            if (x % y == 0)
                return y;
            return getFactor(x - y, y);
        } else {
            if (y % x == 0)
                return x;
            return getFactor(y - x, x);
        }
    }
}
