package leetcodeTest.BiWeek1;/*
 * @author: Robert
 * @date:  2020/2/22/022
 * @description:
 */

import java.util.*;

public class q1 {
    public int[] sortByBits(int[] arr) {

        List<Integer> res = new LinkedList<>();
        for (int i : arr)
            res.add(i);

        Collections.sort(res, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (getBits(o1) == getBits(o2))
                    return o1 - o2;
                return getBits(o1) - getBits(o2);
            }
        });
        int[] ans=new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            ans[i] = res.get(i);
        return ans;
    }

    public int getBits(int a) {
        int cnt = 0;
        while (a != 0) {
            if (a % 2 == 1)
                cnt++;
            a = a >>1 ;

        }   System.out.println(cnt);
        return cnt;
    }


}
