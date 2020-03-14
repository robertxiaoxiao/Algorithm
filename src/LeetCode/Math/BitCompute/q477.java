package LeetCode.Math.BitCompute;

public class q477 {

    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                if ((num & (1 << i)) != 0)
                    cnt++;
            }
            res += (nums.length - cnt) * cnt;
        }
        return res;
    }
}