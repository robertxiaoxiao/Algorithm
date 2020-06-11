package leetcodeTest;

import java.util.*;

public class Week28 {

    public String arrangeWords(String text) {
        String[] words = text.split(" ");
        words[0] = words[0].toLowerCase();
        Arrays.sort(words, new Comparator<String>() {
            public int compare(String s, String t1) {
                return s.length() - t1.length();
            }
        });


        StringBuffer sb = new StringBuffer();
        for (String word : words)
            sb.append(word + " ");
        sb.deleteCharAt(sb.length() - 1);
        sb.setCharAt(0, (char) (sb.charAt(0) - 32));
        return sb.toString();
    }


    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();
        List<Integer> ans = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n && flag; j++) {
                if (i == j)
                    continue;
                if (subset(favoriteCompanies.get(j), favoriteCompanies.get(i)))
                    flag = false;
            }
            if (flag)
                ans.add(i);
        }
        return ans;
    }

    public static boolean subset(List<String> l1, List<String> l2) {
        if (l1.size() < l2.size())
            return false;

        HashSet<String> hset = new HashSet<String>();
        for (String s : l1)
            hset.add(s);

        for (String s : l2) {
            if (!hset.contains(s))
                return false;
        }
        return true;
    }


    public int numPoints(int[][] points, int r) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int a = points[i][0];
                int b = points[i][1];
                int c = points[j][0];
                int d = points[j][1];

                // 精度问题
                double x1 = (a + c) / 2.0 + (b - d) * Math.sqrt(4 * r * r - (a - c) * (a - c) - (b - d) * (b - d)) / (2 * Math.sqrt((a - c) * (a - c) + (b - d) * (b - d)));
                double y1 = (b + d) / 2.0 + (-a + c) * Math.sqrt(4 * r * r - (a - c) * (a - c) - (b - d) * (b - d)) / (2 * Math.sqrt((a - c) * (a - c) + (b - d) * (b - d)));

                double x2 = (a + c) / 2.0 + (-b + d) * Math.sqrt(4 * r * r - (a - c) * (a - c) - (b - d) * (b - d)) / (2 * Math.sqrt((a - c) * (a - c) + (b - d) * (b - d)));
                double y2 = (b + d) / 2.0 + (a - c) * Math.sqrt(4 * r * r - (a - c) * (a - c) - (b - d) * (b - d)) / (2 * Math.sqrt((a - c) * (a - c) + (b - d) * (b - d)));

            }
        return 0;
    }

    public boolean check(int[][] points, int tn, int r, int midx, int midy) {
        int cnt = 0;
        for (int[] point : points)
            if ((point[0] - midx) * (point[0] - midx) + (point[1] - midy) * (point[1] - midy) <= r * r)
                cnt++;

        return cnt >= tn;
    }

}
