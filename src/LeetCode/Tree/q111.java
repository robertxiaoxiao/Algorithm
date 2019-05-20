package LeetCode.Tree;

import BasicDStructure.TreeNode;

public class q111 {
    public int minDepth(TreeNode root) {

         if(root==null)
             return -1;

         return helper(root,1);
    }

    public int helper(TreeNode node,int lenth){

        if(node.left==null&&node.right==null)
            return  lenth;

        int left=Integer.MAX_VALUE;
        int right=Integer.MAX_VALUE;
        if(node.left!=null)
            left=helper(node.left,lenth+1);

        if(node.right!=null)
            right=helper(node.right,lenth+1);

        return Math.min(left,right);

    }


}
