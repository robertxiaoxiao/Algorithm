package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/22/022
 * @description:
 */

public class q744 {

    public char nextGreatestLetter(char[] letters, char target) {

        int i = 0;
        int j = letters.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (letters[mid] > target)
                j = mid;
            else
                i = mid + 1;
        }
        if (i == letters.length)
            return letters[0];

        return letters[i];
    }


}
