package LeetCode.Tree;

public class q1372 {
    int max = 0;

    public int longestZigZag(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }

    // {{len,dir} } -1 0 1
    public int[] helper(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};

        int[] l = helper(root.left);
        int[] r = helper(root.right);
        int lmax = l[1] + 1;
        int rmax = r[0] + 1;
        int m = Math.max(lmax, rmax);
        if (m > max)
            max = m;
        return new int[]{lmax, rmax};
    }

}
