package BasicDStructure.LinkedList;

public class LconcurrentList {

    private static class  Node{

        public  Node  pre;
        public  Node next;
        public  int val;

        public Node(int val) {
            this.val = val;
            this.pre=null;
            this.next=null;
        }
    }

    public  Node head;
    public  Node tail;
    public LconcurrentList() {
        this.head = null;
        this.tail = null;
    }
    public LconcurrentList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public void insertToTail(Node node){

        // 链表为空时
        if(head==null&&tail==null)
        {
            node.pre=node;
            node.next=node;
            head=node;
            tail=node;
        }
        //非空
        else{
            tail.next=node;
            node.pre=tail;
            node.next=head;
            head.pre=node;
            tail=node;
        }
    }

    public  void delete(int val) {
        if (head == null || tail == null)
            return;

        //一个节点
        if (head.val == tail.val && head.val == val) {
            head = null;
            tail = null;
            return;
        }

        //头节点
        if (head.val == val) {
            Node temp = head.next;
            temp.pre = head.pre;
            head.pre.next = temp;
            head = temp;
            return;
        }

        //尾节点
        if(tail.val==val)
        {
            Node temp=tail.pre;
            temp.next=tail.next;
            tail.next.pre=temp;
            tail=temp;
            return;
        }

        //多个节点
        Node pre = head;
        Node cur = head;
        while (cur.next != head) {
            if (cur.val == val) {
                pre.next = cur.next;
                cur.next.pre = pre;
                return;
            }
            pre = cur;
            cur = cur.next;
        }
    }


    public void print(){

        if(head==null||tail==null)
            return;

        Node temp=head;
        while(temp.next!=head)
        {
            System.out.print(temp.val+"-->");
            temp=temp.next;
        }
        System.out.println(tail.val);
    }

    public  boolean isEmpty(){

        if(head==null&&tail==null)
            return true;

        return false;


    }

    public static void main(String[] args) {
        //TestConcu();
        yussefu(10,4);
    }

    private static void TestConcu() {
        LconcurrentList lconcurrentList=new LconcurrentList();

        for(int i =0;i<10;i++) {
            Node newnode=new Node(i);
            lconcurrentList.insertToTail(newnode);
        }

        lconcurrentList.print();

        lconcurrentList.delete(5);

        lconcurrentList.print();

        lconcurrentList.delete(0);

        lconcurrentList.print();

        lconcurrentList.delete(9);

        lconcurrentList.print();
    }

    public static void yussefu(int n,int m){

        LconcurrentList lconcurrentList=new LconcurrentList();
        for(int i =0;i<n;i++)
                lconcurrentList.insertToTail(new Node(i));

        int count=0;
        Node temp=lconcurrentList.head;
        while(!lconcurrentList.isEmpty())
        {
             temp=temp.next;
             count++;

             if(count==m)
             {
                 System.out.println("current deque:"+temp.val);
                 lconcurrentList.delete(temp.val);
                 count=0;

             }
        }
    }
}
