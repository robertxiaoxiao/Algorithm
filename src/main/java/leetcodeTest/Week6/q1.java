package leetcodeTest.Week6;/*
 * @author: Robert
 * @date:  2019/12/15/015
 * @description:
 */

public class q1 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int getDecimalValue(ListNode head) {
        int ans=0;
        ListNode  temp =head ;
        while(temp!=null)
        {
            ans=(ans+temp.val)<<1;
            temp=temp.next;
        }
        return ans ;
    }
}
