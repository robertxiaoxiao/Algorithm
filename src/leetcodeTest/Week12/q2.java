package leetcodeTest.Week12;/*
 * @author: Robert
 * @date:  2020/1/26/026
 * @description:
 */

import java.util.*;

public class q2 {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ans = new LinkedList<>();
        List<int[]> candidates = new LinkedList<>();

        for (int[] res : restaurants) {
            if ((veganFriendly == 0 || res[2] == veganFriendly) && res[3] <= maxPrice && res[4] <= maxDistance)
                candidates.add(res);
        }
        Collections.sort(candidates,new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[1] == o1[1]) {
                    return o2[0] - o1[0];
                }
                return o2[1] - o1[1];
            }
        });

        for (int[] ct : candidates) {
            ans.add(ct[0]);
        }
        return ans;
    }

}
