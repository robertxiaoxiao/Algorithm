package StringPatternMatch;/*
 * @author:
 * @date:  2019/10/29/029
 * @description:
 */

public class BM implements StringMatcher {

    // to store the pattern string with the ASCII key and the last index
    private int[] hashCharIndex = new int[256];

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
        boolean match = true;
        int temp;
        int[] suffix = new int[P.length()];
        boolean[] prefix = new boolean[P.length()];
        initBC(P);
        generateGS(P.toCharArray(), P.length(), suffix, prefix);

        while (i < lenT - lenP + 1) {
            boolean flag = true;

            for (temp = lenP - 1; temp >= 0; temp--)
                if (T.charAt(i + temp) != P.charAt(temp))
                    break;

            // found the begin index
            if (temp < 0)
                return i;
            // bad charector
            int xi = temp-findCharFromEnd(P, T.charAt(i + temp));
            int xs=-1;
            // good character rule
            if (temp < lenP - 1)
                xs = moveByGS(P.toCharArray(), temp, suffix, prefix);
            // TO OPTIMISE :  HASH TABLE

            int finalMove = Math.max(xi, xs);
            //  T move  forward
            i +=finalMove;

        }
        return -1;
    }

    //  j : bad character
    private int moveByGS(char[] b, int j, int[] suffix, boolean[] prefix) {
        int xs = -1;
        int m = b.length;
        int k = m - j - 1;
        //  好后缀
        if (suffix[k] != -1) {
            return j + 1 - suffix[k];
        }
        // [j+2,m-1] 为好后缀的后缀子串
        // [j+1,m-1]经过之前的好后缀比较已经失效了,故只在好后缀的后缀子串中找
        for (int r = j + 2; r <= m - 1; r++)
            if (prefix[m - r] == true)
                return r;
        //Not  found
        return m;
//        else  if(prefix[k]==true)
//        {
//            for(int m=j+1;m<b.length;m++) {
//                int t=0 ;
//                while (m<b.length&&b[m]==b[t])
//                {
//                            m++ ;
//                            t++;
//                }
//                if(m==b.length)
//                {
//                    return b.length-t ;
//                }
//            }
//        }
    }
    //   OPTIMIZE  : Hash
    private int findCharFromEnd(String p, char ch) {
        for (int j = p.length() - 1; j >= 0; j--)
            if (p.charAt(j) == ch)
                return j;
        return -1;
    }

    private void initBC(String P) {
        for (int i = 0; i < 256; i++)
            hashCharIndex[i] = -1;

        //  iterate from head to tail  and the  latter will be recorded at the same value ;
        for (int i = 0; i < P.length(); i++)
            hashCharIndex[P.charAt(i)] = i;
    }


    /*
     calculate  the suffix array of pattern
      suffix[x] =si ;
      x : the len of suffix
      si : the last begin index of matched string segment
     */
    private static void generateSuffix(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 1; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < m - 1; i++) {
            // the len index
            int j = i;
            int k = 0;
            //compare  [0,m-1]  with  [0,i]  的公共子后缀
            //  i belongs to [0,m-2]
            // [j+1,i]   [m-1-k,m-1]
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                --j;
                ++k;
                suffix[k] = j + 1;
            }
            if (j == -1)
                prefix[k] = true;
        }
//        for(int i=1;i<m;i++)
//        {
//            // [m-i....m-1]
//            int j=0;
//            int mlen=0;
//            int k;
//            while(j<m-i)
//            {
//                //sufix begin index
//                 k=m-i;
//
//                while(j<m-i&&k<m&&b[j]==b[k]) {
//                    j++;
//                    k++;
//                    mlen++;
//                }
//
//                if(mlen==i) {
//                    suffix[i] = j - i;
//                    if(j-i==0)
//                        prefix[i]=true;
//
//                    mlen=0;
//                }
//                // step forward
//                j++ ;
//            }
//
//        }
    }

    public static void main(String[] args) {
        String s = "cabcab";
        String P = "bca" ;
        int m = s.length();
        boolean[] prefix = new boolean[m];
        BM bm = new BM();
        System.out.println(bm.match(s, P));
        int[] suffix = new int[m];

        // generateSuffix(s.toCharArray(),m,suffix,prefix) ;
        //   for(int i=0;i<m;i++)
        //  System.out.println(suffix[i]+"    "+prefix[i]);
    }

    // m 模式串长度 （比较串）
    // good suffix len
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < m - 1; i++) //b[0,i]
        {
            int j = i;
            int k = 0; //最长后缀长度

            while (j >= 0 && b[j] == b[m - k - 1]) {
                --j;
                ++k;
                suffix[k] = j + 1;
            }
            if (j == -1)
                prefix[k] = true;  //如果公共后缀子串也是模式串的前缀子串
        }
    }


}
