package leetcodeTest.Week15;/*
 * @author: Robert
 * @date:  2020/2/16/016
 * @description:
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class q4 {

    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int csum = 0;
        for (int i : target) {
            pq.add(-i);
            csum += i;
        }

        int cur = pq.peek();
        while (true) {
            cur = -pq.poll();
            if (cur == 1)
                return true;
            System.out.println(cur);
            int newcur = 2 * cur - csum;
            if (newcur > 0 && newcur < cur) {
                pq.add(-newcur);
                int rest = csum - cur;
                csum = newcur + rest;
            } else
                return false;
        }
    }

}
