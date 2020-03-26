package leetcodeTest.Week15;/*
 * @author: Robert
 * @date:  2020/2/16/016
 * @description:
 */

public class q2 {


    class ProductOfNumbers {
        class node {
            int val;
            node par;
            node next;

            public node(int val) {
                this.val = val;
            }
        }

        node head;
        node tail;

        public ProductOfNumbers() {
            head = new node(-1);
            tail = head;
        }

        public void add(int num) {
            node cur = new node(num);
            cur.par = tail;
            tail.next = cur;
            tail = tail.next;
        }

        public int getProduct(int k) {
            node f = tail;
            int t = 0;
            int cnt = 1;
            while (t < k) {
                cnt *= f.val;
                f = f.par;
                t++;
            }
            return cnt;
        }


    }
}
