package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/2/7/007
 * @description:
 */

public class q968 {

    static int camera = 0;

    public int minCameraCover(TreeNode root) {
        camera = 0;
        if (minCover(root) == -1)
            camera++;
        return camera;
    }


    /*
       -1 : it need a camera
       1  : it is a camera
       0  : it has been covered
     */

    public int minCover(TreeNode root) {
        if (root == null)
            return 0;

        int ls = minCover(root.left);
        int rs = minCover(root.right);

        if (ls == 0 && rs == 0) {
            return -1;
        }

        if (ls == -1 || rs == -1) {
            camera++;
            return 1;
        }
        return 0;
    }

}
