package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/19/019
 * @description:
 */

public class q309 {

    /*
     in one dimension Dynamic programming solution ,we need to check out all different state in each element(day)
     and checkout all state transfer proceed , the dp definition should be figured out clearly and the initial state
     should be set correctly ,and we always append the dp arr ,the idx of corresponding element should be checked at
     after-padding iteration
     */

    public int maxProfit(int[] prices) {

        //  dp[i][0] =dp[i-1][0]
        // curMin  curMax curCoolDownday
        int n=prices.length ;

        int[]  hold=new int[n+1] ;
        int[]  rest=new int[n+1] ;
        int[]  sold=new int[n+1] ;
        /*
          if  i hold : i+1 hold ,sold
          if  i sold : i+1 rest
          if  i rest : i+1 rest hold
         */
        hold[0]=Integer.MIN_VALUE;
        rest[0]=0;
        sold[0]=0;
        for(int i=1;i<=n;i++)
        {
            hold[i]=Math.max(rest[i-1]-prices[i-1],hold[i-1]);
            rest[i]=Math.max(hold[i-1]+prices[i-1],rest[i-1]);
            sold[i]=hold[i-1]+prices[i-1];
        }
        return  Math.max(rest[n],sold[n]);
    }



}
