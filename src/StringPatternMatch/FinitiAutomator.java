package StringPatternMatch;


import java.util.HashMap;

public class FinitiAutomator implements StringMatcher {

    //将字符映射成为整数 {charactor}---->{int}   字符串解空间大小
    public static final int num_of_character=3;
    /*
          利用有穷状态机进行匹配
        @parameter  String T   匹配文本
        @parameter  int[][]    状态转换函数   TF[curState][char]=nextState
            */
    /*
    @parameter  T text P pattern
    @return  begin idx
     */
    public int match(String T, String P){

        int n=T.length();
        int m=P.length();
        int[][] TF=new int[m+1][num_of_character];

        FinitiAutomator.computeTF(P.toCharArray(),m,TF);

        int i,state=0;
        for( i=0;i<n;i++)
        {
            state=TF[state][T.charAt(i)-'a'];
            if(state==m)
                return i-m+1;
        }

        return -1;
    }

    /*
     @Parameter  pts 模式
     @Parameter  M  pts长度
     @parameter  k  当前状态
     @parameter  x  给定字符

     状态对应的 是  迄今所读的后缀模式中的最长前缀
     */
    public static int getNextState(char[] pts,int M,int k ,int x)
    {
      //  pts[1,....K-1]x  和pts前面都是一样的，则当x==pts[k]时，可直接返回当前的后缀模式的最长前缀
        if(k<M&&x==pts[k])
            return k+1;
        //进入新状态  即后缀模式的最长前缀+1
        // 不一样时，要求尽可能大的ns
        int ns ,i;

        //寻找尽可能大的ns ,k为当前的状态,即状态回复，类似于P进行了对应的有效平移
        /*
           一个例子    p=ababaca
           i -    1 2 3 4 5 6 7 8 9 10 11
           T[i]   a b a b a b a c a  b  a
          f(T[i]) 1 2 3 4 5 4 5 6 7  2  3
                      a b a b a
         p(cur)b=ababa+b=p(5)+b

         p=abab aca

         p(cur)b 的后缀同时也是p的前缀的最大值为： 4
          计算状态转换函数：
           x==pts[cur]  state=k+1
           x!=pts[cur]  往回找  找到最长后缀对应的最长前缀  p计算前缀的时候从0往后，pts计算后缀从cur-1往后
         */

        //下一个状态从k往回找
        // p(0,,,k-1) 已经相同，当前输入字符是x，即末尾是x ，则从模式串中也需要找到对应的位置
        // ns即从min(m,q+1)开始，一直到Pk>Pqa即Pk是已知子串的最长前缀后缀
        for(ns=k;ns>0;ns--) {
            //从可能的最长的后缀长度开始找起
            if (pts[ns - 1] == x) {
                //pat前缀和已知后缀作比较
               // pat [0,...,ns,.....k]
                //尝试一下当前最长前缀
                for (i = 0; i < ns - 1; i++)
                    //当前ns-1不满足后缀的最长前缀  继续往前找
                    // p  a b a b c
                    //    0       ns
                    //             k
                      //  a b a b a b c
                      //      a b a b c
                    //前缀[0,ns-1]      后缀(k-ns+1,. ..k)比较
                    if (pts[i] != pts[k - ns + 1 + i])
                        break;

                    //对应前缀和后缀相等 则返回对应长度
                if (i == ns - 1)
                    return ns;
            }
        }

        //当前输入字符构成的子串，之前没有出现过，所以为0，没有找到，状态定义为0
        return 0;

    }

    /*
        状态转化函数
        M 已知的状态总数 P[1,...m]中后缀模式下前缀的长度
     */
    public  static void computeTF(char[] pat,int M,int[][] tf){

        int state,x;
        //state 是patten的长度，后缀往回看同时前缀最长
        for(state=0;state<=M;state++)
            for (x=0;x<num_of_character;x++)
                //  注意匹配字符开始的数值
                tf[state][x]=FinitiAutomator.getNextState(pat,M,state,x+'a');
    }

}
