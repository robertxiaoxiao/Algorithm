package leetcodeTest.Week13;/*
 * @author: Robert
 * @date:  2020/2/2/002
 * @description:
 */

import java.util.*;

public class q2 {

    public int minSetSize(int[] arr) {
        int size = arr.length;
        if (size == 0)
            return 0;

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i : arr) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<Integer> res = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int val : hm.values())
            res.add(val);

        int cnt = 0;
        int sum = 0;
        while (sum * 2 < size) {
            cnt++;
            sum += res.poll();
        }
        return cnt;
    }
}
