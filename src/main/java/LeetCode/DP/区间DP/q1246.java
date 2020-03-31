package LeetCode.DP.区间DP;

public class q1246 {


    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 1, 5, 1, 1, 1, 5, 9};

        System.out.println(minremoval(arr));
    }

    public static int minremoval(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int len = 1; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1)
                    dp[i][j] = 1;
                else {
                    dp[i][j] = Integer.MAX_VALUE / 2;
                    dp[i][j] = dp[i + 1][j] + 1;
                    if (i + 1 <= j && arr[i] == arr[i + 1])
                        dp[i][j] = Math.min(dp[i][j], dp[i + 2][j] + 1);

                    /*
                    转移时，可以将 arr[i]arr[i] 单独考虑删除，
                    则转移 f(i,j)=min(f(i,j),1+f(i+1,j))f(i,j)=min(f(i,j),1+f(i+1,j))。
                    如果 arr[i]==arr[i+1]arr[i]==arr[i+1]，
                    则这两个可以一起删除，转移 f(i,j)=min(f(i,j),1+f(i+2,j))f(i,j)=min(f(i,j),1+f(i+2,j))。
                    如果 arr[i]==arr[k],(i+2<=k<=j)arr[i]==arr[k],(i+2<=k<=j)，
                    则 arr[i]arr[i] 和 arr[k]arr[k] 可以随着区间 [i+1,k−1][i+1,k−1] 一起删除，
                    转移 f(i,j)=min(f(i,j),f(i+1,k−1)+f(k+1,j))f(i,j)=min(f(i,j),f(i+1,k−1)+f(k+1,j))。
                     */

                    for (int k = i + 2; k <= j; k++)
                        if (arr[i] == arr[k])
                            dp[i][j] = Math.min(dp[i][j], dp[i + 1][k - 1] + dp[k + 1][j]);
//                    for (int k = 1; k <= len; k++)
//                        // a[l]==a[j] dp[l+1][j-1]+dp[j+1][r];
//                        // dp[i][j] = min(dp[i+1][j]+1,dp[l+1][j-1]+dp[j+1][r],dp[i+2][j]+1)
//                        if (check(arr, k) && j - k - 1 >= i) {
//                            dp[i][j] = Math.min(dp[i][j], dp[i][j - k - 1] + 1);
//                        }
                }
            }
        return dp[0][n - 1];
    }

    public static boolean check(int[] arr, int len) {
        int n = arr.length;
        int j = n - 1;
        int i = n - len;
        while (i < j) {
            if (arr[i] != arr[j])
                return false;
            i++;
            j--;
        }
        return true;
    }
}
