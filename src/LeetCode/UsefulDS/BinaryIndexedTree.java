package LeetCode.UsefulDS;/*
 * @author: Robert
 * @date:  2020/1/20/020
 * @description:
 */

public class BinaryIndexedTree {

    // mutable sum range
    int[] nums;

    public BinaryIndexedTree(int n) {
        nums = new int[n + 1];
    }

    public void update(int i, int delta) {
        while (i < nums.length) {
            nums[i] += delta;
            i += lowbits(i);
        }
    }

    public int query(int x) {
        int sum = 0;
        while (x > 0) {
            sum += nums[x];
            x -= lowbits(x);
        }
        return sum;
    }

    public int lowbits(int x) {
        return (x & (-x));
    }


}
