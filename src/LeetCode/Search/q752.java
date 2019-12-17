package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/17/017
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class q752 {


    // that method the queue will be large;
    public static int openLock(String[] deadends, String target) {

        HashSet<String> hset = new HashSet<>();
        HashSet<String> deadset = new HashSet<>();
        for (String word : deadends)
            deadset.add(word);

        Queue<String> queue = new LinkedList<>();
        String start = "0000";
        queue.add(start);
        hset.add(start);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                String cur = queue.poll();
                if (cur.equalsIgnoreCase(target))
                    return steps;

                for (int i = 0; i < 4; i++) {
                    for (int k = 0; k <= 1; k++) {
                        String next = rotate(cur, i, k);
                        System.out.println(next);
                        if (deadset.contains(next) || hset.contains(next))
                            continue;

                        hset.add(next);
                        System.out.println(next);
                        queue.add(next);
                    }
                }
            }
            ++steps;
        }
        return -1;
    }

    // each move one slot
    public static String rotate(String cur, int k, int dir) {
        StringBuffer sb = new StringBuffer();
        int rnum = cur.charAt(k)-'0';
        if (dir == 0) {
            rnum--;
            if (rnum == -1)
                rnum = 9;
        } else {
            rnum++;
            if (rnum == 10)
                rnum = 0;
        }
        sb.append(cur.substring(0, k) + rnum + cur.substring(k + 1));
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] deads = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(openLock(deads,target));
    }

}
