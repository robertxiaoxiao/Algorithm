package leetcodeTest.Week5;/*
 * @author:
 * @date:  2019/12/8/008
 * @description:
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class q2 {
    static class Node{
        int size ;
        int rest;
        List<Integer>  list;
        public Node(int size, int rest) {
            this.size = size;
            this.rest = rest;
            list =new LinkedList<>() ;
        }
    }
    public List<List<Integer>> groupThePeople(int[] groupSizes) {

        List<List<Integer> >  res=new LinkedList<>();
        List<Node>  list =new LinkedList<>() ;
        calSize(groupSizes,list);
        for(Node node :list)
            res.add(new LinkedList<>(node.list)) ;

        return res  ;
    }

    private void calSize(int[] groupSizes,List<Node> list) {
        for(int i=0;i<groupSizes.length;i++) {
            int temp =groupSizes[i] ;
            boolean found=false;
            if (temp!= 0) {
                for (Node node : list)
                    if (node.size == temp&&node.rest!=0)
                    {
                        found=true;
                        --node.rest ;
                        node.list.add(i);
                        break;
                    }
                if(!found) {
                    Node newnode =new Node(temp,temp) ;
                    --newnode.rest;
                    newnode.list.add(i);
                    list.add(newnode);
                }

            }
        }
    }
}
