package LeetCode.Greedy;/*
 * @author: Robert
 * @date:  2019/12/25/025
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q1253 {

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> r1 = new LinkedList<>();
        List<Integer> r2 = new LinkedList<>();
        int dir = upper - lower;
        for (int num : colsum) {
            if (num == 2) {
                r1.add(1);
                r2.add(1);
                upper--;
                lower--;
            } else if (num == 0) {
                r1.add(0);
                r2.add(0);
            } else if (upper == 0 && lower == 0)
                return res;
            else if (upper > lower) {
                r1.add(1);
                r2.add(0);
                upper--;
            } else {
                r1.add(0);
                r2.add(1);
                lower++;
            }
        }

        if (lower == 0 && upper == 0) {
            res.add(r1);
            res.add(r2);
        }

        return res;

    }
}
