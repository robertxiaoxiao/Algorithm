package LeetCode.DP;

public class q838 {


    /*
    Scanning from left to right, our force decays by 1 every iteration,
    and resets to N if we meet an 'R', so that force[i] is higher
    (than force[j]) if and only if dominoes[i] is closer (looking leftward) to 'R' (than dominoes[j]).
  Similarly, scanning from right to left,
 we can find the force going rightward (closeness to 'L').
     */
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();

        int[] lefts = new int[n];
        int[] rights = new int[n];
        int force = 0;
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == 'R') {
                force = n;
            } else if (dominoes.charAt(i) == 'L') {
                force = 0;
            } else
                force = Math.max(force - 1, 0);
            rights[i] = force;
        }

        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'R') {
                force = 0;
            } else if (dominoes.charAt(i) == 'L') {
                force = n;
            } else
                force = Math.max(force - 1, 0);
            lefts[i] = force;

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int cur = lefts[i] - rights[i];
            if (cur == 0)
                sb.append('.');
            else if (cur > 0)
                sb.append('L');
            else
                sb.append('R');
        }
        return sb.toString();
    }

    public String pushDominoesOfficial(String dominoes) {
        int N = dominoes.length();
        int[] indexes = new int[N + 2];
        char[] symbols = new char[N + 2];
        int len = 1;
        indexes[0] = -1;
        symbols[0] = 'L';

        for (int i = 0; i < N; ++i)
            if (dominoes.charAt(i) != '.') {
                indexes[len] = i;
                symbols[len++] = dominoes.charAt(i);
            }

        indexes[len] = N;
        symbols[len++] = 'R';

        char[] ans = dominoes.toCharArray();
        for (int index = 0; index < len - 1; ++index) {
            int i = indexes[index], j = indexes[index + 1];
            char x = symbols[index], y = symbols[index + 1];
            char write;
            if (x == y) {
                for (int k = i + 1; k < j; ++k)
                    ans[k] = x;
            } else if (x > y) { // RL
                for (int k = i + 1; k < j; ++k)
                    ans[k] = k - i == j - k ? '.' : k - i < j - k ? 'R' : 'L';
            }
        }

        return String.valueOf(ans);
    }

}
