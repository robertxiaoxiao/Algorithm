package LeetCode;

public class q1191 {


    static int mod = (int) 1e9 + 7;

    public int kConcatenationMaxSum(int[] arr, int k) {

        long r1 = getMax(arr) % mod;

        if (k == 1)
            return (int) r1;
        long sum = getSum(arr);
        long max = 0;
        long suf = getSuffix(arr) % mod;
        long pre = getPrefix(arr) % mod;

        if (sum > 0) {
            long r2 = suf + Math.max(k - 2, 0) * getSum(arr) % mod + pre;
            max = Math.max(r1, r2) % mod;
        } else {
            max = Math.max(r1, suf + pre);
        }

        return (int) max;
    }

    public long getSum(int[] arr) {
        int n = arr.length;
        long res = 0l;
        for (int i = 0; i < n; i++) {
            res += arr[i];
        }
        return res;
    }

    public long getMax(int[] arr) {

        int n = arr.length;

        long res = 0l, cur = 0l;
        for (int i = 0; i < n; i++) {
            cur = Math.max(cur + arr[i], arr[i]);
            res = Math.max(res, cur);
        }

        return res;
    }

    public long getSuffix(int[] arr) {

        long suf = 0, max = 0;
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            suf += arr[i];
            max = Math.max(max, suf);
        }
        return max;
    }

    public long getPrefix(int[] arr) {

        long pre = 0, max = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            pre += arr[i];
            max = Math.max(max, pre);
        }
        return max;
    }

    int modulo = 1000000007;

    /*
    3 cases:
1  maximum sum is located inside the array without any concatenation
   (this is refer to 53. Maximum Subarray)
2  if sum of array is negative - maximum sum is over somewhere after
   two array concatenation
3  if sum of the array is positive - maximum sum is based on case 2 + (k - 2) * sum
     */
    public int kConcatenationMaxSumSimply(int[] arr, int k) {

        int n = arr.length;
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        for (int i = 1; i < n; i++) {
            maxEndingHere = Math.max(maxEndingHere + arr[i], arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        if (k < 2) return maxSoFar;

        int leftSum = arr[0];
        int rightSum = arr[n - 1];
        int lMax = Math.max(0, arr[0]);
        int rMax = Math.max(0, arr[n - 1]);

        for (int i = 1; i < n; i++) {
            leftSum += arr[i];
            lMax = Math.max(lMax, leftSum);
        }

        for (int i = n - 2; i >= 0; i--) {
            rightSum += arr[i];
            rMax = Math.max(rMax, rightSum);
        }

        int headTailMax = lMax + rMax;

        if (leftSum < 0) return Math.max(maxSoFar, headTailMax);

        else return Math.max(maxSoFar, (int) (headTailMax + ((k - 2) * (long) leftSum) % modulo));

    }

}
