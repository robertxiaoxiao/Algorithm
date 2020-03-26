package LeetCode.DP;

public class q517 {

    public int findMinMoves(int[] machines) {
        int total = 0, target = 0, result = 0, n = machines.length;
        for (int d : machines) total += d;
        if (total == 0) return 0;
        if (total % n != 0) return -1;
        target = total / n;

        int[] move = new int[n];
        for (int i = 0; i < n - 1; i++) {
            if (machines[i] > target) {
                // to left
                move[i] += machines[i] - target;
                machines[i + 1] += machines[i] - target;
                machines[i] = target;
                result = Math.max(result, move[i]);
            } else {
                // i+1 need to move left 
                move[i + 1] = target - machines[i];
                machines[i + 1] -= target - machines[i];
                machines[i] = target;
                result = Math.max(result, move[i + 1]);
            }
        }
        return result;
    }

}
