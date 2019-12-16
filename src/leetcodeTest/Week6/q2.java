package leetcodeTest.Week6;/*
 * @author: Robert
 * @date:  2019/12/15/015
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q2 {


    static class node {
        int fnum;
        int size;

        public node(int fnum, int size) {
            this.fnum = fnum;
            this.size = size;

        }
    }

    public static List<Integer> sequentialDigitsUsing(int low, int high) {
        List<Integer> res = new LinkedList<>();

        for (int i = 2; i <= 9; i++) {
            for (int begin = 1; begin < 9; begin++) {
                int num =begin;
                int adder=begin;
                int k=1;
                boolean flag=true;
                while(k<i) {
                    adder++;
                    if (adder == 10) {
                        flag = false;
                        break;
                    }
                    num = num * 10+adder;
                    k++;
                }
                if(flag&&num>=low&&num<=high)
                    res.add(num);
            }
        }

        return res;

    }

    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new LinkedList<>();
        node firstnode = getFirstDigits(low);

        int size = firstnode.size;
        int added = getN(size, 1);
        int firstadded = getFirst(firstnode.fnum, size);
        int temp = firstadded;

        while (temp < low) {
            temp += added;
            if (temp % 10 == 0) {
                size++;
                temp = getFirst(1, size);
                added = added * 10 + 1;
            }
        }

        while (temp <= high) {
            res.add(temp);
            temp += added;
            if (temp % 10 == 0) {
                added = added * 10 + 1;
                size++;
                temp = getFirst(1, size);
                if (temp == -1 || temp > high)
                    return res;
            }
        }
        return res;
    }

    public static int getFirst(int f, int cursize) {

        if (f == 1 && cursize > 9)
            return -1;

        if (f == 1 && cursize == 9)
            return 123456789;

        if (f + cursize > 10)
            return getFirst(1, cursize + 1);

        int num = f;
        int size = cursize;
        size--;
        while (size != 0) {
            f++;
            if (f == 10)
                return getFirst(1, cursize + 1);
            num = num * 10 + f;
            size--;
        }
        return num;
    }

    public static int getN(int size, int base) {
        int ans = 0;
        while (size != 0) {
            ans = ans * 10 + base;
            size--;
        }
        return ans;
    }

    public static node getFirstDigits(int num) {
        int size = 0;
        int last = 0;
        int temp = num;
        while (temp != 0) {
            last = temp;
            temp = temp / 10;
            size++;
        }

        return new node(last, size);
    }

    public static void main(String[] args) {
        // System.out.println(getFirst(1,9));
        //  node test =getFirstDigits(8500);
        List<Integer> res = sequentialDigits(744, 1928);
    }

}
