package StringPatternMatch;


/*
@input  文本T   n   模式P   m
@return  if match  return beg idx  ;
朴素字符串匹配算法，采用一个循环来找到所有有效的位移,该循环对n-m+1中可能的每一个s值检查条件p[1...m]=T[s+1,....,s+m]
时间复杂度：
0(m*(n-m+1))
 */
public class NaiveString  implements  StringMatcher {


    @Override
    public int match(String T, String P) {

        int n=T.length();
        int m=P.length();

        for(int i=0;i<n-m+1;i++)
        {

            int idx=0;

            // brute match
            while(idx<m)
            {
             if(T.charAt(i+idx)==P.charAt(idx))
                idx++;
             else
                 break;
            }

            //find the match begin index
            if(idx==m)
                return i;
        }

        return -1;
    }

}
