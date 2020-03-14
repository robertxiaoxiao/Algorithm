package LeetCode.Tree;

public class q1373 {

    int max = 0;

    public int maxSumBST(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }

    // min, max ,sum ,isvalid
    public int[] helper(TreeNode root) {
        if (root == null)
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1};
        int[] l = helper(root.left);
        int[] r = helper(root.right);
        boolean isvalid = false;
        if (l[3] == 1 && r[3] == 1 && root.val > l[1] && root.val < r[0])
            isvalid = true;
        int sum = root.val;
        sum += l[2] + r[2];
        if (isvalid && sum > max)
            max = sum;

        int curmin = Math.min(l[0], root.val);
        int curmax = Math.max(r[1], root.val);
        return new int[]{curmin, curmax, sum, isvalid ? 1 : 0};
    }

}
