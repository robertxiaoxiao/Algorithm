package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2019/12/18/018
 * @description:
 */


public class binaryAd {

    //  {1,2,3,4,5,5,5,7,8,9,10,10,10}
    public static int binarySearchLeft(int[] temp, int k) {
        int i = 0;
        int j = temp.length - 1;
        while (i<=j) {
            int mid = i + (j - i) / 2;
      //      System.out.printf("%d %d %d %d\r\n", i, j, mid, temp[mid]);
            if (temp[mid] >=k)
                j = mid-1;
            else
                i = mid + 1;
        }
        return i;
    }
    public static int binarySearchRight(int[] temp, int k) {
        int i = 0;
        int j = temp.length - 1;
        while (i<=j) {
            int mid = i + (j - i) / 2;
          //  System.out.printf("%d %d %d %d\r\n", i, j, mid, temp[mid]);
            if (temp[mid] >=k)
                j = mid-1;
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


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 7, 8, 9, 10, 10, 10, 12};
        // System.out.println(arr.length);
        //  System.out.println(binarySearchUsingWhile(arr, 5));
        //System.out.println(binarySearchUsingWhile(arr, 9));
        //  System.out.println(binarySearchUsingWhile(arr, 10));
        // System.out.println(binarySearchUsingWhile(arr, 5));
        System.out.println(binarySearchLeft(arr, 5));
        System.out.println(binarySearchRight(arr, 5));
    }
}
