package LeetCode.Tree;

import java.util.HashMap;
import java.util.Map;

public class q105 {


    static int n;
    static int[] pre;
    static int[] in;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        in = inorder;
        n = preorder.length;
        if (n == 0)
            return null;

        return buildTree(0, n - 1, 0, n - 1);
    }

    public TreeNode buildTree(int pi, int pj, int ii, int ij) {

        if (pi > pj)
            return null;

        TreeNode root = new TreeNode(pre[pi]);
        if (pi == pj)
            return root;

        int len = 0;
        for (int k = ii; k <= ij; k++) {
            if (in[k] == pre[pi])
                len = k - ii;
        }

        root.left = buildTree(pi + 1, pi + len, ii, ii + len - 1);
        root.right = buildTree(pi + len + 1, pj, ii + len + 1, ij);
        return root;
    }


    public TreeNode buildTreeUsinghashmap(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }
}
