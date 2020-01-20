package LeetCode.Graph.Basic.MST;/*
 * @author: Robert
 * @date:  2020/1/20/020
 * @description:
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kruskal {

    class edge {
        int from;
        int to;
        int cost;
    }

    class ufs {
        int[] parent;

        public ufs(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        public int find(int t) {
            int p = parent[t];
            if (p != t)
                parent[t] = find(p);
            return parent[t];
        }

        public void union(int t, int s) {
            int pt = find(t);
            int ps = find(s);
            if (pt != ps) {
                parent[pt] = ps;
            }
        }
    }

    public int kruskal(List<edge> edges, int n) {
        ufs uset = new ufs(n);
        Collections.sort(edges, new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                return o1.cost - o2.cost;
            }
        });
        int ans = 0;
        for (edge e : edges) {
            int f = e.from;
            int t = e.to;
            if (uset.find(f) != uset.find(t)) {
                ans += e.cost;
                uset.union(f, t);
            }
        }
        return ans;
    }

}
