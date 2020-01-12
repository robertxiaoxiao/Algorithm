package leetcodeTest.Week10;/*
 * @author: Robert
 * @date:  2020/1/12/012
 * @description:
 */

public class q1 {
    public int[] getNoZeroIntegers(int n) {
        int[] ans=new int[2];
        for(int i=1;i<=n/2;i++)
        {
            if(judge(i)&&judge(n-i))
            {
                ans[0]=i;
                ans[1]=n-i;
                return ans;
            }
        }

        return ans;
    }

    public boolean judge(int i){
        if(i<10)
            return true;

        int temp=i;
        while(temp!=0)
        {
            int k=temp%10;
            if(k==0)
                return false;
            temp=(temp-k)/10;
        }
        return true;
    }

}
