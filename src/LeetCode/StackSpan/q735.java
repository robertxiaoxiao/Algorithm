package LeetCode.StackSpan;/*
 * @author: Robert
 * @date:  2020/2/21/021
 * @description:
 */

import java.util.Stack;

public class q735 {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int cur : asteroids) {
            if (cur > 0)
                stack.push(cur);
            else {
                if (stack.isEmpty())
                    stack.push(cur);
                else {
                    int pre = stack.peek();
                    if (pre < 0 && cur < 0) {
                        stack.push(cur);
                    } else if (pre > 0 && cur > 0) {
                        stack.push(cur);
                    } else if (pre < 0 && cur > 0) {
                        stack.push(cur);
                    } else {
                        while (!stack.isEmpty()) {
                            int t = stack.peek();
                            if (t + cur > 0) {
                                break;
                            } else if (t + cur == 0) {
                                stack.pop();
                                break;
                            } else {
                                if (t < 0) {
                                    stack.push(cur);
                                    break;
                                } else {
                                    stack.pop();
                                    if (stack.isEmpty()) {
                                        stack.push(cur);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int n = stack.size();
        int[] ans = new int[n];
        int j = n - 1;
        while (!stack.isEmpty()) {
            ans[j--] = stack.pop();
        }
        return ans;
    }


}
