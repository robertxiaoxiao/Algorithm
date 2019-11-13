package RelatedImpl;/*
 * @author:
 * @date:  2019/11/13/013
 * @description:
 */

import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import sun.plugin2.message.HeartbeatMessage;

import java.util.HashMap;
import java.util.Iterator;

/*
    O(1) : ha
 */
public class LinkedHashMap implements Iterator {
    //HashMap hm = new java.util.LinkedHashMap();
    static int defautlhashsize = 10;
    static Node[] hashtable;
    // recurrent head node
    Node head;
    int curSize =0;

    //  threshold of lru
    static int defaultMaxSize =5;

    static Iterator lhmiterator;
    private class lhashmapIterator implements Iterator {

        private Node lastreadNode;

        public lhashmapIterator() {
            this.lastreadNode = head;
        }

        @Override
        public boolean hasNext() {
            if (head == null)
                return false;

            if (lastreadNode.after == head)
                return false;

            return true;
        }

        @Override
        public Object next() {
            lastreadNode = lastreadNode.after;
            return lastreadNode;
        }
    }

    @Override
    public boolean hasNext() {
        return lhmiterator.hasNext();
    }

    @Override
    public Object next() {
        return lhmiterator.next();
    }

    static class Node {
        Node before;
        Node after;
        int key;
        int val;
        int hashkey;
        // hash collision to store redundancy
        Node next;

        public Node() {
        }

        public Node(int key, int val, int hashkey) {
            this.key = key;
            this.val = val;
            this.hashkey = hashkey;
        }
    }

    private Iterator getiter() {
        return new lhashmapIterator();
    }

    public LinkedHashMap() {
        hashtable = new Node[defautlhashsize];
        head = new Node();
        head.before = head;
        head.after = head;
        lhmiterator = new lhashmapIterator();
    }

    Node get(int key) {
        int hashkey = getHash(key);
        Node cur = hashtable[hashkey];
        while (cur != null) {
            if (cur.key == key) {
                moveNodeTotail(cur);
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    private void addNodeTotail(Node cur) {
        Node tail = head.before;
        if (tail == head) {
            head.after = cur;
            head.before = cur;
            cur.after = head;
            cur.before = head;
            return;
        }
        // it must be clear
        head.before = cur;
        tail.after = cur;
        cur.before = tail;
        cur.after = head;

    }

    private void moveNodeTotail(Node cur) {
        Node tail = head.before;
        if (cur == tail)
            return;
          /*
           delete from list
           */
        Node curnext = cur.after;
        Node curbefore = cur.before;
        curbefore.after = curnext;
        curnext.before = curbefore;

        // add to the tail
        tail.after = cur;
        cur.before = tail;
        cur.after = head;
        head.before = cur;
    }

    private boolean remove(int key) {
        Node found = get(key);
        if (found == null)
            return false;
        deleteNodefromlist(found);
        return true;
    }

    /*
       to complete the delete structure in hashtable
     */
    private void deleteFromHashtable(Node dnode){
        int hashkey=dnode.hashkey;
        Node cur=hashtable[hashkey];
        Node lastread=null;
        while (cur != null) {
            if (cur.key == dnode.key&&lastread!=null) {
                lastread.next=dnode.next;
            }
            lastread=cur;
            cur = cur.next;
        }
    }
    private void deleteNodefromlist(Node dnode) {

        Node before = dnode.before;
        Node after = dnode.after;
        before.after = after;
        after.before = before;
        dnode = null;
    }

    /*
        hashkey = the number of key in unit digit
     */
    private int getHash(int key) {
        int temp = key;
        while (temp >= 10) {
            temp = temp - (temp / 10) * 10;
        }
        return temp;
    }

    void put(int key, int val) {
        int hashkey = getHash(key);
        Node cur = hashtable[hashkey];
        Node lastnode = cur;
        while (cur != null) {
            if (cur.key == key) {   //update
                cur.val = val;
                addNodeTotail(cur);
                return;
            }
            lastnode = cur;
            cur = cur.next;
        }
        // not existed in that table
        Node newnode = new Node(key,val,hashkey);

        if (lastnode == null)
            hashtable[hashkey] = newnode;
        else
            lastnode.next = newnode;
        addNodeTotail(newnode);
        curSize++;
        while(curSize>defaultMaxSize)
            removeFromHead();
    }
    /*
       it only be called when the size is bigger than the threshold,so the list cannot be empty(only head node)
     */
    void removeFromHead(){
         Node headFirst=head.after;
         deleteNodefromlist(headFirst);
         curSize--;
    }

    void print() {
        System.out.println("curreent state :");
        Iterator it = getiter();
        while (it.hasNext()) {
            print((Node) it.next());
            System.out.print(" ");
        }
        System.out.println();
    }

    void print(Node node) {
        System.out.print("(" + node.key + "," + node.val + ")");
    }

    /*
       LRU  at the head : least recently used
     */
    public static void main(String[] args) {
        LinkedHashMap map = new LinkedHashMap();
        for (int i = 0; i <=11; i++)
            map.put(i, i);

        map.print();

        for (int i = 0; i <=11; i=i+3)
           map.get(i);
        map.print();

//        for (int i = 0; i < 11; i = i + 3)
//            map.remove(i);
//        map.print();
    }


}
