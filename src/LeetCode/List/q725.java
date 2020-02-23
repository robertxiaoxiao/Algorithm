package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/21/021
 * @description:
 */

public class q725 {

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] ans = new ListNode[k];
        int t = 0;
        ListNode temp = root;
        ListNode f = root;
        ListNode pre = root;
        int initT = k;
        while (t < k && temp != null) {
            int len = getlen(temp);
            int cur = len / initT + len % initT == 0 ? 0 : 1;
            int j = 0;
            while (j < cur) {
                pre = temp;
                temp = temp.next;
                j++;
            }
            initT--;
            System.out.println(cur);
            ans[t++] = f;
            pre.next = null;
            f = temp;
        }
        return ans;
    }

    public int getlen(ListNode root) {
        int cnt = 0;
        while (root != null) {
            cnt++;
            root = root.next;
        }
        return cnt;
    }
}
