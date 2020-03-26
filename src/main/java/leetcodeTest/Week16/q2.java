package leetcodeTest.Week16;/*
 * @author: Robert
 * @date:  2020/2/23/023
 * @description:
 */

import java.util.HashSet;

public class q2 {

    class UFS {
        int[] parent;

        public UFS(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        public int findparent(int n) {
            if (n != parent[n])
                parent[n] = findparent(parent[n]);
            return parent[n];
        }

        public void union(int i, int j) {
            int pa = findparent(i);
            int pb = findparent(j);
            parent[pb] = pa;
        }

        public boolean check() {
            int cnt = parent[0];
            for (int i = 1; i < parent.length; i++)
                if (parent[i] != cnt)
                    return false;
            return true;
        }

    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

        if (n == 0 || n == 1)
            return true;

        HashSet<Integer> left = new HashSet<>();
        for (int i : leftChild) {
            if (i != -1)
                left.add(i);
        }
        HashSet<Integer> right = new HashSet<>();
        for (int i : rightChild) {
            if (i != -1)
                right.add(i);
        }

        if (left.size() == 0 || right.size() == 0)
            return false;

        UFS ufs = new UFS(n);
        for (int i = 0; i < n; i++) {
            if (left.contains(i) && right.contains(i))
                return false;
            if (leftChild[i] != -1)
                ufs.union(i, leftChild[i]);
            if (rightChild[i] != -1)
                ufs.union(i, rightChild[i]);
        }

        return ufs.check();
    }

}
