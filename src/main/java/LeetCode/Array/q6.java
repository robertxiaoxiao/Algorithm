package LeetCode.Array;

import java.util.Arrays;

public class q6 {

    static int[][] dirs = {{1, 0}, {-1, 1}};

    public String convert(String s, int numRows) {
        int n = s.length();
        if (n == 0)
            return s;

        if (numRows == 1)
            return s;

        int gsize = numRows * 2 - 2;
        int k = n / gsize;
        int rest = n % gsize;
        int m = k * (numRows - 1);
        if (rest <= numRows)
            m++;
        else {
            m += 1 + rest - numRows;
        }

        char[][] chars = new char[numRows][m];
        for (int tn = 0; tn < numRows; tn++)
            Arrays.fill(chars[tn], '$');
        int index = 0;
        int i = 0, j = 0, d = 1;
        StringBuffer sb = new StringBuffer();
        while (index < n) {

            chars[i][j] = s.charAt(index++);
            if (i == numRows - 1 || i == 0) {
                d = (d + 1) % 2;
            }
            int ni = i + dirs[d][0];
            int nj = j + dirs[d][1];
            i = ni;
            j = nj;
        }


//        for (int ti = 0; ti < numRows; ti++)
//            for (int tj = 0; tj < m; tj++) {
//                if (chars[ti][tj] != '$') {
//                    sb.append(chars[ti][tj]);
//                }
//            }

        return sb.toString();
    }

    public String convertUsing(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder("");   //init every sb element **important step!!!!
        }
        int incre = 1;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            sb[index].append(s.charAt(i));
            if (index == 0) {
                incre = 1;
            }
            if (index == numRows - 1) {
                incre = -1;
            }
            index += incre;
        }
        String re = "";
        for (int i = 0; i < sb.length; i++) {
            re += sb[i];
        }
        return re.toString();
    }
}
