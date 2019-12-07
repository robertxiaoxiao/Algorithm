package BasicDStructure.Tree;/*
 * @author:
 * @date:  2019/10/28/028
 * @description:
 */

//  SegmentTree
/*
   SegmentTree implements  in Node(idx ,val ) ;
   each query we can get the targetNode (val,idx );
 */
public class SegmentTree {

    static class Node {
        int minval;
        int idx;

        public Node(int idx, int val) {
            this.idx = idx;
            this.minval = val;
        }

        public Node(Node temp) {
            if (temp != null) {
                this.idx = temp.idx;
                this.minval = temp.minval;
            }
        }
    }

    int length;
    Node[] data;
    Node[] tree;
    // lazy mark
    int[] Add;

    public SegmentTree(int[] arr) {
        length = arr.length;
        // 2^(log2n+1) UsualKey =4;
        data = new Node[length];
        tree = new Node[4 * length];
        Add = new int[4 * length];
        for (int i = 0; i < length; i++)
            data[i] = new Node(i, arr[i]);
        build(0, length - 1, 0);
    }

    private void build(int left, int right, int tr) {
        if (left == right) {
            tree[tr] = new Node(data[left]);
            return;
        }
        int mid = left + (right - left) / 2;
        build(left, mid, tr * 2 + 1);
        build(mid + 1, right, tr * 2 + 2);
        pushup(tr);
        //      tree[tr] = new Node(tree[tr * 2 + 1].minval > tree[tr * 2 + 2].minval ? tree[tr * 2 + 2] : tree[tr * 2 + 1]);
    }

    private Node query(int left, int right) {
        return query(left, right, 0, length - 1, 0);
    }

    /*
       return the object reference
     */
    private Node query(int left, int right, int curl, int curr, int tr) {
        if (left == curl && right == curr)
            return tree[tr];
        //   if we want the idx ,we need to return  index ;
        //   return tree[tr];
        int mid = curl + (curr - curl) / 2;

        // pushdown the alter mark rather it may be wrong ;
        pushdown(tr, mid - left + 1, right - mid);

        if (right <= mid)
            return query(left, right, curl, mid, tr * 2 + 1);
        else if (left > mid)
            return query(left, right, mid + 1, curr, tr * 2 + 2);
        else {
            //
            Node leftnode = query(left, mid, curl, mid, tr * 2 + 1);
            Node rightnode = query(mid + 1, right, mid + 1, curr, tr * 2 + 2);
            return leftnode.minval > rightnode.minval ? rightnode : leftnode;
            //  return Math.min(tree[query(left, mid, curl, mid, tr * 2 + 1)], tree[query(mid + 1, right, mid + 1, curr, tr * 2 + 2)]);
        }
    }

    private void updateVal(int val, int tnode) {
        data[tnode].minval = val;
        update(val, tnode, 0, length - 1, 0);
    }

    //  segmentree  iterating parameter :
    //  node [left ,left ] =arr[left] , treenode  tr : the node in segmentTree ,the left and right marks the internal sides;
    private void update(int val, int l, int left, int right, int tr) {

        if (l == left) {
            tree[tr].minval = val;
            return;
        }
        int mid = left + (right - left) / 2;
        if (l <= mid)
            update(val, l, left, mid, tr * 2 + 1);
        else
            update(val, l, mid + 1, right, tr * 2 + 2);

        pushup(tr);
    }

    private void pushup(int tr) {
        tree[tr] = new Node(tree[tr * 2 + 1].minval >= tree[tr * 2 + 2].minval ? tree[tr * 2 + 2] : tree[(tr * 2) + 1]);
    }

    /*
        update  all the nodes in the assigned interval
        e.x   add the count of nodes in the interval
        if we update [L,R] , and we will update the node [cl,cr] belongs to [L,R] ;
        and then  we pushup(tr) to update the node  ;
                10
              #3     7
           ?1   ?2   #3  4
         so if we update [0,3]+=C  in [0,4]
         we will first upodate  [0,2] [2,3] and then we pushup(5),pushup(3) ,pushup(0)
         and when we query(0,0) ,we will find Add[4] =C ,so we will update node3 at that time;
     */
    public void updateInterval(int val, int l, int r, int curl, int curr, int tr) {
        if (l <= curl && r >= curr) {
            // update current node and  mark the node ;
            tree[tr].minval += val * (l - r + 1);
            Add[tr] += val;
            return;
        }
        int mid = curl + (curr - curl) / 2;
        // if intersection exists ,update the leftchildnode or the rightchildnode
        if (l <= mid)
            updateInterval(val, l, r, curl, mid, tr * 2 + 1);
        if (r > mid)
            updateInterval(val, l, r, mid + 1, curr, tr * 2 + 2);

        pushup(tr);
    }

    //  add nodes ,so we need to know the nums of leftchildnodes and rightchildnodes
    public void pushdown(int tr, int ln, int rn) {
        if (tr > 4 * length)
            return;

        if (Add[tr] != 0) {
            Add[tr * 2 + 1] += Add[tr];
            Add[tr * 2 + 2] += Add[tr];
            tree[tr * 2 + 1].minval += Add[tr] * ln;
            tree[tr * 2 + 1].minval += Add[tr] * rn;
            // clear the add mark
            Add[tr] = 0;
        }
    }
    public static void main(String[] args) {
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;
        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println(segmentTree.query(3, n - 1).minval);
        segmentTree.updateVal(-1, n / 2);
        System.out.println(segmentTree.query(3, n - 1).minval);
    }
}
