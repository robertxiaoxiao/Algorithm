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

    public SegmentTree(int[] arr) {
        length = arr.length;
        // 2^(log2n+1) UsualKey =4;
        data = new Node[length];
        tree = new Node[4 * length];
        for (int i = 0; i < length; i++)
            data[i] = new Node(i, arr[i]);
        build(0, length - 1, 0);
    }

    public void build(int left, int right, int tr) {
        if (left == right) {
            tree[tr] = new Node(data[left]);
            return;
        }
        int mid = left + (right - left) / 2;
        build(left, mid, tr * 2 + 1);
        build(mid + 1, right, tr * 2 + 2);
        tree[tr] = new Node(tree[tr * 2 + 1].minval > tree[tr * 2 + 2].minval ? tree[tr * 2 + 2] : tree[tr * 2 + 1]);
    }

    public Node query(int left, int right) {
        return query(left, right, 0, length - 1, 0);
    }

    public Node query(int left, int right, int curl, int curr, int tr) {
        if (left == curl && right == curr)
            return tree[tr];
        //   if we want the idx ,we need to return  index ;
        //   return tree[tr];
        int mid = curl + (curr - curl) / 2;
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

}
