package StringPatternMatch;/*
 * @author:
 * @date:  2019/10/29/029
 * @description:
 */

public class BM implements StringMatcher {
    @Override
    public int match(String T, String P) {

        /* bad character rule  :
          string  T :       T1 T2 T3 T4 T5
          String  P :       P1  P2 P3
        IF(T3!=P3)
        {当发生不匹配的时候，我们把坏字符对应的模式串中的字符下标记作 si。
         如果坏字符在模式串中存在，我们把这个坏字符在模式串中的下标记作 xi。
         如果不存在，我们把 xi 记作 -1。那模式串往后移动的位数就等于 si-xi。
           IF(P.Contains(T3))
              T move  t3-t1 ;
            else
              T move  t3-t1+1;    if( index[t1]=-1;)
        }
        */

        int lenT = T.length();
        int lenP = P.length();
        int i = 0;
        boolean match = true;
        while (i < lenT - lenP + 1) {
            boolean flag = true;
            a:
            for (int temp = lenP - 1; temp >= 0; temp--)
                if (T.charAt(i + temp) == P.charAt(temp))
                    continue;
                else {
                    flag = false;
                    int si = temp;
                    int xi = findCharFromEnd(P, T.charAt(i + temp));
                    i += si - xi;
                    // jump out from loop
                    break a;
                }
            if (flag)
                return 1;
        }
        return 0;
    }

    private int findCharFromEnd(String p, char ch) {
        for (int j = p.length() - 1; j >= 0; j--)
            if (p.charAt(j) == ch)
                return j;
        return -1;
    }

    public static void main(String[] args) {
        BM bm=new BM();
        String T="aaabaaabaaabaaab" ;
        String P="aab" ;
        System.out.println(bm.match(T,P));
    }
}
