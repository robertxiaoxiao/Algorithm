package LeetCode.Math.BitCompute;

import java.util.HashSet;

public class q762 {

    public int countPrimeSetBits(int L, int R) {

        HashSet<Integer> hashSet = new HashSet<>();
        int[] nums = {2, 3, 5, 7, 11, 13, 17, 19};
        for (int num : nums)
            hashSet.add(num);

        int res = 0;
        for (int i = L; i <= R; i++) {
            int cnt = 0;
            int t = 0;
            while (t < 20) {
                cnt += (i >> t++) & 1;
            }
            if (hashSet.contains(cnt))
                res++;
        }
        return res;
    }
}
