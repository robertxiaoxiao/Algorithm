package LeetCode.Array;/*
 * @author: robert
 * @date:  2019/10/22/022
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q5 {

    static int[] minDis = new int[8];
    static int[][] tempPointes = new int[8][2];
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < 8; i++)
            minDis[i] = 129;

        for (int i = 0; i < queens.length; i++) {
            int temp = searchDir(king, queens[i]);
            if (calculatDis(king, queens[i]) < minDis[temp]) {
                int[] tempP = new int[2];
                tempP[0] = queens[i][0];
                tempP[1] = queens[i][1];
                tempPointes[temp] = queens[i];
            }
        }

        for (int i = 0; i < 8; i++) {
            if (minDis[i] != 129) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(tempPointes[i][0]);
                list.add(tempPointes[i][1]);
                result.add(list);
            }
        }
        return result;
    }

    // mark the perPath
    public static int searchDir(int[] pA, int[] pB) {
        int xa = pA[0];
        int ya = pA[1];
        int xb = pB[0];
        int yb = pB[1];

        if (xa == xb) {
            if (ya > yb)
                return 6;
            else
                return 1;
        } else if (ya == yb) {
            if (xa > xb)
                return 3;
            else
                return 4;
        } else if ((yb - ya) == (xb - xa)) {
            if (yb > ya && xb > xa)
                return 7;
            else if (yb < ya && xb > xa)
                return 2;
            else if (yb < ya && xb < xa)
                return 0;
            else
                return 5;
        }
        return -1;
    }

    public static int calculatDis(int[] pA, int[] pB) {
        return (pA[0] - pB[0]) * (pA[0] - pB[0]) + (pA[1] - pB[1]) * (pA[1] - pB[1]);
    }


}
