package StringPatternMatch;

/*
通常是十进制数字的匹配  T 131415161718    P 15167
数论的观点：两个数对第三个数的模等价
则ts=[s+1,...s+m]表示的十进制数  p=[1,..m]表示的十进制数
ts==p当且仅当p[1,,,m]=T[s+1,...s+m]
根据霍纳法则  p=P[m]+10P[m-1]+10(P[m-2]+....+10P[1])   T=HUONA(T[1,..m])
O（n-m）的时间内求出   t1,t2,t(n-m)
//去掉高位，补最后一位，等效于往右平移一位
t(s+1)=10(t-exp(10,m-1)T[s+1])+T[s+m+1]
***求解p ts位数较大，不利用单次操作，则对一个合适的模数q进行计算，rk算法是选择q=13来进行计算
如果采用d进制的数，则选取的q应该为dq为一个计算机字长内，可以直接使用单精度运算，通解为
t(s+1)=(d(t-hT[s+1])+T[s+m+1])mod q
其中h=exp(d,m-1) mod q 是一个m数位文本窗口上高位数位上数字1的值
快速的启发式测试方法：  ts= p mod q
java 中取模不等价%(取余符号，负数时无效)
*/
public class RabinKarp implements     StringMatcher{
    @Override
    public int match(String T, String P) {

         int n=T.length();
         int m=P.length();
         int d=10;
         int q=13;
         int p=0;
         int t0=0;
         int h=getMod((int)Math.pow(d,m-1),q);
         // 根据霍纳法则求解 t0,p
         for (int i=0;i<m;i++)
         {
            p=getMod(d*p+(P.charAt(i)-'0'),q);
            t0=getMod(d*t0+(T.charAt(i)-'0'),q);
         }
         int[] ts=new int[n-m];
         ts[0]=t0;
         for(int s=0;s<n-m;s++)
         {
             //启发式测试
             if(p==ts[s])
             {
                 if(Trymacth(T,P,s))
                      return s;
             }
             //去掉高位数字   往后补一位
             if(s<n-m-1)
                 ts[s + 1] = getMod(d * (ts[s] - (T.charAt(s) - '0') * h) + T.charAt(s + m) - '0', q);

         }

        return -1;
    }

    private boolean Trymacth(String t, String p, int s) {
        int n=p.length();
        int i=0;
        while(i<n){
            if(t.charAt(s+i)==p.charAt(i))
               i++;
            else
                break;
        }

        if(i==n)
            return true;

        return false;
    }

    /*
      数学上的取模，当为负数时，取模结果为整数
     */
    private  static int getMod(int a ,int b){

           if(a>=0)
            return a%b ;

            int temp=a;
            while(temp<0)
            {
                temp+=b;
            }

            if(temp>=0)
                return temp%b;

            return -1;

    }


}
