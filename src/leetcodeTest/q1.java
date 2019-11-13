package leetcodeTest;/*
 * @author:
 * @date:  2019/11/10/010
 * @description:
 */


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class q1 {
    public static void main(String[] args) {
        int[] colsum={0,1,0,1,2,1,1};
        System.out.println(reconstructMatrix(5,1,colsum).size());
    }
    public void reconstructMatrixG(int upper ,int lower ,int[] colsum){
        //  0 2 ;
        int size=colsum.length;
        int[] row1= new int[size];
        int[] row2= new int[size];

        for(int i=0;i<size;i++)
            if(colsum[i]==2){
                row1[i]=1;
                row2[i]=1;
                --upper;
                --lower;
             }

        for(int i=0;i<size;i++)
        {
            if(colsum[i]==1) {
                if (upper > lower)
                {
                    row1[i]=1;
                    --upper;
                }
                else
                {
                    row2[i]=1;
                    --lower;
                }
            }
        }




    }
    public static List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int len=colsum.length;
        List<Integer>   list0=new LinkedList<>();
        List<Integer>   list1=new LinkedList<>();
        List<List<Integer>> result= new LinkedList<>();
        int cur0sum=0;
        int cur1sum=0;
        helper(list0,list1,0,len,cur0sum,cur1sum,result,upper,lower,colsum);
        return result;

    }

    public static void helper(List l0,List l1 ,int curidx ,int len ,int cur0sum,int cur1sum,List result,int upper, int lower, int[] colsum) {
        if (curidx == len) {
            if(result.size()==0&&cur0sum==upper&&cur1sum==lower) {
                result.add(new LinkedList<>(l0));
                result.add(new LinkedList<>(l1));
            }
            return;
        }

        for (int i = 0; i <= 1; i++) {
            int idx1 = colsum[curidx] - i;

            if(idx1<0||idx1>1)
                continue;

            if (cur0sum + i <= upper && cur1sum + idx1 <=lower) {
                l0.add(i);
                l1.add(idx1);
                helper(l0, l1, curidx + 1, len, cur0sum + i, cur1sum + idx1, result, upper, lower, colsum);
                l0.remove(l0.size() - 1);
                l1.remove(l1.size() - 1);
            }
        }

    }
}
