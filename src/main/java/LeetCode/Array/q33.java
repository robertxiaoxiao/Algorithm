package LeetCode.Array;

public class q33 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0)
            return -1;

        int r = nums.length - 1;
        int l = 0;
        while (l < r) {
            // mid>  r :   minval in the right
            // mid< r :  minval in the left
            int mid = l + r >> 1;
            if (nums[mid] > nums[r])
                l = mid + 1;
            else
                r = mid;
        }

        int rot = l;
        l = 0;
        r = n;

        while (l < r) {
            int mid = l + r >> 1;
            int realmid = (mid + rot) % n;
            if (nums[realmid] >= target)
                r = mid;
            else
                l = mid + 1;
        }

        int real = (l + rot) % n;
        if (nums[real] == target)
            return real;

        return -1;
    }
}
