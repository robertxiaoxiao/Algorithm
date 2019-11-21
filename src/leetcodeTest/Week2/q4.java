package leetcodeTest.Week2;/*
 * @author:
 * @date:  2019/11/17/017
 * @description:
 */

import java.util.*;

public class q4 {

    public  static class Node {
        int x, y;
        int bx, by;
        int minStep;

        public Node(int x, int y, int bx, int by,int minStep) {
            this.x = x;
            this.y = y;
            this.bx = bx;
            this.by = by;
            this.minStep =minStep;
        }
        // implements comparator
    }

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, +1};
    public int minPushBox(char[][] grid) {

        int n = grid.length;
        if (n == 0)
            return -1;
        int m = grid[0].length;
        int x = 0, y = 0, bx = 0, by = 0, tx = 0, ty = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 'S') {
                    x = i;
                    y = j;
                }
                if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
                if (grid[i][j] == 'T') {
                    tx = i;
                    ty = j;
                }
            }


        HashMap<Node, Integer> dist = new LinkedHashMap<>();
        HashMap<Node, Boolean> inque = new LinkedHashMap<>();
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                // ascend
                return o1.minStep - o2.minStep;
                // desccend
                //return o2.minStep-o1.minStep ;
            }
        });

        Node root = new Node(x, y, bx, by,0);
        queue.add(root);
        dist.put(root, 0);
        inque.put(root,true) ;
        int ans = -1;
        while (!queue.isEmpty()) {
            Node temp = queue.peek();
            inque.put(temp,false);
            queue.poll();
            int curx = temp.x;
            int cury = temp.y;
            int curbx = temp.bx;
            int curby = temp.by;
            int curStep = dist.get(temp);
            // whether move
            int delte = 0;

            if (temp.bx == tx && temp.by == ty) {
                if (ans == -1 || ans > curStep)
                    ans = curStep;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                delte = 0;
                int x0 = temp.x + dx[i];
                int y0 = temp.y + dy[i];
                int bx0 = curbx;
                int by0 = curby;
                if (x0 < 0 || x0 >= n || y0 < 0 || y0 >= m || grid[x0][y0] == '#')
                    continue;

                if (x0 == curbx && y0 == curby) {
                    bx0 = curbx + dx[i];
                    by0 = curby + dy[i];
                    if (bx0 < 0 || bx0 >= n || by0 < 0 || by0 >= m || grid[bx0][by0] == '#')
                        continue;
                    delte = 1;
                }

                Node newnode = new Node(x0, y0, bx0, by0,curStep+delte);
                if (!dist.containsKey(newnode) || dist.get(newnode) > curStep + delte) {
                    dist.put(newnode, curStep + delte);
                }
                if(!inque.containsKey(newnode)) {
                    inque.put(newnode,true);
                    queue.offer(newnode) ;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {

        Node  temp=new Node(1,1,2,2,1);
        Node  temp1=new Node(1,1,2,2,1);
        HashMap<Node,Integer> hm= new HashMap<>();
        hm.put(temp,1);
        System.out.println(hm.containsKey(temp1));
        hm.put(temp1,1) ;

        Queue<Integer> queueAscend = new PriorityQueue<>();
        Queue<Integer> queuedescend = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < 10; i++) {
            queueAscend.add(i);
            queuedescend.add(i);
        }

        Iterator it=queueAscend.iterator();
        Iterator it1=queuedescend.iterator();

        while(it.hasNext())
            System.out.print(it.next()+"  ");

        System.out.println();
        while(it1.hasNext())
            System.out.print(it1.next()+"  ");
        }




}
