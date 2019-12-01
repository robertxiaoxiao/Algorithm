package leetcodeTest.Week2;/*
 * @author:
 * @date:  2019/11/17/017
 * @description:
 */

import java.util.*;

//  moving boxes in mineral step
public class q4 {

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static class Node extends  Object {
        int px, py;
        int bx, by;
        int minstep;

        public Node(int px, int py, int bx, int by, int minstep) {
            this.px = px;
            this.py = py;
            this.bx = bx;
            this.by = by;
            this.minstep = minstep;
        }
        int key() {
            return (px * 20 + py) << 16 | (bx + by);
        }
        // implements comparator
    }

    public int minPushBox(char[][] arr) {
        HashSet<Integer> seen = new HashSet<>();
        HashMap<Integer, Integer> dist = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        int n = arr.length;
        if (n == 0)
            return -1;
        int m = arr[0].length;
        int px = 0;
        int py = 0;
        int bx = 0;
        int by = 0;
        int tx = 0;
        int ty = 0;
        int step = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'S') {
                    px = i;
                    py = j;
                }
                if (arr[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
                if (arr[i][j] == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        Node start = new Node(px, py, bx, by, 0);
        queue.add(start);
        seen.add(start.key());
        dist.put(start.key(), 0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node temp = queue.poll();
                // get the target
                if (temp.bx == tx && temp.by == ty)
                    return dist.get(temp.key());
                size--;
                for (int i = 0; i < 4; i++) {
                    int cpx = temp.px + dx[i];
                    int cpy = temp.py + dy[i];
                    int curstep = temp.minstep;
                    if (cpx < 0 || cpx > n || cpy < 0 || cpy > m || arr[cpx][cpy] == '#')
                        continue;
                    int cbx = temp.bx;
                    int cby = temp.by;
                    if (cpx == temp.bx && cpy == temp.by) {
                        cbx += dx[i];
                        cby += dy[i];
                        if (cbx < 0 || cbx > n || cby < 0 || cby > m || arr[cbx][cby] == '#')
                            continue;
                        curstep++;
                    }

                    Node curnode = new Node(cpx, cpy, cbx, cby, curstep);
                    if (!dist.containsKey(curnode.key()))
                        dist.put(curnode.key(), curstep);
                    else {
                        int premin = dist.get(curnode.key());
                        if (curstep<premin) {
                            dist.put(curnode.key(), curstep);
                        }
                        curnode.minstep = dist.get(curnode.key());
                    }

                    if (!seen.contains(curnode.key())) {
                        System.out.printf("%d %d %d %d %d ", curnode.bx, curnode.by, curnode.px, curnode.py, step);
                        queue.add(curnode);
                        seen.add(curnode.key());
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        HashMap<Node, Integer> hm = new HashMap<>();
        Node n1 = new Node(1, 1, 1, 1, 0);
        Node n2 = new Node(1, 1, 1, 1, 0);
        hm.put(n1, 1);
        System.out.println(hm.get(n2));
        hm.put(n2, 2);
        System.out.println();

    }
}
