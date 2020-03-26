package leetcodeTest.Week5;/*
 * @author:
 * @date:  2019/12/8/008
 * @description:
 */

import java.util.Arrays;

public class q3 {
    public int smallestDivisor(int[] nums, int threshold) {

        Arrays.sort(nums);
        int res = -1;
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (calnums(nums, nums[mid]) <= threshold) {
                --mid;
                while (mid>=0&&calnums(nums, nums[mid]) <= threshold)
                    --mid;
                int temp=nums[mid+1]-1;
                while(temp!=0&&calnums(nums,temp)<=threshold)
                    --temp;

                return temp+1;
            } else
                i = mid + 1;
        }
        return res;
    }
    private int calnums(int[] nums, int midval) {
        int sum = 0;
        for (int num : nums)
            sum += caldiv(num, midval);
        return sum;
    }


    public int caldiv(int num, int divisor) {
        return num % divisor == 0 ? num / divisor : num / divisor + 1;
    }

}
