package leetcodeTest.Week5;/*
 * @author:
 * @date:  2019/12/8/008
 * @description:
 */

public class q1 {
    public int subtractProductAndSum(int n) {

        int sum = 0;
        int product = 1;
        int temp = n;
        while (temp != 0) {
            sum += temp %10;
            product *= temp %10;
            temp =(temp - (temp % 10) )/ 10;
        }

        return product -sum ;
    }


}
