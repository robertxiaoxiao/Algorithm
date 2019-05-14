package StringPatternMatch;

public class MatchTest {


    public static void main(String[] args){

//        String t="abcdefgabcdefmabc";
//        String p="abcdefm";
//        String t="121314151615169910";
//        String p="15161";

        String t="abababacabca";
        String p="ababaca";

        StringMatcher finitiAutomator=new FinitiAutomator();
        StringMatcher kmp=new KMP();


        NaiveString naiveString=new NaiveString();
        RabinKarp rabinKarp=new RabinKarp();

        int[] pi=KMP.compute_prefix(t);
        for(int i :pi)
            System.out.print(i+"  ");

        int res=kmp.match(t,p);
//    //    int res=finitiAutomator.match(t,p);
////        int res=naiveString.match(t,p);
//        //int res=rabinKarp.match(t,p);
//
        String result=res==-1?"match failed":String.format("match success ,the index begins at %s",res);

        System.out.println(result);


    }




}
