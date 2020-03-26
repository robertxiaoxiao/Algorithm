package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/7/007
 * @description:
 */

import java.util.*;

public class q133 {

    public Node cloneGraph(Node node) {
        Node[] nodes = new Node[101];
        List<Integer>[] path = new List[101];
        int idx = 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        HashMap<Node, Integer> hm = new HashMap<>();
        hm.put(node, idx);
        Node temp = new Node();
        temp.val = node.val;
        nodes[idx++] = temp;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                Node cur = queue.poll();
                int curidx = hm.get(cur);
                if (path[curidx].size() == 0)
                    path[curidx] = new LinkedList<>();
                List<Integer> list = path[curidx];
                for (Node next : cur.neighbors) {
                    if (!hm.containsKey(next)) {
                        hm.put(next, idx++);
                        int nidx = hm.get(next);
                        Node curtemp = new Node();
                        curtemp.val = next.val;
                        nodes[nidx] = curtemp;
                        queue.add(next);
                    }
                    list.add(hm.get(next));
                }
            }
        }
        // NOTICE : NULL POINT  IF WE INITIATE THE ARRAY BEFORE
        for (int i = 0; i < idx; i++) {
            Node curnode = nodes[i];
            List<Node> tlist = new LinkedList<>();

            for (int nidx : path[i])
                tlist.add(nodes[nidx]);
            curnode.neighbors = tlist;
        }
        return nodes[0];
    }

    static HashMap<Node, Node> hm = new HashMap<>();

    public Node cloneGraphusingrec(Node node) {
        if (hm.containsKey(node))
            return hm.get(node);

        Node newnode = new Node();
        newnode.val = node.val;
        newnode.neighbors = new LinkedList<Node>();
     // NOTICE : IT SHOULD BE NEWED BEFORE ADDING THE NEIGHBORS
        hm.put(node, newnode);
        for (Node neigibor : node.neighbors) {
            Node newneighbor=cloneGraphusingrec(neigibor);
            newnode.neighbors.add(newneighbor);
        }

        return newnode;
    }
}
