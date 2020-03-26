package leetcodeTest.Week17;

public class q1 {

    public int[] smallerNumbersThanCurrent(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] < nums[i])
                    t++;
            }
            ans[i] = t;
        }
        return ans;
    }

}
