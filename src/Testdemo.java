
public class Testdemo {

    static   int[] judges1= new int[52] ;
    static   int[]  judges2=new int[52] ;

    public static void main(String[] args) {
            int N =2;
            String[] words={"abcd" ,"cbad"} ;
            System.out.println(UniquePassword(N,words));
    }

    public static int   UniquePassword(int N , String[] words) {
            int cnt =0;
            int[] marks=new int[N] ;

            for(int i= 0;i<N;i++) {
                marks[i]+=1;
                for (int j = i + 1; j < N; j++) {
                    // sum of chars not the same
                    if (!sameTotal(words[i], words[j]))
                        continue;
                    //strings at different length
                    if (words[i].length() != words[j].length())
                        continue;
                    // may disorder but same
                    if (assemble(words[i].toCharArray(), words[j].toCharArray())) {
                        marks[j] += 1;
                    }
                }
            }

            for(int i=0;i <N ;i++)
                if(marks[i]==1)
                    cnt++ ;

            return cnt;
    }

    public static boolean sameTotal(String s1 ,String s2 ){

        int cnt=0 ;
        for(int i =0;i<s1.length();i++)
            cnt +=s1.charAt(i)-'a';
        for(int j =0;j<s2.length();j++)
            cnt -=s1.charAt(j)-'a';
        if(cnt==0)
            return true ;
        else
            return false;
    }
    public  static boolean assemble(char[] s1,char[] s2 ) {

        for (int i = 0; i < s1.length; i++) {
            judges1[i] = 0;
            judges2[i] = 0;
        }

        for (int i = 0; i < s1.length; i++) {
            // even
            if (i % 2 == 0) {
                judges1[s1[i] - 'a'] += 1;
                judges2[s2[i] - 'a'] += 1;
            } else {
                judges1[s1[i] - 'a' + 26] += 1;
                judges2[s2[i] - 'a' + 26] += 1;
            }
        }
        for (int i = 0; i < s1.length; i++) {
            if (judges1[i] - judges2[i] != 0)
                return false;
        }
        return true;
    }

}
