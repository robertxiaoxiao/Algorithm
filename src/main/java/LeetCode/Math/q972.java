package LeetCode.Math;

import java.util.regex.*;

public class q972 {

    static class Fraction {
        long l;
        long r;

        public long getGCD(long a, long b) {
            return a % b == 0 ? b : getGCD(b, a % b);
        }

        public Fraction() {
            this.l = 0;
            this.r = 1;
        }

        public Fraction(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public void add(Fraction c) {
            l = l * c.r + r * c.l;
            r = r * c.r;
        }

        public void reduce() {
            long p = getGCD(l, r);
            l = l / p;
            r = r / p;
        }

        public boolean euqal(Fraction c) {
            return l == c.l && r == c.r;
        }
    }

    public boolean isRationalEqual(String S, String T) {
        Fraction sf = transfer(S);

        Fraction st = transfer(T);
        sf.reduce();
        st.reduce();
        return sf.l == st.l && sf.r == st.r;
    }

    public Fraction transfer(String s) {
        int pre = 0;
        String denum = null;
        String dot = null;
        String loop = null;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                denum = s.substring(pre, i);
                pre = i + 1;
            } else if (s.charAt(i) == '(') {
                dot = s.substring(pre, i);
                pre = i + 1;
            } else if (s.charAt(i) == ')') {
                loop = s.substring(pre, i);
            }
        }
        Fraction ans = new Fraction();
        if (denum == null) {
            ans.add(new Fraction(Integer.parseInt(s), 1));
            return ans;
        }

        if (denum.length() != 0)
            ans.add(new Fraction(Integer.parseInt(denum), 1));

        if (dot == null)
            dot = s.substring(pre);

        if (dot.length() != 0)
            ans.add(new Fraction(Integer.parseInt(dot), (int) Math.pow(10, dot.length())));
        int l = dot.length();
        if (loop == null)
            return ans;
        if (loop.length() != 0) {
            int r = loop.length();
            ans.add(new Fraction(Integer.parseInt(loop), (int) ((Math.pow(10, r) - 1) * (Math.pow(10, l)))));
        }
        System.out.println(denum + " " + dot + " " + loop);
        System.out.println(ans.l + "    " + ans.r);
        return ans;
    }
}
