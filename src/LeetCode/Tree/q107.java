package LeetCode.Tree;

import java.util.*;
import  BasicDStructure.TreeNode;
/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 (ie, from left to right, level by level from leaf to root).
input : [3,9,20,null,null,15,7],
output: [
  [15,7],
  [9,20],
  [3]
]
 */
public class q107 {
    /*
      brutal solution
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
         List<List<Integer>> result=new LinkedList<List<Integer>>();
         if(root==null)
             return result;
         Stack<List<Integer>>  restack=new Stack<>();
         Stack<TreeNode>  stackleft=new Stack<>();
          Stack<TreeNode>  stackright=new Stack<>();
         stackleft.add(root);
         TreeNode temp=null;
         while (!stackleft.isEmpty()||!stackright.isEmpty())
         {
             List<Integer>  templist =new LinkedList<>();

             while(!stackleft.isEmpty()) {
                 temp = stackleft.pop();
                 if(temp!=null) {
                     templist.add(temp.val);
                     if (temp.left != null)
                         stackright.add(temp.left);
                     if (temp.right != null)
                         stackright.add(temp.right);
                 }
             }

             while(!stackright.isEmpty()) {
                 temp = stackright.pop();
                 stackleft.add(temp);
             }

             restack.add(templist);
         }

         while (!restack.isEmpty())
             result.add(restack.pop());
         return result;
    }
    /*
         recursion solution
     */

    public List<List<Integer>> levelOrderBottomRecur(TreeNode root){

        List<List<Integer>>  result=new ArrayList<>();

        if(root==null)
            return result;

        levelOrderBottom(result,root,1);

        Collections.reverse(result);
        return result;
    }
    /*
       深度遍历，通过记录节点层数，然后从左往右添加值
     */
    private void levelOrderBottom(List<List<Integer>> l, TreeNode node, int curlevel){

        //第一次遍历到更深节点
        if( l.size()<curlevel)
            l.add(new ArrayList<>());

        //没必要，保证第一步输入的值不为空，后续节点都不会为空
        if(node==null)
            return ;

        if(node.left!=null)
            levelOrderBottom(l,node.left,curlevel+1);

        //把当前节点值缓存
        l.get(curlevel-1).add(node.val);

        if(node.right!=null)
            levelOrderBottom(l,node.right,curlevel+1);

    }




}
