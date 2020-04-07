package LeetCode.Queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class q406 {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[0] == o1[0])
                    return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });

        List<int[]> ans = new LinkedList<>();
        for (int[] p : people) {
            if (ans.isEmpty())
                ans.add(p);
            else if (p[1] == 0)
                ans.add(0, p);
            else {
                int cursize = ans.size();
                if (ans.get(cursize - 1)[0] >= p[0] && p[1] == cursize)
                    ans.add(p);
                else {
                    int i = 0;
                    for (i = 0; i < ans.size(); i++) {
                        if (ans.get(i)[0] >= p[0] && i + 1 == p[1]) {
                            ans.add(i + 1, p);
                            break;
                        }
                    }
                }
            }
        }

        int n = people.length;
        int[][] fans = new int[n][2];
        int idx = 0;
        for (int[] p : ans) {
            fans[idx++] = p;
        }
        return fans;
    }

}
