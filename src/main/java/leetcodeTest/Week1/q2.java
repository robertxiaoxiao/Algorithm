package leetcodeTest.Week1;/*
 * @author:
 * @date:  2019/11/11/011
 * @description:
 */

import java.util.HashMap;

public class q2 {
    public void oddnums(int n,int m ,int[][] matrix){
        /*
          arr[i][j] = sum[i] +sum[j];
         */
        int[] row=new int[n];
        int[] col=new int[m];
        for(int i=0;i<matrix.length;i++)
        {
            row[matrix[i][0]]++;
            col[matrix[i][1]]++;
        }
        int oddcnt=0 ;
        for (int i=0;i<n;i++)
            for(int j=0;j<n;j++)
            {
                if(((row[i]+col[j])&1)==1)
                    ++oddcnt;
            }

    }
}
