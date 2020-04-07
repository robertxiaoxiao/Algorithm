package LeetCode.StackSpan;

import java.util.Stack;

public class q20 {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(')
                stack.add(ch);
            else {
                if (stack.isEmpty())
                    return false;

                    char ctop = stack.peek();
                    if (ch == '{' && ctop == '}')
                        stack.pop();
                    else if (ch == '[' && ctop == ']')
                        stack.pop();
                    else if (ch == '(' && ctop == ')')
                        stack.pop();
                    else
                        return false;

            }
        }
        return stack.isEmpty();
    }
}
