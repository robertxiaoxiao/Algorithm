package leetcodeTest.Tricks;/*
 * @author: Robert
 * @date:  2019/12/24/024
 * @description:
 */

public class Fast_Pow {

    /*
    求x^y?
y使用二进制数表示：

y = 2^y0 + 2^y1 + 2^y2 + 2^y3 + ... + 2^yn


x^y = x^(2^y0 + 2^y1 + 2^y2 + 2^y3 + ... + 2^yn)
    = x^(2^y0) * x^(2^y1) * x^(2^y2) * x^(2^y3) * ... * x^(2^yn-1) * x^(2^yn)

证明：x^(2^yn) = (x^(2^yn-1))^(2^yn-1), yn = 2 * yn-1 ?

     x^(2^yn) = x^(2^(2 *yn-1))
              = x^(2^yn-1 * 2^yn-1)
              = (x^(2^yn-1))^(2^yn-1)
     */
    public int fast_pow_backtracking(int base, int power) {
        if (base == 0) return 0;
        if (power == 0) return 1;
        if (power == 1) return base;

        if (power % 2 == 1)
            return base * fast_pow_backtracking(base * base, power / 2);

        return fast_pow_backtracking(base * base, power / 2);

    }

    // the res can be extended to matrix pow computation
    public int fast_pow(int x, int n) {
        int ans = x;
        n -= 1;
        while (n > 0) {
            if (n % 2 == 1)
                ans *= x;
            x = x * x;
            n /= 2;
        }
        return ans;
    }
}
