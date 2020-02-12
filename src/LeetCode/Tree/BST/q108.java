package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/12/012
 * @description:
 */

public class q108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int i, int j) {
        if (i > j)
            return null;
        int mid = (i + j) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (i == j)
            return root;

        root.left = sortedArrayToBST(nums, i, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, j);
        return root;
    }

}
