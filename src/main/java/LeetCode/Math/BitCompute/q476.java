package LeetCode.Math.BitCompute;

public class q476 {

    public int findComplement(int num) {
        int res = 0;
        boolean flag = false;
        for (int i = 31; i >= 0; i--) {
            int cur = (num >> i) & 1;
            if (!flag && cur == 0)
                continue;
            flag = true;
            res += (1 - cur) << i;
        }
        return res;
    }

    public int findComplementON(int num) {
        int res = 0;
        int cnt = 0;
        while (num != 0) {
            int cur = num & 1;
            res += (1 - cur) << cnt++;
            num = num >> 1;
        }
        return res;
    }
}
