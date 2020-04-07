package LeetCode.Array;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class q41 {


    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++)
            list.add(i);


       Integer[] arr=  list.toArray(new Integer[list.size()]);
       for (int i: arr)
           System.out.println(i);

    }

    // Leverage index of the original array
    // How to leverage:
    // 1. if negative number, ignore
    // 2. if nums[i] >= nums.length, ignore
    // 3. if nums[i] within 0 to nums.length -1, swap index i and index nums[i]
    public int firstMissingPositive(int[] A) {
        int n = A.length;

        /*
            while (i < n)
        {
            if (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i])
                swap(nums[i], nums[nums[i]-1]);
            else
                i++;
        }
         A[A[i] - 1] != A[i] means that the right position for the current
         number (A[i]) is occupied with another number, that is,
         the position(A[i]-1) holds the wrong number. After swapping,
         we make sure that the position holds the right one.
         Before we modify the array, the number of positions
         that hold wrong number is at most n,
         and after each loop, we ascertain that another one is right.
         */
        for (int i = 0; i < A.length; i++) {
            while (A[i] > 0 && A[i] <= n && A[A[i] - 1] != A[i]) {
                swap(A, A[i] - 1, i);
                print(A);
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return A.length + 1;
    }

    public void print(int[] nums) {
        for (int i : nums)
            System.out.print(i + " ");
        System.out.println();
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
