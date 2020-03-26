package BasicDStructure.Stack;
/*
 * @author: robert
 * @date:  2019/6/4/004
 * @description:
 *
 * 栈的几个经典算法实现
 */

import java.util.ArrayList;
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

    /*
      算法四： 实现一个基本的计算器
     */

    public int  calculate(String s ){

        int result=do_calculate(parse(s));


        return result;
    }

    private  static String plus="+";
    private  static String minus="-";
    private  static String left="(";
    private  static String right=")";

    //可能出现的字符 + - ( ) [0-9]
    private  static ArrayList<String> parse(String str) {
        int i=0;
        String s=pre_process(str);
        char[]  chars=s.toCharArray();
        int start=0;
        //上一个数字
        ArrayList<String>  list=new ArrayList<>();
        //遍历
        while(chars[i]!='e')
        {
            //判断字符类型
            int result=judge(chars[i]);

            if(result==0)
            {
                //数字起点
                if(i==0||judge(chars[i-1])!=0) {
                    start = i;
                    System.out.println("num starts at " +start);
                }
                i++;
                continue;
            }
            else {
                //把之前的数字截取
                // 注意范围
                if(i-1>=0&&judge(chars[i-1])==0) {
                  //  stack.push(s.substring(start, i));
                    list.add(s.substring(start, i));
                }
                switch (result)
                {
                    case 1 :
                        //stack.push(plus);
                        list.add(plus);
                        break;

                    case 2:
                        //stack.push(minus);
                        list.add(minus);
                        break;

                    case 3:
                        //stack.push(left);
                        list.add(left);
                        break;

                    case 4:
                        //stack.push(right);
                        list.add(right);
                        break;

                        default:
                           return null;
                }
                i++;
            }
        }
        //最后一位非括号数字
        //一定要考虑到首尾情况（ num开头和 ) num结尾
        if(judge(chars[i-1])!=4&&chars[i]=='e')
            //stack.push(s.substring(start));
            list.add(s.substring(start,i));
        //print(stack);
        return  list;
    }
    /*
        0:digit ;
        1: +
        2：-
        3：（
        4：）
        5：others
     */
    private static int judge(char c) {

        if(c-'0'>=0&&c-'0'<=9)
            return 0;
        else if(c=='+')
            return 1 ;
        else if(c=='-')
            return 2;
        else if(c=='(')
            return 3 ;
        else if (c==')')
            return 4;
        else
            return 5;
    }

    //去掉括号
    private static String pre_process(String s) {
        char[] chars=new char[s.length()+1];

        int j=0;
        for(int i =0;i<s.length();i++)
        {
            if(s.charAt(i)==' ')
                continue;
            else
                chars[j++]=s.charAt(i);
        }
        //结束标志位
        chars[j]='e';

        return new String(chars);
    }

    private static int do_calculate( ArrayList<String>  list) {
        Stack<Integer>  stack=new Stack<>();
        //上一个符号

        int sign=1;
        //当前数
        int num=0;
        int sum=0;
        for(String str :list) {
            System.out.print(str + "    ");
            if (isNum(str)) {
                num = Integer.parseInt(str);
                sum += sign * num;
            } else {
                if (str.equalsIgnoreCase(plus))
                    sign = 1;
                else if (str.equalsIgnoreCase(minus))
                    sign = -1;

                    //碰到括号 存储上一个符号
                else if (str.equalsIgnoreCase(left)) {
                    stack.push(sum);
                    stack.push(sign);
                    sum=0;
                    sign=1;

                } else if (str.equalsIgnoreCase(right))

                    if (!stack.isEmpty()) {
                        sign = stack.pop();
                        sum = stack.pop()+sign*sum;
                    }
            }

        }
        System.out.println(sum);
        return sum;
    }

    public  static boolean isNum(String s){

        if(s.equalsIgnoreCase("+")||s.equalsIgnoreCase("-")||s.equalsIgnoreCase("(")||s.equalsIgnoreCase(")"))
            return false;
        return true;

    }


    //算法五：基本栈操作
    //Each round's operation is permanent and could have an impact on the round before and the round after.
    public static int calPoints(String[] ops) {

        Stack<Integer>   res=new Stack<>();
        int sum=0;
        for(String str:ops)
        {
            if(str.equalsIgnoreCase("C"))
                sum-=res.pop();

            else if (str.equalsIgnoreCase("D")) {
                int last=res.peek();
                sum += last * 2;
                res.push(last*2);
            }
            else if(str.equalsIgnoreCase("+"))
            {
                int last1=res.pop();
                int last2=res.pop();
               // 注意顺序
                res.push(last2);
                res.push(last1);
                res.push(last1+last2);
                sum+=last1+last2;
            }
            else {
                int tmp=Integer.parseInt(str);
                res.push(tmp);
                sum+=tmp;
            }

        }

        return sum;
    }
//496
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
          int[] result=new int[nums1.length];

          for(int i=0;i<nums1.length;i++)
              result[i]=findGreater(nums1[i],nums2);

          return  result;

    }

    private static int findGreater(int j, int[] nums2) {

        int lastGreater=-1;
        for(int i=0;i<nums2.length;i++) {

                if (lastGreater == -1 && nums2[i] > j) {
                    lastGreater = i;
                    continue;
                }

          if(nums2[i] >j&&nums2[i]<nums2[lastGreater])
                   lastGreater=i;

        }
        return lastGreater;

    }


    public static void main(String[] args) {
        StackAlo s=new StackAlo();
        String a= "1+1";
        System.out.println(a);
       do_calculate(parse(a));
    }



}
