package BasicDStructure.Stack;


/*
 * @author: robert
 * @date:  2019/6/4/004
 * @description:
 *
 * 栈的几个经典算法实现
 */

import java.util.Calendar;
import java.util.Stack;

public class StackAlo {

    /*
     栈在算法中的运用，主要是与其他数据结构配合使用，或者两个栈共同使用，两个典型例子，
     （1）浏览器的回退和前进逻辑的实现 ，就是通过两个栈X,Y记录前进浏览页面和后退浏览页面
     （2）函数调用栈，当被调用函数执行完成，返回之后，将对应函数的栈帧返回。
     */

    //    20,155,232,844,224,682,496
    /*
       算法一：括号匹配中的应用
     */

    public boolean isValid(String s) {

        Stack<Character>  stack=new Stack<>();

        for(char c:s.toCharArray())
        {
            if(c=='{'||c=='['||c=='(')
                    stack.push(c);
            else
            {
                //考虑健壮性
                if(stack.isEmpty())
                   return false;

                char temp=stack.pop();

                if((c=='{'&&temp=='}')||(c=='['&&temp==']')||(c=='('&&temp==')'))
                    continue;
                else
                        return false;
            }
        }

        if(stack.isEmpty())
            return true;

        return false;
    }
    /*
     算法二： 通过两个栈来实现最小栈元素O(1)操作
      没有考虑程序健壮性，只是基本实现
      Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     */
    private  class MinStack{

        private  Stack<Integer>  stack;
        private  Stack<Integer>  minstack;
        public MinStack() {
          stack=new Stack<>();
          minstack=new Stack<>();
        }

        public void push(int x) {

            if(stack.isEmpty()&&minstack.isEmpty())
            {
                stack.push(x);
                minstack.push(x);
            }
            else
            {
                stack.push(x);
                int temp=minstack.peek();
                int res=temp<=x?temp:x;
                minstack.push(res);
            }
        }

        public void pop() {
            if(!stack.isEmpty())
            {
                stack.pop();
                minstack.pop();
            }
        }
        public int top() {

            return stack.peek();
        }

        public int getMin() {
            return minstack.peek();
        }
    }

    /*
     算法三： 用栈来实现队列
     Implement the following operations of a queue using stacks.
     */

    private class  MyQueue{


        Stack<Integer> s1 = new Stack();
        Stack<Integer> s2 = new Stack();

        public MyQueue() {
        }

        // Push element x to the back of queue.
        public void push(int x) {
            while (!s2.isEmpty())
                s1.push(s2.pop());

            s1.push(x);
        }

        // Removes the element from in front of queue.
        public int pop() {
            while (!s1.isEmpty())
                s2.push(s1.pop());

            return     s2.pop();
        }

        // Get the front element.
        public int peek() {
            while (!s1.isEmpty())
                s2.push(s1.pop());

            return s2.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }

    }

}
