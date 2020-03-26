package LeetCode.Array;/*
 * @author: robert
 * @date:  2019/10/22/022
 * @description:
 */

import java.util.concurrent.ThreadPoolExecutor;

public class q45 {
    static int minSteps=Integer.MAX_VALUE;

    public static void main(String[] args) {

        int[] nums={1,1,1,2};
        System.out.println(jump(nums));
        System.out.println(jumpIterator(nums));

    }

    public static int jump(int[] nums) {
        int curend = 0;
        int curfar = 0;
        int step = 0;

        // we dont need to care about the furtherest jump from  the last index of nums
        for (int i = 0; i < nums.length-1; i++) {
            curfar = Math.max(curfar, i + nums[i]);
            if (i== curend) {
                step++;
                curend=curfar;
            }
            if(curend>=nums.length-1)
                return step ;

            if(curend<i)
                return 0;
        }

        return step;

    }

    public static int jumpIterator(int[] nums){
        helper(0,nums,0);
        return minSteps;
    }

    public static void helper(int cur ,int[] nums,int steps){

        if(steps>minSteps)
            return ;

        if(cur>=nums.length-1)
        {
           minSteps=Math.min(minSteps,steps) ;
           return ;
        }
        for(int i=1;i<=nums[cur];i++)
            helper(cur+i,nums,steps+1);
    }


}
