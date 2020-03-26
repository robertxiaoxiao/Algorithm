package leetcodeTest.Week3;/*
 * @author:
 * @date:  2019/11/24/024
 * @description:
 */
public class q2 {
    public int countServers(int[][] grid) {

        int row=grid.length ;
        if(row==0)
            return  0;
        int col=grid[0].length ;

        int cnt=0;
        for(int i=0;i<row;i++)
        {
            int temp=0;
            int tcol=-1;
            for(int j=0;j<col;j++) {
                temp += grid[i][j];
                if(grid[i][j]==1)
                    tcol=j;
            }
            if(temp>1)
                cnt+=temp ;
            else if(temp==1)
            {
                int tt=0;
                for(int  ti=0;ti<row;ti++)
                    tt+=grid[ti][tcol] ;
                if(tt>1)
                    cnt+=1;
            }
        }
        return cnt;

    }
}
