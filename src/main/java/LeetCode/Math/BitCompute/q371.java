package LeetCode.Math.BitCompute;

public class q371 {

    public int getSum(int a, int b) {
        if (b == 0)
            return a;
        int cur = a ^ b;
        int carry = (a & b) << 1;
        return getSum(cur, carry);
    }
}
