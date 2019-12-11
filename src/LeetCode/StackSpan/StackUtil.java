package LeetCode.StackSpan;/*
 * @author: Robert
 * @date:  2019/12/11/011
 * @description:
 */

import java.io.PrintWriter;
import java.util.Stack;

public class StackUtil {

    // using the stack to get the previous less key/index
    // monotone(increase/decrease)  stack
    public static int[] PLN(int[] a) {

        int n = a.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && a[stack.peek()] > a[i])  //i > stack.peek(), A[i] < A[stack.peek()], strict less, >= means non-strict less because this is inside the loop
                stack.pop();
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return res;
    }

    // using the stack to get the next less key/index
    // o(n^2)
    public static int[] NLN(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int temp = i + 1;
            while (temp < n && a[temp] >= a[i]) //>= means strict less, > means non-strict less because the assignment is outside the loop
                ++temp;
            res[i] = (temp == n) ? -1 : temp;
        }
        return res;
    }

    // using O(N) time stack to get the nextLessValue(Strict)
    public static int[] NLNusingStack(int[] a) {
        int n = a.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++)
            // if  merge the edge situation out of right range, we need to initiate the value as arr.length or zero (-1,n)
            // for the incoming uniformed computation
            res[i] = -1;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 2 3 7 9
            /*
               2 3 7 9
            */
            while (!stack.isEmpty() && a[stack.peek()] > a[i]) {
                int x = stack.peek();
                // found the first x such that a[x]>a[i]
                stack.pop();
                res[x] = i;
            }
            stack.push(i);
        }
        return res;
    }

    public static void print(int[] a) {
        for (int i : a)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        //  xor : ^    1^0=1
        //  and : &    1&1=1
        //  or : |    1|0 =1
        System.out.println(2 & 3);
        System.out.println(2 ^ 3);
        System.out.println(2 | 3);
        // inverse :  ^1=0
        System.out.println(~2);
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(3));
//        int[] test = {2, 9, 7, 8, 3, 4, 6, 1};
//        print(test);
//        print(PLN(test));
//        print(NLN(test));
//        print(NLNusingStack(test));

    }
}
