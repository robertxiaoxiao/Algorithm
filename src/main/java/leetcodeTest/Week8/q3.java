package leetcodeTest.Week8;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class q3 {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        if (arr[start] == 0)
            return true;

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> seen = new HashSet<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int cur = queue.poll();
                if (arr[cur] == 0)
                    return true;
                int nl = cur - arr[cur];
                int nr = cur - arr[cur];

                if (nl >= 0 && !seen.contains(nl))
                    queue.add(nl);

                if (nr < n && !seen.contains(nr))
                    queue.add(nr);
            }
        }

        return false;
    }

    public boolean expand(int cur, int[] arr, int left, int right, HashMap<Integer, Boolean> hm) {
        if (cur < left || cur > right)
            return false;
        if (hm.containsKey(cur))
            return hm.get(cur);

        int nl = cur - arr[cur];
        int nr = cur + arr[cur];
        if (arr[nl] == 0 || arr[nr] == 0)
            return true;

        boolean flag = false;
        if (nl >= left && !hm.containsKey(nl)) {
            flag |= expand(nl, arr, left, right, hm);
        }

        if (nr <= right) {
            flag |= expand(nr, arr, left, right, hm);
        }
        hm.put(cur, flag);
        return flag;
    }

    public void expand(int cur, int[] arr, int left, int right, HashSet<Integer> canget) {
        if (cur < left || cur > right)
            return;

        int nl = cur - arr[cur];
        int nr = cur + arr[cur];
        if (nl >= left && !canget.contains(nl)) {
            canget.add(nl);
            System.out.println(nl);
            expand(nl, arr, left, right, canget);
        }

        if (nr <= right && !canget.contains(nr)) {
            canget.add(nr);
            expand(nr, arr, left, right, canget);
        }
    }

}
