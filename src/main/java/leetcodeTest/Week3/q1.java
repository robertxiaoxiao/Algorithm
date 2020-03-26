package leetcodeTest.Week3;/*
 * @author:
 * @date:  2019/11/24/024
 * @description:
 */

public class q1 {
    public int minTimeToVisitAllPoints(int[][] points) {
        int  n=points.length ;
        if(n==0)
            return 0;

        int cnt=0;
        for(int i=0;i<n-1;i++)
            cnt+=calMinlength(points[i],points[i+1]);
        return cnt ;

    }

    public int calMinlength(int[] a1,int[] a2){

        int x1=a1[0];
        int y1=a1[1];
        int x2=a2[0];
        int y2=a2[0];
        int lenx=x1-x2>0?x1-x2:x2-x1;
        int leny=y1-y2>0?y1-y2:y2-y1;

        return lenx>leny?lenx:leny;

    }



}
