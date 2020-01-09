package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/9/009
 * @description:
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class q815 {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        int buses = 0;
        if (S == T)
            return 0;
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        for (int i = 0; i < routes.length; i++)
            for (int stop : routes[i]) {
                if (!hm.containsKey(stop))
                    hm.put(stop, new LinkedList<>());
                hm.get(stop).add(i);
            }

        boolean[] ridebuses = new boolean[500];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        while (!queue.isEmpty()) {
            buses++;
            int size = queue.size();
            while (size > 0) {
                size--;
                int cur = queue.poll();
                // if(cur==T)
                // it will be slower than  judging in the expansion precedence
                for (int route : hm.get(cur)) {
                    if (ridebuses[route])
                        continue;
                    for (int stop : routes[route]) {
                        if (cur == T)
                            return buses;
                        queue.add(stop);
                    }
                    ridebuses[route] = true;
                }
            }
            // buses++;
        }

        return -1;
    }

}
