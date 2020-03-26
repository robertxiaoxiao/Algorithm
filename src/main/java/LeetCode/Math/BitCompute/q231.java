package LeetCode.Math.BitCompute;

public class q231 {

    public boolean isPowerOfTwo(int n) {
        for (int i = 0; n >= 0 && i <= 32; i++)
            if ((1 << i) == n)
                return true;
        return false;
    }
}
