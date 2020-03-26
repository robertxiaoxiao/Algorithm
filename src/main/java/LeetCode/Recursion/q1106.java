package LeetCode.Recursion;/*
 * @author: Robert
 * @date:  2020/1/1/001
 * @description:
 */

public class q1106 {

    String exp = null;
    int idx = 0;

    public boolean parseBoolExpr(String expression) {
        exp = expression;
        idx = 0;
        return helper();
    }

    public boolean helper() {
        char ch = exp.charAt(idx++);
        if (ch == 't')
            return true;
        if (ch == 'f')
            return false;
        if (ch == '!') {
            // consume  '('
            idx++;
            boolean ans = !helper();
            // consume ')'
            idx++;
            return ans;
        }
        boolean isand = ch == '&';
        boolean ans = isand;
        //consume '('
        idx++;
        while (true) {
            if (isand)
                ans &= helper();
            else
                ans |= helper();
            if (exp.charAt(idx++) == ')')
                break;
        }
        return ans;
    }
}
