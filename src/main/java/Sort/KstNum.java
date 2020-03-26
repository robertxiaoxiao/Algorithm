package Sort;/*
 * @author:
 * @date:  2019/12/3/003
 * @description:
 */

public class KstNum {

    public static void main(String[] args) {
        int[] a = {5, 2, 3, 7, 5, 9, 4, 2};
        //quickSort(a, 0, a.length - 1);
        for (int i = 1; i <= a.length; i++)
            System.out.println(getKnum(a, 0, a.length - 1, i));
    }
    /*
            O(n)  :
           compare times :N+N/2+n/4+....+1=2n-1
     */
    public static int getKnum(int[] arr, int start, int end, int k) {

        int i = start;
        int j = end;
        int temp = arr[end];
        while (i < j) {
            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
            while (j > i && arr[j] >= temp)
                j--;

            if (j > i) {
                arr[i] = arr[j];
                i++;
            }
        }
        arr[i] = temp;
        int tk = i - start + 1;
        if (tk == k)
            return arr[i];
        else if (tk < k)
            return getKnum(arr, i + 1, end, k - tk);
        else
            return getKnum(arr, start, i - 1, k);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end)
            return;

        int temp = arr[end];
        int i = start;
        int j = end;
        /*
            跳出条件是 i<j rather i<=j ;
         */
        while (i < j) {
            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
            while (j > i && arr[j] >= temp)
                j--;

            if (j > i) {
                arr[i] = arr[j];
                i++;
            }
        }
        arr[i] = temp;
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }

}
