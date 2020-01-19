package leetcodeTest.Week11;/*
 * @author: Robert
 * @date:  2020/1/19/019
 * @description:
 */

public class q3 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static boolean flag = false;

    public TreeNode removeLeafNodes(TreeNode root, int target) {
          TreeNode ans=root;

          while(true){
              flag =false;
              ans=helper(ans,target);
              if(!flag)
                  return ans ;
          }
    }

    public TreeNode helper(TreeNode root, int target) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null && root.val == target) {
            flag = true;
            return null;
        }
        if (root.left != null)
            root.left = helper(root.left, target);
        if (root.right != null)
            root.right = helper(root.right, target);
        return root ;
    }
}
