package Search.BinarySearch.bfs;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

import java.util.*;

public class bfstemple {

    public int bfs() {
        HashSet<Integer> seen = new HashSet<>();
        int steps = 0;
        int start = 0;
        int end = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int temp = queue.poll();
                if (temp == end)
                    return steps;

                for (Integer i : expend(temp))
                    if (!seen.contains(i)) {
                        seen.add(i);
                        queue.add(i);
                    }
            }
            ++steps;
        }
        // notfound
        return -1;
    }
    private List<Integer> expend(int temp) {
        return new LinkedList<>();
    }

}
