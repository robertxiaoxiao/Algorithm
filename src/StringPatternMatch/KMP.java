package StringPatternMatch;


/**
 *
 * @back    KMP算法，它利用之前已经部分匹配这个有效信息，保持i 不回溯，通过修改j 的位置，让模式串尽量地移动到有效的位置。
 * 假设现在文本串S匹配到 i 位置，模式串P匹配到 j 位置
 * 如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++，继续匹配下一个字符；
 * 如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]。此举意味着失配时，模式串P相对于文本串S向右移动了j - next [j] 位。
 * 换言之，当匹配失败时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next 值（next 数组的求解会在下文的3.3.3节中详细阐述），即移动的实际位数为：j - next[j]，且此值大于等于1。
 *   next 数组各值的含义：代表当前字符之前的字符串中，有多大长度的相同前缀后缀。例如如果next [j] = k，代表j 之前的字符串中有最大长度为k 的相同前缀后缀
 *  在某个字符失配时，该字符对应的next 值会告诉你下一步匹配中，模式串应该跳到哪个位置（跳到next [j] 的位置）。如果next [j] 等于0或-1，则跳到模式串的开头字符，
 *  若next [j] = k 且 k > 0，代表下次匹配跳到j 之前的某个字符，而不是跳到开头，且具体跳过了k 个字符。
 */
public class KMP  implements StringMatcher {
    @Override
    /*
       基于最大前缀后缀表进行匹配
     */
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
            //  p[q+1]==t[i]   下一个可能的起点
            while (q>0&&P.charAt(q)!=T.charAt(i)) {
                //递归查找到匹配的字符为止
                q=next[q-1];
            }

            //当前匹配字符 P[q+1]==T[i+1]   移动后与当前的i匹配上了即已经匹配成功了q+1个字符
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
/*
如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++，继续匹配下一个字符；
如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]。此举意味着失配时，模式串P相对于文本串S向右移动了j - next [j] 位。
换言之，当匹配失败时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next 值，即移动的实际位数为：j - next[j]，且此值大于等于1。”
 */
    public  int matchUsingNext(String T, String P){

        int n=T.length();
        int m=P.length();
        //计算模式P的前缀函数
        int[] next=getNext(P);
        // num of chracter matched
       int i=0;
       int j=0;
       //int k=-1;
       while(i < n&&j < m)
       {
          //如果j = -1，或者当前字符匹配成功（即T[i] == P[j]），都令i++，j++
           if(j==-1||P.charAt(j)!=T.charAt(i))
           {
               //当j==-1时即从 p[0]=T[i+1]比较
               j++;
               i++;
               /*
                 优化部分
                */

           }

           //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
           //next[j]即为j所对应的next值     [0,,j]  [i-j,...i]还是相等的   T不动 相对于移动P了 j-next[j]段距离
           else
           {
               j=next[j];
           }

       }
       if(j==m)
           return i-j+1;

        return -1;

    }

    /**
     *
     * @param p   模式串p
     * @return  最长前缀后缀长度数组
     * 两种写法   pi[0]=-1 第一个值默认pi[i]=pi[0]+1=0   算法导论中的实现方法
     * 它默认下标从1开始，故实现的时候要注意下标
     *  π[0]=0
     *  k=0
     *  for q   2 to m
     *        while(q>0 p[k+1]!=p[q])
     *            k=next[k]
     *
     *       if(p[k+1]==p[q])
     *       	k=k+1
     *
     *       next[q]=k;
     *
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

/*
   根据next数组求解
 */

    public static int[]   getNext(String P){
         int m=P.length();
        int[] next=new int[m];
        next[0]=-1;
        //已匹配字符
        int k=-1;
        int j=0;
        while(j<m-1)
        {
            if(k==-1||P.charAt(j)== P.charAt(k))
            {
                k++;
                j++;
                next[j]=k;
//                //匹配之后 k j都往后走 ，如果此时还相等
//                 if(P.charAt(j)== P.charAt(k))
//                     next[j]=k;
//                 else
//                     //因为p[j]！=p[next[j]],所以当需要递归时k=next[k]=next[next[k]]  一直递归找到相等的k j
//                     next[j]=next[k];
            }
            else
                k=next[k];
        }

        return next;
    }


}
