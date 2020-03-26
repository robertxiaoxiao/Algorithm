package leetcodeTest.Week9;/*
 * @author: Robert
 * @date:  2020/1/5/005
 * @description:
 */

public class q1 {
    public String freqAlphabets(String s) {

        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; ) {
            int j = i + 2;
            if (j < n && s.charAt(j) == '#') {
                String temp = s.substring(i, j);
                int num = Integer.parseInt(temp);
                char ch = (char)(num - 10 + 'j');
                sb.append(ch);
                i = j + 1;
            } else {
                char c=(char)(s.charAt(i) - '1' + 'a');
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }

}
