package leetcodeTest.Week5;/*
 * @author:
 * @date:  2019/12/8/008
 * @description:
 */

/*
    when committed ,you should put all test cases ac and then push ;
 */

import org.junit.Assert;
import org.junit.Test;

public class q1 {
    public int subtractProductAndSum(int n) {
        int sum = 0;
        int product = 1;
        int temp = n;
        while (temp != 0) {
            sum += temp % 10;
            product *= temp % 10;
            temp = (temp - (temp % 10)) / 10;
        }

        return product - sum;
    }

    @Test
    public void testAdd(){
        q1 c = new q1();
        int result = c.subtractProductAndSum(6);
        Assert.assertEquals(result, 0);
    }
}
