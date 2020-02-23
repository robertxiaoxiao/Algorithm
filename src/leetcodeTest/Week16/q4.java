package leetcodeTest.Week16;/*
 * @author: Robert
 * @date:  2020/2/23/023
 * @description:
 */

public class q4 {

    // num 3 property
    public String largestMultipleOfThree(int[] digits) {
        int n = digits.length;
        int[] nums = new int[10];
        long sum = 0;
        for (int i : digits) {
            nums[i]++;
            sum += i;
        }
        if (sum == 0)
            return "0";

        if (sum / 3 == 0)
            return "";

        if (sum % 3 == 0) {
            return getstr(nums);
        } else if (sum % 3 == 1) {
            String s1 = delete1(nums, (int) sum, 1);

            String s2 = delete2(nums, (int) sum, 2);
            if (compare(s1, s2))
                return s1;
            else
                return s2;
        } else {
            String s1 = delete1(nums, (int) sum, 2);
            String s2 = delete2(nums, (int) sum, 1);
            System.out.println(s2);
            if (compare(s1, s2))
                return s1;
            else
                return s2;
        }
    }

    public boolean compare(String s1, String s2) {
        if (s1.length() == s2.length())
            return s1.compareTo(s2) > 0;
        return s1.length() > s2.length();
    }

    public String delete2(int[] tnums, int sum, int cnt) {
        int[] nums = tnums.clone();

        for (int i = 1; i <= cnt; i++) {
            if (nums[2] - 1 >= 0) {
                nums[2] -= 1;
                sum -= 2;
            } else {
                if (nums[5] - 1 >= 0) {
                    nums[5] -= 1;
                    sum -= 5;
                } else {
                    if (nums[8] - 1 >= 0) {
                        nums[8] -= 1;
                        sum -= 8;
                    }
                }
            }
        }
        boolean flag = sum % 3 == 0;
        if (flag)
            return getstr(nums);
        else
            return "";
    }

    public String delete1(int[] tnums, int sum, int cnt) {
        int[] nums = tnums.clone();
        int pre = sum;
        for (int i = 1; i <= cnt; i++) {
            if (nums[1] - 1 >= 0) {
                nums[1] -= 1;
                sum -= 1;
            } else {
                if (nums[4] - 1 >= 0) {
                    nums[4] -= 1;
                    sum -= 4;
                } else {
                    if (nums[7] - 1 >= 0) {
                        nums[7] -= 1;
                        sum -= 7;
                    }
                }
            }
        }
        boolean flag = sum % 3 == 0;
        if (flag)
            return getstr(nums);
        else
            return "";
    }

    public String getstr(int[] nums) {
        StringBuffer sb = new StringBuffer();
        for (int i = 9; i >= 0; i--) {
            int k = nums[i];
            while (k != 0) {
                sb.append(i);
                k--;
            }
        }
        return sb.toString();
    }

}
