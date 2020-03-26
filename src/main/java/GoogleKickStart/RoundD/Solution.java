package GoogleKickStart.RoundD;

import java.util.Scanner;
import java.util.TreeSet;

public class Solution {

    static class Num implements Comparable<Num> {
        int val;
        public boolean delete;

        public Num(int val) {
            this.val = val;
        }

        public Num(int val, boolean delete) {
            this.val = val;
            this.delete = delete;
        }

        @Override
        public int compareTo(Num o) {
            if (val > o.val)
                return 1;
            if (val == o.val && (delete || o.delete))
                return 0;
            return -1;
        }
    }

    public static void main(String[] args) {
        Num n1 = new Num(1);
        Num n2 = new Num(1);
        Num n3 = new Num(2);
        TreeSet<Num> t = new TreeSet<>();

        t.add(n1);
        t.add(n2);
        t.add(n3);
//        System.out.println(t.size());
//        for (Num n : t.subSet(new Num(1), new Num(3)))
//            System.out.println(n.val);

        System.out.println(t.floor(new Num(2, true)).val);
        System.out.println(t.ceiling(new Num(1, true)).val);

        t.remove(new Num(1, true));
        System.out.println(t.size());
        t.remove(new Num(1, true));
        System.out.println(t.size());

//        for (Integer i:t)
//            System.out.println(i.intValue());
//
//        System.out.println(t.ceiling(3));
//        System.out.println(t.floor(0));

        // System.out.println(s1 == s2);
    }

    public int solve() {
        return -1;
    }

    public int MaxSuger(int[] arr) {
        // n o p
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int O = scanner.nextInt();
        int D = scanner.nextInt();
        scanner.nextLine();
        int x1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int m = scanner.nextInt();
        int l = scanner.nextInt();
        scanner.nextLine();
        int[] sweets = getinfo(x1, x2, a, b, c, m, l, n);
        return -1;
    }


    public int solve(int n, int O, int D, int[] sweets) {
        int[] psum = new int[n + 1];
        psum[0] = 0;
        int ans = 0;

        for (int i = 1; i <= n; i++)
            psum[i] = psum[i - 1] + sweets[i - 1];

        for (int i = 1; i <= n; i++) {

        }

        return ans;
    }

    public boolean check(int i, int j, int[] preodds, int[] sweetsum, int o, int d) {
        int odds = preodds[j] - preodds[i - 1];
        int sums = sweetsum[j] - sweetsum[i - 1];
        return odds <= o && sums <= d;
    }

    public int[] getinfo(int x1, int x2, int a, int b, int c, int m, int l, int n) {
        int[] infos = new int[n + 1];
        infos[1] = x1;
        infos[2] = x2;
        for (int i = 3; i <= n; i++) {
            infos[i] = (a * infos[i - 1] + b * infos[i - 2] + c) % m;
        }
        for (int i = 1; i <= n; i++) {
            infos[i] += l;
        }
        return infos;
    }


}
