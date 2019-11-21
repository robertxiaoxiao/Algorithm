package leetcodeTest.Week1;/*
 * @author:
 * @date:  2019/11/11/011
 * @description:
 */
/*
  leetcode  200
 */
import java.util.LinkedList;
import java.util.List;

public class q3 {
    private class landUnit {
        int row;
        int col;

        landUnit(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    /*
      基本类型总是按值传递。对于对象来说，是将对象的引用也就是副本传递给了方法，
      在方法中只有对对象进行修改才能影响该对象的值，操作对象的引用时是无法影响对象。
     */
    /*
    -->堆内存是用来存放由new创建的对象和数组，即动态申请的内存都存放在堆内存
    -->栈内存是用来存放在函数中定义的一些基本类型的变量和对象的引用变量
例子：局部变量存放在栈；new函数和malloc函数申请的内存在堆；函数调用参数，函数返回值，函数返回地址存放在栈
     */
    /*
    如果将单个基本类型数组的元素传递给方法，并在方法中对 其进行修改，则在被调用方法结束执行时，该元素中存储的并不是修改后的值，
    因为这种元素是按值传递，如果传递的是数组的引用，则对数组元素的后续修改可以
    在原始数组中反映出来（因为数组本身就是个对象，int[] a = new int[2];，这里面的int是数组元素的类型，而数组元素的修改是操作对象）
     */
    public int closedIsland(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        /*
          find connected island ;
          check isIsland?
         */
        return 0;
    }

    public boolean connected(List<landUnit> connect ,landUnit cur){

        for(landUnit temp:connect)
        {
            if(temp.row==cur.row)
            {
                if(temp.col-cur.col==1||temp.col-cur.col==-1)
                    return true ;
            }
            if(temp.col==cur.col)
            {
                if(temp.row-cur.row==1||temp.row-cur.row==-1)
                    return true ;
            }
        }
        return false;
    }

    public void findConnectedIsland(int[][] grid, int n, int m, int curn, int curm, List<landUnit> connectedland, List<landUnit> seen, List<List<landUnit>> result) {
        if (curn >= n || curm >= m) {
            if (connectedland.size() != 0)
                result.add(new LinkedList<>(connectedland));
            return;
        }

        if (grid[curn][curm] == 0) {
            boolean hseen = false;
            for (landUnit l : seen)
                if (l.row == curn && l.col == curm) {
                    hseen = true;
                    break;
                }
            if (!hseen) {
                landUnit curland=new landUnit(curn, curm);
                seen.add(curland);
                if(connected(connectedland,curland))
                    connectedland.add(curland);
            }
        }

        findConnectedIsland(grid,n,m,curn+1,curm,connectedland,seen,result) ;
        findConnectedIsland(grid,n,m,curn,curm+1,connectedland,seen,result) ;
        findConnectedIsland(grid,n,m,curn-1,curm,connectedland,seen,result) ;
        findConnectedIsland(grid,n,m,curn,curm-1,connectedland,seen,result) ;

    }

}
