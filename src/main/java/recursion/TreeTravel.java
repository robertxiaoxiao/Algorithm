package recursion;
import BasicDStructure.Tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
此深度递归方法适用于求解Binary Tree Level Order Traversal. 比使用栈的层次遍历空间复杂度更低，
同时时间复杂度也更低。
主要点在于找到递归过程中的不变量，和递归过程中互相约束的变量，在不含返回值的递归过程中，不需要关注递归出口，
更多是关注递归的入口，以及递归中相关变量的变化。
 */
public class TreeTravel {

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
