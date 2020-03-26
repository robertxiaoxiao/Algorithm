package LeetCode.UsefulDS;/*
 * @author: Robert
 * @date:  2020/2/21/021
 * @description:
 */

public class SegmentTree {

    class TreeNode {
        int val;
        int l;
        int r;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, int l, int r, TreeNode left, TreeNode right) {
            this.val = val;
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
        }
    }

    int[] nums;
    TreeNode root;

    public SegmentTree(int[] ns) {
        nums = ns;
        root = buildTree(0, nums.length - 1);
    }

    public TreeNode buildTree(int l, int r) {
        if (l == r) {
            return new TreeNode(nums[l], l, r, null, null);
        }
        int mid = (l + r) / 2;
        TreeNode left = buildTree(l, mid);
        TreeNode right = buildTree(mid + 1, r);
        return new TreeNode(func(left.val, right.val), l, r, left, right);
    }


    public int func(int l, int r) {
        return -1;
    }

    public void update(TreeNode root, int idx, int val) {
        if (root.l == root.r && root.l == idx) {
            root.val = val;
            return;
        }
        int l = root.l;
        int r = root.r;
        int mid = (l + r) / 2;
        if (idx < l) {
            update(root.right, idx, val);
        } else
            update(root.left, idx, val);
        root.val = func(root.left.val, root.right.val);
    }

    public int query(TreeNode root, int l, int r) {
        int cr = root.r;
        int cl = root.l;
        if (cr == r && cl == l)
            return root.val;
        int mid = (l + r) / 2;
        if (cr < mid) {
            return query(root.left, l, r);
        } else if (cl > mid) {
            return query(root.right, l, r);
        } else {
            return func(query(root.left, cl, mid), query(root.right, mid + 1, cr));
        }
    }
}
