package BasicDStructure.LinkedList;


/*
  单向循环链表
 */
public class ILinkedList {

    public  static Node curhead=null;
    private static class Node {

        public  int val;
        public  Node next;
        public Node() {
            this.val=-1;
            this.next = null;
        }
        public Node(int val) {
            this.val = val;
            this.next = null;
        }
        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public  Node head;

    public ILinkedList() {
        this.head = new Node();
    }

    public  void insertToTail(int val){

        Node  newnode=new Node(val);

        Node  temp=head;
        while(temp.next!=null)
            temp=temp.next;
        temp.next=newnode;
    }

    public  void insertToTail(Node  node){
        Node  temp=head;
        while(temp.next!=null)
            temp=temp.next;
        temp.next=node;
    }

    public  void insertToHead(int val){

        Node newnode=new Node(val);
        Node temp=head.next;
        newnode.next=temp;
        head.next=newnode;
    }

    public  void insertToHead(Node node){

        Node temp=head.next;
        node.next=temp;
        head.next=node;
    }


    public  void delete(int val){
        Node  pre=head;
        Node  cur=head.next;
        while(cur!=null)
        {
            if(cur.val==val) {
                pre.next = cur.next;
                break;
            }
            pre=pre.next;
            cur=cur.next;
        }
    }

    public  void print() {
        Node temp = head.next;
        System.out.println("cur list:");
        while (temp != null)
        {       System.out.print(temp.val + "--->");
            temp = temp.next;
    }
        System.out.println("null");
    }

    //有头节点的翻转
    public  void reverse(){
        /*
        从第二个节点开始使用“头插法”
         */
        //从第一个节点开始
        Node cur=head.next;
        // 把头节点取下来
        head.next=null;
        //只有一个节点
        if(cur.next==null)
             return ;
        while(cur!=null)
        {
           Node temp=cur.next;
           cur.next=null;
           //取下结点然后进行头插
           insertToHead(cur);
           cur=temp;
        }
    }


    // 无头节点的翻转
    /*
     传入的是链表头节点
     输出的是翻转后的头节点
     */
    public  Node reverse(Node p){

        Node pre=null;
        Node cur=p;

        Node temp=null;
        while(cur!=null)
        {
            //下一个节点
            temp=cur.next;

            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }

    //不带头结点的链表输出
    public void print(Node p ){

        Node temp=p;
        while(temp!=null) {
            System.out.print(temp.val + "--->");
            temp=temp.next;
        }
        System.out.println("null");

    }
    public static void main(String[] args) {
        ILinkedList  linkedList= new ILinkedList();
        for(int i=0;i<10;i++)
            linkedList.insertToTail(i);

        linkedList.print();

//        linkedList.reverse(linkedList.head.next);
        linkedList.print( linkedList.reverse(linkedList.head.next));

//        for(int i=0;i<5;i++) {
//            linkedList.delete(i+2);
//            linkedList.print();
//        }
//
//        for(int i=0;i<10;i++)
//            linkedList.insertToTail(i);
//
//        linkedList.print();


    }

}
