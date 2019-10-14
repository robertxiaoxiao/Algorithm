package DynamaicP;/*
 * @author: ROBERT
 * @date:  2019/7/16/016
 * @description:
 */

import java.sql.Struct;

public class DPAlo {


     static int cnt=0;
    /*
       一维递归
     */
    public int mincostTickets(int[] days, int[] costs){

        boolean[] includedays=new boolean[366];

        int[] dp=new int[366];

        for(int day :days)
          includedays[day]=true;


        for(int i=1;i<366;i++)
        {
            if(!includedays[i]) {
                dp[i]=dp[i-1];   // notice 迭代中每一步都要考虑清楚
                continue;
            }
            dp[i]=Integer.MAX_VALUE;
            dp[i]=Math.min(dp[i],dp[Math.max(0,i-1)]+costs[0]);
            dp[i]=Math.min(dp[i],dp[Math.max(0,i-7)]+costs[1]);
            dp[i]=Math.min(dp[i],dp[Math.max(0,i-30)]+costs[2]);

        }

        return dp[365];

    }




    public  static  int countSubstrings(String s) {

        int count = 0;
        char[] temp = s.toCharArray();
        boolean[][] dp = new boolean[temp.length][temp.length];

        int len = temp.length;
        boolean[] dps=new boolean[len];

        //明确状态转移方程，确定好求解顺序，一定要在二维数组中标记好求解次序。
        for (int i = len-1; i >= 0; i--)
            for(int j=i;j<=len-1;j++)
            {
                //多位
                if(i+1<=j-1&&dp[i+1][j-1]&&s.charAt(i)==s.charAt(j))
                {
                    dp[i][j]=true;
                    count+=1;
                }

                //双位
                if(i+1==j&&s.charAt(i)==s.charAt(j))
                {
                    dp[i][j]=true;
                    count+=1;
                }
                //单位
                if(i==j)
                {
                    dp[i][j]=true;
                    count+=1;
                }
            }


        //

        for ( int i = 0; i < len; i++)
            for ( int j = 0; j < len; j++)
            if(dp[i][j])
                System.out.println(s.substring(i,j+1));

            //完全背包问题 可用动态规划一维数组优化

        return count;
    }

    public  static  int countSubstringsRe(String s){
        helper(0,s);
        return cnt;

    }
    //递归解
    public  static  void   helper(int start,String s )
    {
        // return
        if(start==s.length())
            return;

        for(int i=0;i<s.length()-start;i++)
              if(judge(start,i+start,s))
                  cnt+=1;

        helper(start+1,s);


    }

    private static boolean judge(int start, int i, String s) {

        while(start<=i)
        {
            if(s.charAt(start)!=s.charAt(i))
                return false;

            start+=1;
            i-=1;
        }

        return true;

    }

    public static void main(String[] args) {

        System.out.println(countSubstringsRe("aaabbaaa"));
        System.out.println(countSubstrings("aaabbaaa"));
    }



}
