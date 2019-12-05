package leetcodeTest.Week2;/*
 * @author:
 * @date:  2019/11/17/017
 * @description:
 */

import java.util.*;
import java.util.function.Function;

//  moving boxes in mineral step
public class q4 {

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static class Node extends Object {
        int px, py;
        int bx, by;
        int g, h;

        public Node(int px, int py, int bx, int by) {
            this.px = px;
            this.py = py;
            this.bx = bx;
            this.by = by;
        }

        int f() {
            return g + h;
        }

        int key() {
            return (px * 20 + py) << 16 | (bx * 20 + by);
        }
        // implements comparator
    }

    public static int calmanhattan(Node curnode, int tx, int ty) {
        return Math.abs(tx - curnode.bx) + Math.abs(ty - curnode.by);
    }

    public static int minPushBoxUsingPq(char[][] arr) {
        HashSet<Integer> seen = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.f() - o2.f();
            }
        });
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
        Node curnode = new Node(px, py, bx, by);
        curnode.g = 0;
        curnode.h = calmanhattan(curnode, tx, ty);
        pq.add(curnode);
        seen.add(curnode.key());
        while (!pq.isEmpty()) {
            Node cnode = pq.peek();
            pq.remove();
            for (int i = 0; i < 4; i++) {
                Node next = canpush(arr, cnode, dx[i], dy[i]);
                if (next == null || seen.contains(next.key()))
                    continue;
                //meet the target
                next.g = cnode.g + 1;
                next.h = calmanhattan(next, tx, ty);
                if (next.bx == tx && next.by == ty)
                    return next.g;
                seen.add(next.key());
                pq.add(next);
            }
        }
        return -1;
    }


    public static Node canpush(char[][] arr, Node curnode, int dx, int dy) {

        int cbx = curnode.bx;
        int cby = curnode.by;
        int nbx = cbx + dx;
        int nby = cby + dy;
        // whether  next box position  is valid
        if (nbx < 0 || nbx >= arr.length || nby < 0 || nby >= arr[0].length || arr[nbx][nby] == '#')
            return null;

        if (haspath(arr, curnode, cbx - dx, cby - dy))
            return new Node(cbx, cby, cbx + dx, cby + dy);

        return null;
    }

    public static boolean haspath(char[][] arr, Node curnode, int tx, int ty) {
        HashSet<Integer> hashSet = new HashSet<>();
        return canarrive(arr, hashSet, curnode.bx, curnode.by, curnode.px, curnode.py, tx, ty);
    }

    public static boolean canarriveUsingBFS(char[][] arr, HashSet<Integer> nodes, int bx, int by, int nx, int ny, int tx, int ty) {
        if (tx < 0 || tx >= arr.length || ty < 0 || ty >= arr[0].length || arr[tx][ty] == '#')
            return false;
        Queue<Integer> queue = new ArrayDeque<>();
        int m = 20;
        queue.add(nx * m + ny);
        nodes.add(nx * m + ny);
        while (!queue.isEmpty()) {
            int temp = queue.peek();
            queue.remove();
            int curx = temp / m;
            int cury = temp % m;
            for (int i = 0; i < 4; i++) {
                curx += dx[i];
                cury += dy[i];
                if (curx == bx && cury == by)
                    continue;
                if (curx == tx && cury == ty)
                    return true;
                if (nodes.contains(curx * m + cury))
                    continue;
                nodes.add(curx * m + cury);
                if (curx < 0 || curx >= arr.length || cury < 0 || cury >= arr[0].length || arr[curx][cury] == '#')
                    continue;
                queue.add(curx * m + cury);
            }
        }
        return false;
    }


    public static boolean canarrive(char[][] arr, HashSet<Integer> nodes, int bx, int by, int nx, int ny, int tx, int ty) {

        if (nx == tx && ny == ty)
            return true;

        if (tx < 0 || tx >= arr.length || ty < 0 || ty >= arr[0].length || arr[tx][ty] == '#')
            return false;

        if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[0].length || arr[nx][ny] == '#')
            return false;

        // meet the box
        if (nx == bx && ny == by)
            return false;

        if (nodes.contains(nx * 20 + ny))
            return false;
        else
            nodes.add(nx * 20 + ny);

        return canarrive(arr, nodes, bx, by, nx + 1, ny, tx, ty) || canarrive(arr, nodes, bx, by, nx - 1, ny, tx, ty)
                || canarrive(arr, nodes, bx, by, nx, ny + 1, tx, ty) || canarrive(arr, nodes, bx, by, nx, ny - 1, tx, ty);
    }

    public static int minPushBox(char[][] arr) {
        HashSet<Integer> seen = new HashSet<>();
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
        Node curnode = new Node(px, py, bx, by);
        queue.add(curnode);
//        PriorityQueue<Node>  pq=new PriorityQueue<>(new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                return calmahhatten(o1,tx,ty)-calmahhatten();
//            }
//        });
        seen.add(curnode.key());
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                --size;
                Node cnode = queue.peek();
                queue.remove();
                for (int i = 0; i < 4; i++) {
                    /*
                          dir arr : can be merged in 5 size arr[0,-1,0,1,0]
                            for(int i=0;i<4;i++)
                            dx=a[i];       0    -1  0   1
                            dy=a[i+1];    -1    0   1   0
                      */
                    Node next = canpush(arr, cnode, dx[i], dy[i]);
                    if (next == null || seen.contains(next.key()))
                        continue;
                    //meet the target
                    if (next.bx == tx && next.by == ty)
                        return step + 1;

                    seen.add(next.key());
                    queue.add(next);
                }
            }
            // forward one step;
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {

        HashMap<Node, Integer> hm = new HashMap<>();
        Node n1 = new Node(1, 1, 1, 1);
        Node n2 = new Node(1, 1, 1, 1);
        hm.put(n1, 1);
        System.out.println(hm.get(n2));
        hm.put(n2, 2);
        System.out.println();

    }
}
