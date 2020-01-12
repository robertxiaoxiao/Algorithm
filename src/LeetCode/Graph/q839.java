package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/12/012
 * @description:
 */

import java.util.HashSet;

public class q839 {

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

    public int numSimilarGroups(String[] A) {
        int n = A.length;
        UninFindSet ufs = new UninFindSet(n);
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (connective(A[i], A[j]))
                    ufs.union(i, j);
        HashSet<Integer> hset = new HashSet<>();
        for (int i = 0; i < n; i++)
            hset.add(ufs.findParent(i));
        return hset.size();
    }

    public boolean connective(String a, String b) {
        if (a.equals(b))
            return true;
        int cnt = 0;
        int n = a.length();
        if (n != b.length())
            return false;
        int i = 0;
        while (i < n) {
            if (a.charAt(i) != b.charAt(i))
                cnt++;
            i++;
            if (cnt > 2)
                return false;
        }
        if (cnt == 2)
            return true;
        return false;
    }
}
