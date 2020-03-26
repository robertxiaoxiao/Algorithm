package BasicDStructure.LinkedList;

public class listProblems {

    /*
      1  链表中环的检测
      2. 合并两个有序的链表
      3 删除链表中倒数第n个节点
      4 求链表的中间节点
      leetcode :206，141，21，19，876
     */

    public class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {


        ListNode  temp1=l1;
        ListNode  temp2=l2;

        if(temp1==null)
            return temp2;

        if(temp2==null)
            return temp1;


        ListNode head=new ListNode(-1);

        ListNode   cur=head;

        while(temp1!=null&&temp2!=null)
        {

            if(temp1.val<temp2.val)
            {

                cur.next=temp1;
                cur=temp1;

                temp1=temp1.next;

            }
            else
            {
                //尾插法
                cur.next=temp2;
                cur=temp2;
                temp2=temp2.next;
            }

        }
        if(temp1==null)
            cur.next=temp2;

        if(temp2==null)
            cur.next=temp1;

        return head.next;

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode temp=head;
        ListNode cur=head;
        //考虑特殊情况

        if(head==null||(head.next==null&&n==1))
            return null;

        int count=0;
        while(count!=n&&temp!=null)
        {
            temp=temp.next;
            count++;
        }

        //只有k个节点
        if(temp==null&&count==n)
            return head.next;

        //没有k个值
        if(temp==null)
            return head;


        ListNode  pre=head;
        while( temp!=null)
        {   pre=cur;
            cur=cur.next;
            temp=temp.next;
        }

        pre.next=pre.next.next;

        return head;

    }

    /*
    返回中间的节点  如果存在两个中间节点则返回后一个节点
     */
    public ListNode middleNode(ListNode head) {
        //空节点和只有一个节点
        if(head==null||head.next==null)
                return head;

        ListNode faster=head;
        ListNode slower=head;
        while(faster!=null&&faster.next!=null)
        {
            faster=faster.next.next;
            slower=slower.next;
        }

        return slower;
    }

}
