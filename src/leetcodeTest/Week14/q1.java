package leetcodeTest.Week14;/*
 * @author: Robert
 * @date:  2020/2/9/009
 * @description:
 */

import java.util.HashSet;

public class q1 {


    public boolean checkIfExist(int[] arr) {
        if (arr.length < 2)
            return false;

        HashSet<Integer> hset = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (hset.contains(arr[i]))
                continue;
            int cur = arr[i];
            if (hset.contains(cur * 2))
                return true;

            if (cur % 2 == 0 && hset.contains(cur / 2))
                return true;
            hset.add(cur);
        }
        return false;
    }
}
