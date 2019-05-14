package StringPatternMatch;


/**
 *
 * @back
 *
 */
public class KMP  implements StringMatcher {
    @Override
    public int match(String T, String P) {

        int n=T.length();
        int m=P.length();
        //计算模式P的前缀函数
        int[] next=compute_prefix(P);
        // num of chracter matched
        int q=0;
        for (int i=0;i<n;i++)
        {
            //当期已经匹配了q个字符 第q+1个字符和当前i比较不同时
            //  p[q+1]==t[i]   下一个可能的起点，一直到q=0为止，相对于已经匹配的全部没用
            while (q>0&&P.charAt(q)!=T.charAt(i)) {
                //递归查找到匹配的字符为止
                q=next[q-1];
            }

            //当前匹配字符 移动T--- i->i+1  移动后与当前的i匹配上了
            if (P.charAt(q)==T.charAt(i))
                q=q+1;

            //出口，匹配完成
            if(q==m) {
                //寻找下一个匹配串
               //q = pi[q];
                System.out.println("Pattern occurs with shift  " + q);
                return i - q+1;
            }

        }

        return -1;
    }


    /**
     *
     * @param p   模式串p
     * @return  最长前缀后缀长度数组
     * 两种写法   pi[0]=-1 第一个值默认pi[i]=pi[0]+1=0
     */
    public static int[] compute_prefix(String p){
        int m=p.length();
        int[] pi=new int[m];

        //要比较字符的前一个字符
        int k=0;
        pi[0]=0;
        /*
          P[1,....,q]   T[s+1,,,,s+q]相等
          求下一个可能的有效位移：  P[1,,,,.q]   T[s'+1,......,s'+q]
          example:      i=  1  2  3  4  5  6  7  8  9  10
                       p[q] a  b  a  b  a  b  a  b  c  a
                      pi[q] 0  0  1  2  3  4  5  6  0  1
          pi 前缀函数  P的最长前缀也是p[1,,,k]的一个真后缀的长度l   pi[k]=l
          在位移s有q个字符成功匹配，则下一个可能有效的位移为s'=s+(q-pi[q]) 即平行移动T串
         */
        for (int q=1;q<m;q++)
        {
            //第 k+1项！=q
            while(k>0&&p.charAt(k)!=p.charAt(q))
             {
                k=pi[k-1];
                //当前字符与查找的字符不匹配
            }

            //找到了有效位移  入口从此  p[k+1]和p[q+1]比   已知k ,q之前都相等
            if(p.charAt(k)==p.charAt(q))
                k=k+1;

            pi[q]=k;

        }

        return pi;

    }


}
