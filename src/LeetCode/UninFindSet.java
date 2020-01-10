package LeetCode;/*
 * @author: Robert
 * @date:  2019/12/17/017
 * @description:
 */

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

    public void print() {
        for (int i = 0; i < parent.length; i++)
            System.out.print(parent[i] + " ");
        System.out.println();
        for (int i = 0; i < parent.length; i++)
            System.out.print(i + " ");
    }

    //
    public int findParentUsingstack(int k) {
        int temp = k;
        while (temp != parent[temp]) {
            temp = parent[temp];
        }

        // compress path
        while (k != parent[k]) {
            int c = parent[k];
            parent[k] = temp;
            k = c;
        }
        return temp;
    }

    public static void main(String[] args) {
        int n = 5;
        UninFindSet ufs = new UninFindSet(5);
        for (int i = 0; i < 4; i++)
            ufs.union(i, i + 1);
        ufs.print();
        System.out.println(ufs.findParent(0));
        ufs.print();
    }
}
