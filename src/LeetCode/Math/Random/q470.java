package LeetCode.Math.Random;

public class q470 {
    public int rand7() {
        return -1;
    }

    public int rand10() {
        while (true) {
            int cnt = (rand7() - 1) * 7 + rand7() - 1;
            // 0 39 idx-based
            if (cnt < 40)
                return cnt % 10 + 1;
        }
    }

}
