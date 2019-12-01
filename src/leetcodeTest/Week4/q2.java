package leetcodeTest.Week4;/*
 * @author:
 * @date:  2019/12/1/001
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q2 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {

        List<Integer> res = new LinkedList<>();
        int x1 = 0;
        int x2 = cheeseSlices;
        int reTo = tomatoSlices - 2 * x2;
        if (tomatoSlices > 4 * cheeseSlices || tomatoSlices < 2 * cheeseSlices || reTo < 0 || reTo % 2 != 0)
            return res;
        x1 = reTo / 2;
        x2 -= x1;
        res.add(x1);
        res.add(x2);
        return res;

    }
}
