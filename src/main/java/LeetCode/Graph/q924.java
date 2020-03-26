package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/20/020
 * @description:
 */

import javax.swing.*;
import java.util.*;

public class q924 {

    class UFS {
        int[] parent;
        int[] groups;
        public UFS(int n) {
            parent = new int[n];
            groups = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                groups[i] = 1;
            }
        }

        public int findParent(int n) {
            int p = parent[n];
            if (p != n)
                parent[n] = findParent(p);
            return parent[n];
        }

        public void union(int t, int k) {
            int pt = findParent(t);
            int pk = findParent(k);
            if (pt != pk) {
                if (groups[pt] < groups[pk]) {
                    groups[pk] += groups[pt];
                    parent[pt] = pk;
                } else {
                    groups[pt] += groups[pk];
                    parent[pk] = pt;
                }
            }
        }

        public int findGsize(int t) {
            return groups[findParent(t)];
        }
    }

    public int minMalwareSpreadufs(int[][] graph, int[] initial) {
        Arrays.sort(initial);
        int n = graph.length;
        UFS ufs = new UFS(n);
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] == 1)
                    ufs.union(i, j);
            }
        int ans = -1;
        int k = -1;
        for (int i = 0; i < initial.length; i++) {
            int t = ufs.findGsize(initial[i]);
            if (t > ans) {
                ans = t;
                k = initial[i];
            }
        }
        return k;
    }


    public int minMalwareSpread(int[][] graph, int[] initial) {
        int ans = -1;
        int k = -1;
        Arrays.sort(initial);
        for (int i : initial) {
            int temp = getInjected(graph, i);
            if (temp > ans) {
                ans = temp;
                k = i;
            }
        }
        return k;
    }

    public int getInjected(int[][] graph, int k) {
        int n = graph.length;
        int[] state = new int[n];
        dfs(state, graph, k);
        int ans = 0;
        for (int i : state) {
            ans += i;
        }
        return ans;
    }

    public void dfs(int[] state, int[][] graph, int cur) {
        if (state[cur] != 0)
            return;
        state[cur] = 1;
        for (int i = 0; i < graph.length; i++) {
            if (i != cur && graph[cur][i] == 1) {
                dfs(state, graph, i);
            }
        }
    }


}
