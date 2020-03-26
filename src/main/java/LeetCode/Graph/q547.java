package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/7/007
 * @description:
 */

import javax.xml.transform.Templates;
import java.lang.reflect.Parameter;
import java.util.*;

public class q547 {

    public int findCircleNumUsingbfs(int[][] M) {
        int n = M.length;
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        for (int i = 0; i < n; i++)
            hm.put(i, new LinkedList<>());

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (M[i][j] == 1) {
                    hm.get(i).add(j);
                    hm.get(j).add(i);
                }

        boolean[] visited = new boolean[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            cnt++;
            visited[i] = true;
            bfs(hm, i, visited);
        }
        return cnt;
    }

    public void bfs(HashMap<Integer, List<Integer>> hm, int cur, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(cur);
        while (!queue.isEmpty()) {
            int curnode = queue.poll();
            for (int nnode : hm.get(curnode))
                if (visited[nnode])
                    continue;
                else {
                    visited[nnode] = true;
                    queue.add(nnode);
                }
        }
    }

    public void dfs(HashMap<Integer, List<Integer>> hm, int cur, boolean[] visited) {
        for (int nnode : hm.get(cur)) {
            if (visited[nnode])
                continue;
            else {
                visited[nnode] = true;
                dfs(hm, nnode, visited);
            }
        }
    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        int[] friend = new int[n];
        for (int i = 0; i < n; i++)
            friend[i] = i;

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (M[i][j] == 1) {
                    int ti = findcommonfriens(friend, i);
                    int tj = findcommonfriens(friend, j);
                    friend[ti] = tj;
                }
        HashSet<Integer> hset = new HashSet<>();
        for (int i = 0; i < n; i++) {
            // it may not update the array (f[i]=i)
            friend[i] = findcommonfriens(friend, i);
            hset.add(friend[i]);
        }
        return hset.size();
    }

    public void union(int[] friend, int i, int j) {
        int ti = findcommonfriens(friend, i);
        int tj = findcommonfriens(friend, j);
        if (ti != tj) {
            friend[ti] = tj;
            //compress
            while (i != ti) {
                int k = friend[i];
                friend[i] = tj;
                i = k;
            }
        }
    }

    public int findcommonfriens(int[] friend, int i) {
        int temp = i;
        while (friend[temp] != temp)
            temp = friend[temp];

        while (i != temp) {
            int p = friend[i];
            friend[i] = temp;
            i = p;
        }
        return temp;
    }

    public static void main(String[] args) {

    }


}
