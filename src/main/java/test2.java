public class test2 {

    public static void main(String[] args) {

        int D = 10;
        int K = 1;
        int[] primeNums = new int[K];
        createPrimeArr(primeNums);
    }

    public  static void createPrimeArr(int[] nums){

        int cnt= 0;
        for(int i=2 ; ;i++)
        {
            // prefer k
            int j=0;
            for(;j<cnt;j++)
                if(i%nums[j]==0)
                    break;
                if(j==cnt)
                {
                    nums[cnt]=i;
                    cnt++;
                }

            if(cnt==nums.length)
                return;

        }
    }



}
