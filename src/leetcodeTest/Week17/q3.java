package leetcodeTest.Week17;

import BasicDStructure.Tree.TreeNode;

public class q3 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public boolean isSubPath(ListNode head, TreeNode root) {

        if (head == null)
            return true;
        if (root == null)
            return false;

        if (helper(head, root))
            return true;

        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean helper(ListNode head, TreeNode root) {
        if (head == null)
            return true;

        if (root == null)
            return false;

        if (head.val != root.val)
            return false;

        return helper(head.next, root.left) || helper(head.next, root.right);

    }
//    public boolean isSubPath(ListNode head, TreeNode root) {
//        if (root == null)
//            return false;
//        boolean ans = false;
//        if (root.val == head.val) {
//            ans = ans || isph(head.next, root.left);
//            ans = ans || isph(head.next, root.right);
//        }
//        if (ans)
//            return true;
//        ans = ans || isSubPath(head, root.left);
//        ans = ans || isSubPath(head, root.right);
//        return ans;
//    }

    public boolean isph(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        boolean ans = false;
        if (root.val == head.val) {
            ans = ans || isph(head.next, root.left);
            ans = ans || isph(head.next, root.right);
        }
        return ans;
    }
}
