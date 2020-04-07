package LeetCode.TwoPointers;

public class q28 {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        int n = haystack.length();
        int m = needle.length();
        char c = needle.charAt(0);
        int max = n - m + 1;
        for (int i = 0; i < max; i++) {
            if (haystack.charAt(i) != c) {
                while (i < max && haystack.charAt(i) != c)
                    i++;
            }
            if (i < max) {
                int end = i + m;
                int k = i + 1;
                int j = 1;
                for (; k < end && j < m; k++, j++) {
                    if (haystack.charAt(k) != needle.charAt(j))
                        break;
                }
                if (k == end)
                    return end - m;
            }
        }
        return -1;
//        for (int i = 0, j = 0; i < n && j < m; ) {
//            while (i < n && haystack.charAt(i) != needle.charAt(j))
//                i++;
//            if (i == n)
//                return -1;
//
//            int pre = i;
//            while (i < n && j < m && haystack.charAt(i) == needle.charAt(j)) {
//                i++;
//                j++;
//            }
//            if (j == m)
//                return pre;
//            else {
//                i = pre + 1;
//                j = 0;
//            }
//        }

    }
}
