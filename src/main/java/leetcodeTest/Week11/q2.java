package leetcodeTest.Week11;/*
 * @author: Robert
 * @date:  2020/1/19/019
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q2 {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int len = -1;
        for (String word : words)
            len = Math.max(word.length(), len);

        List<String> res = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            StringBuffer sb = new StringBuffer();
            for (String word : words)
                if (word.length() < i)
                    sb.append(' ');
                else
                    sb.append(word.charAt(i));
            String temp = sb.toString();
            int idx=temp.length()-1;
            while(temp.charAt(idx)==' ')
                idx--;
            res.add(temp.substring(0,idx+1));
        }
        return res;
    }
}
