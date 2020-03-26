package leetcodeTest.Week2;/*
 * @author:
 * @date:  2019/11/17/017
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q1 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> result=new LinkedList<>();
            int n=grid.length;
            if(n==0)
                return result ;
            int m=grid[0].length;

            for(int i=0;i<k;i++)
                shiftOnce(grid,n,m);
            for(int i=0;i<n;i++) {
                List<Integer> temp=new LinkedList<>();
                for (int j = 0; j < m; j++)
                   temp.add(grid[i][j]) ;

                result.add(temp);
            }

            return result;
    }

    public  void shiftOnce(int[][] grid,int n,int m){
        int[]  lastcol=new int[n] ;
        //swap
        int temp =grid[n-1][m-1];
        for(int i=n-2;i>=0;i--)
            lastcol[i+1]=grid[i][m-1];
        lastcol[0]=temp;

       for(int i=0;i<n;i++) {
           for (int j = m - 2; j >= 0; j--)
               grid[i][j + 1] = grid[i][j];
           grid[i][0]=lastcol[i];
       }
    }

}
