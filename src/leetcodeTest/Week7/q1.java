package leetcodeTest.Week7;/*
 * @author: Robert
 * @date:  2019/12/22/022
 * @description:
 */

public class q1 {
    public int findNumbers(int[] nums) {

         int[] enen={10,99,1000,9999} ;
         int cnt=0;
         for(int num:nums)
         {
             if((num>=10&&num<=99)||(num>=1000&&num<=9999))
                 cnt++;
         }

        return cnt;

    }

}
