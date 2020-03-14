package LeetCode.SlidingWindows;

import java.util.Stack;

public class q155 {

    class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minstack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
            minstack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minstack.isEmpty())
                minstack.push(x);
            else
                minstack.push(Math.min(minstack.peek(), x));
        }

        public void pop() {
            stack.pop();
            minstack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minstack.peek();
        }
    }

}
