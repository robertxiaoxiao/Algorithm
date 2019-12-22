package leetcodeTest.Week7;/*
 * @author: Robert
 * @date:  2019/12/22/022
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class q4 {

    public int maxCandiesUsinghashset(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

        int ans = 0;
        if (initialBoxes.length == 0)
            return 0;

        HashSet<Integer> ownedboxes = new HashSet<>();
        HashSet<Integer> ownedkeys = new HashSet<>();
        HashSet<Integer> opened = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int n = status.length;
        for (int i = 0; i < n; i++) {
            if (status[i] == 1)
                ownedkeys.add(i);
        }
        for (int ibox : initialBoxes) {
            ownedboxes.add(ibox);
            if (ownedkeys.contains(ibox)) {
                queue.add(ibox);
                opened.add(ibox);
            }
        }

        while (!queue.isEmpty()) {
            int curBox = queue.poll();
            ans += candies[curBox];

            for (int ckey : keys[curBox]) {
                ownedkeys.add(ckey);
                if (ownedboxes.contains(ckey) && !opened.contains(ckey)) {
                    queue.add(ckey);
                    opened.add(ckey);
                }
            }

            for (int cbox : containedBoxes[curBox]) {
                ownedboxes.add(cbox);
                if (ownedboxes.contains(cbox) && !opened.contains(cbox)) {
                    queue.add(cbox);
                    opened.add(cbox);
                }
            }
        }

        return ans;
    }


    public int maxCandiesUsingarr(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

        int ans = 0;
        if (initialBoxes.length == 0)
            return 0;

        boolean[] hasbox = new boolean[1050];
        boolean[] haskey = new boolean[1050];
        boolean[] opened = new boolean[1050];
        Queue<Integer> queue = new LinkedList<>();
        int n = status.length;
        for (int i = 0; i < n; i++) {
            if (status[i] == 1)
                haskey[i] = true;
        }
        for (int ibox : initialBoxes) {
            hasbox[ibox] = true;

            if (haskey[ibox]) {
                queue.add(ibox);
                opened[ibox] = true;
            }
        }

        while (!queue.isEmpty()) {
            int curBox = queue.poll();
            ans += candies[curBox];

            for (int ckey : keys[curBox]) {
                haskey[ckey] = true;
                if (!opened[ckey] && hasbox[ckey]) {
                    queue.add(ckey);
                    opened[ckey] = true;
                }
            }

            for (int cbox : containedBoxes[curBox]) {
                hasbox[cbox] = true;
                if (haskey[cbox] && !opened[cbox]) {
                    queue.add(cbox);
                    opened[cbox] = true;
                }
            }
        }

        return ans;
    }


    // open the all boxes until all closed box and corresponding key not found ;
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

        int ans = 0;
        if (initialBoxes.length == 0)
            return 0;

        HashSet<Integer> foundkeys = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int ibox : initialBoxes) {
            queue.add(ibox);
        }

        while (!queue.isEmpty()) {
            boolean allboxesclosed = true;
            int size = queue.size();
            while (size > 0) {
                size--;
                int curbox = queue.poll();
                for (int key : keys[curbox])
                    foundkeys.add(key);
                if (status[curbox] == 1 || foundkeys.contains(curbox)) {
                    ans += candies[curbox];
                    allboxesclosed = false;
                    for (int nbox : containedBoxes[curbox])
                        queue.add(nbox);
                } else
                    queue.add(curbox);
            }
            if (allboxesclosed)
                break;
        }
        return ans;
    }
}
