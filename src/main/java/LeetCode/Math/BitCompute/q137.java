package LeetCode.Math.BitCompute;

public class q137 {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += (num >> i) & 1;
            }
            if (cnt % 3 == 1)
                res += 1 << i;
        }
        return res;
    }
}
