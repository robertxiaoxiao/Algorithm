package StringPatternMatch;/*
 * @author:
 * @date:  2019/10/29/029
 * @description:
 */

public class BM implements StringMatcher {

    // to store the pattern string with the ASCII key and the last index
    private int[] hashCharIndex =new int[256] ;

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
        /*
           good suffix rule :
           我们拿下标从 0 到 i 的子串（i 可以是 0 到 m-2）与整个模式串，求公共后缀子串。
           如果公共后缀子串的长度是 k，那我们就记录 suffix[k]=j（j 表示公共后缀子串的起始下标）。如果 j 等于 0，
           也就是说，公共后缀子串也是模式串的前缀子串，我们就记录 prefix[k]=true。

         */
        int lenT = T.length();
        int lenP = P.length();
        int i = 0;
        boolean match = true;int temp;
        while (i < lenT - lenP + 1) {
            boolean flag = true;
            a:
            for (temp= lenP - 1; temp >= 0; temp--)
                if (T.charAt(i + temp) == P.charAt(temp))
                    continue;
                else {
                    flag = false;
                    int si = temp;
                    // TO OPTIMISE :  HASH TABLE
                    int xi = findCharFromEnd(P, T.charAt(i + temp));
                    i += si - xi;
                    // jump out from loop
                    break a;
                }

            if (temp<0)
                return i;
        }
        return -1;
    }

    private int findCharFromEnd(String p, char ch) {
        for (int j = p.length() - 1; j >= 0; j--)
            if (p.charAt(j) == ch)
                return j;
        return -1;
    }

    private void initBC(String P){

        for(int i=0;i<256;i++)
            hashCharIndex[i]=-1;

        for(int i=0;i<P.length();i++)
            hashCharIndex[P.charAt(i)] =i;
    }

    // m 模式串长度 （比较串）
    // good suffix len
    private void generateGS(char[] b ,int m,int[] suffix, boolean[] prefix){
        for(int i=0 ; i<m;i++) {
            suffix[i] = -1;
            prefix[i]=false;
        }

        for(int i=0;i<m-1;i++) //b[0,i]
        {
            int j=i;
            int k=0; //最长后缀长度
            while(j>=0&&b[j]==b[m-k-1])
            {
                --j;
                ++k;
                suffix[k]=j+1;
            }
            if(j==-1)
                prefix[k]=true;  //如果公共后缀子串也是模式串的前缀子串
        }
    }


    public static void main(String[] args) {
        BM bm=new BM();
        String T="abaaabaaabaaab" ;
        String P="aab" ;
        System.out.println(bm.match(T,P));
    }
}
