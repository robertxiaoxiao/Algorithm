package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2019/12/18/018
 * @description:
 */


public class binaryAd {

    /*
      basic template
     */
    public int binaryserach(int[] nums, int l, int r) {
        int i = l;
        int j = r;
        while (i < j) {
            int mid = (i + j) >> 1;
            if (g(mid))
                r = mid;
            else
                l = mid + 1;
        }
        // g(l)==true ,l is the less
        // l==r ans not exist
        return l;
    }

    // nums[i]>=k
    public int lower_bound(int[] nums, int k) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] >= k)
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }

    // nums[i]>k
    public int upper_bound(int[] nums, int k) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] > k)
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }

    public boolean g(int num) {
        return true;
    }

    //  {1,2,3,4,5,5,5,7,8,9,10,10,10}
    public static int binarySearchLeft(int[] temp, int k) {
        int i = 0;
        int j = temp.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            //      System.out.printf("%d %d %d %d\r\n", i, j, mid, temp[mid]);
            if (temp[mid] >= k)
                j = mid - 1;
            else
                i = mid + 1;
        }

        // the first one >=k
        return i;
    }

    public static int binarySearchRight(int[] temp, int k) {
        int i = 0;
        int j = temp.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            //  System.out.printf("%d %d %d %d\r\n", i, j, mid, temp[mid]);
            if (temp[mid] >= k)
                j = mid - 1;
            else
                i = mid + 1;
        }
        return j;
    }

    public static int binarySearchUsingWhile(int[] temp, int k) {
        int i = 0;
        int j = temp.length - 1;

        while (i <= j) {
            int mid = i + (j - i) / 2;
            System.out.println(temp[mid]);
            if (temp[mid] > k)
                j = mid - 1;
            else if (temp[mid] < k)
                i = mid + 1;
            else {
                int cur = mid + 1;
                while (cur < temp.length && temp[cur] == k)
                    cur++;
                return cur - 1;
            }
        }
        return -1;
    }

    public static int bsFLESSThank(int[] temp, int k) {
        int l = 0;
        int r = temp.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (temp[mid] >= k)
                r = mid;
            else
                l = mid + 1;
        }
        System.out.printf("the first less than k at index  %d  :%d\r\n ", l, temp[l]);
        return l;
    }

    public static int bsLLESSThank(int[] temp, int k) {
        int l = 0;
        int r = temp.length;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (temp[mid] > k)
                r = mid;
            else
                l = mid + 1;
        }
        System.out.printf("the last less than k at index  %d  :%d\r\n ", l - 1, temp[l - 1]);
        return l - 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 7, 8, 9, 10, 10, 10, 10};
        bsFLESSThank(arr, 5);
        bsLLESSThank(arr, 5);
        bsFLESSThank(arr, 10);
        bsLLESSThank(arr, 10);
        System.out.println();
        // System.out.println(arr.length);
        //  System.out.println(binarySearchUsingWhile(arr, 5));
        //System.out.println(binarySearchUsingWhile(arr, 9));
        //  System.out.println(binarySearchUsingWhile(arr, 10));
        // System.out.println(binarySearchUsingWhile(arr, 5));
    }
}
