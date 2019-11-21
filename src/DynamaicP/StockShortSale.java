package DynamaicP;/*
 * @author:
 * @date:  2019/11/21/021
 * @description:
 */


import java.util.LinkedList;
import java.util.List;

/*
         input :  stock value array
         output : return the stock series ascend in the max size
 */
public class StockShortSale {

    public static void main(String[] args) {

        int[] arr = {12, 15, 16, 14, 13, 17, 11, 20, 22, 9};
        for (int node : arr)
            System.out.print(node + "  ");
        System.out.println();
        System.out.print(transactionSeries(arr));
    }


    public static int transactionSeries(int[] arr) {

        List<List<Integer>> result = new LinkedList<>();

        // dp[i] :第I天最大递增长度
        // dp[i] =max(dp[i],dp[j]+1) ;
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        List<List<Integer>>[] tempList = new LinkedList[n + 1];
        for (int i = 1; i < n + 1; i++)
            tempList[i] = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            List<Integer> listtemp = new LinkedList<>();
            listtemp.add(arr[i - 1]);
            tempList[i].add(new LinkedList<>(listtemp));
            for (int j = 1; j < i; j++) {
                if (arr[j - 1] < arr[i - 1]) {
                    int temp = dp[i];
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    System.out.printf("current(i,j):     (%d,%d)\r\n", i, j);
                    for (List<Integer> list : tempList[j]) {
                        // 递增序列比当前已知最长的短，则不必更新
                        if (list.size() < temp)
                            continue;

                        tempList[i].add(new LinkedList<>(list));
                        List<Integer> sublist = new LinkedList<>();
                        for (int node : list)
                            sublist.add(node);
                        sublist.add(arr[i - 1]);

                        for (int node : sublist)
                            System.out.print(node + "  ");
                        System.out.println();
                        tempList[i].add(sublist);
                    }
                }
            }
        }
        int cur = dp[1];
        for (int i = 1; i < n + 1; i++)
            if (cur < dp[i])
                cur = dp[i];

        // if return dp[n] .maybe arr[n]  not selected
        return cur;
        // return result;
    }

}
