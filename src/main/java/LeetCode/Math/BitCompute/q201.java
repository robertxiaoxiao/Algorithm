package LeetCode.Math.BitCompute;

public class q201 {

    public int rangeBitwiseAnd(int m, int n) {

        int res = 0;
        for (int i = 0; (1l << i) <= m; i++) {
            if (((m >> i) & 1) == 1) {
                /* m 101010011
                     101010000
                     101100000
                */
                if ((m & (~((1 << i) - 1))) + (1 << i) > n)
                    res += 1 << i;
            }
        }

        int tm = n;
        while (tm > 0) {
            int lowbit = tm & (-tm);
            int nm = tm - 1;
            if (m > nm)
                res += lowbit;
            tm -= lowbit;
        }

        return res;
    }

    public int rangeBitwiseAndPrefixSum(int m, int n) {
        int diffBits = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            diffBits++;
        }
        return n << diffBits;
    }

    public static void main(String[] args) {
        System.out.println(2 & (-2));

    }
}
