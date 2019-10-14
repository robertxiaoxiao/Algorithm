package LeetCode.Array;/*
 * @author: robert
 * @date:  2019/10/12/012
 * @description:
 */


import java.util.concurrent.AbstractExecutorService;

public class q4 {
    public static void main(String[] args) {
        AbstractExecutorService a  ;

    }

    public  static double findMedianSortedArrays(int[] arrayA,int[] arrayB ){
            int m=arrayA.length;
            int n=arrayB.length;
        // arrayA[m-1] <arrayB[0] ;

        if((m+n)%2==0)
            return findInArrays(arrayA,arrayB,0,m-1,0,n-1,(m+n)/2);
        else
            return (findInArrays(arrayA,arrayB,0,m-1,0,n-1,(m+n)/2)+findInArrays(arrayA,arrayB,0,m-1,0,n-1,(m+n)/2+1))/2;
    }

    //  array a
    public static double findInArrays(int[] a,int[] b, int starta ,int enda,int startb,int endb ,int posatEnd)
    {
        int halfa=(starta+enda)/2;
        int halfb=(startb+endb)/2;


        return 0;
    }

}
