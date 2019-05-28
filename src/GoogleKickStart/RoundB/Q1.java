package GoogleKickStart.RoundB;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
Round B 2019 - Kick Start 2019
Building Palindromes
 */
public class Q1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }

    /*
     */
    private static int solve(Scanner scanner) {
        int N = scanner.nextInt();
        int Q = scanner.nextInt();
        String have = scanner.next();
        List<HashMap<Integer,Integer>> cache=new ArrayList<>();

        // 每一个字符串
        for(int i=0;i<N;i++)
        {
            HashMap<Integer,Integer> temp=new HashMap<>();
            int c=have.charAt(i)-'A';
            if(i==0)
               temp.put(c,1);
            else {
                temp.putAll(cache.get(i-1));
                temp.put(c,cache.get(i-1).getOrDefault(c, 0) + 1);

            }
            cache.add(temp);
        }

        // ABAACCA
        //3,6  AACC
        //  AB  ABAACC
        // A 1
        // a 1 b 1
        //4 4  A

        int sum=0;
        for (int i = 1; i <= Q; i++) {
            int Li = scanner.nextInt();
            int Ri = scanner.nextInt();
            HashMap<Integer,Integer>  left=null;
            if(Li!=1)
                left=cache.get(Li-2);
            else
                left=new HashMap<>();

            HashMap<Integer,Integer>  right=cache.get(Ri-1);

            int count=0;

            for (int key:right.keySet())
            {
                  System.out.println(key);
                  int cur=right.get(key)-left.getOrDefault(key,0);

                  if(cur%2==1)
                      count++;

                  if(count>1)
                      break;
            }
            if(count<=1)
                sum++;
        }
        return sum;
    }
}
