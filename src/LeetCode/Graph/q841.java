package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/8/008
 * @description:
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class q841 {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] keys = new boolean[n];
        boolean[] visited = new boolean[n];
        keys[0] = true;
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int key : rooms.get(cur)) {
                keys[key] = true;
                if (!visited[key]) {
                    visited[key] = true;
                    queue.add(key);
                }
            }
        }
        for (int i = 0; i < n; i++)
            if (!keys[i])
                return false;
        return true;
    }
}
