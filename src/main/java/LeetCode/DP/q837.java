package LeetCode.DP;

public class q837 {


      public double new21Game(int N, int K, int W) {

        if (N >= K + W - 1) return 1.0; // all possibilities of positions after alice stops playing are from [K, K+W-1]

        double p[] = new double[K + W];
        double prob = 1 / (W + 0.0); // single elem probability

        double prev = 0;

        p[0] = 1; // Since we start from 0, initialize it to 1

        //p[x] = (p[x-1] + p[x-2] + ..... + p[x-W]) * (1/W); (Probabilities of previous possible positions to get to x,
        // multiplied by probability of choosing an elem to get to x)
        //Until K
        // pre  maintains array sum  of + (i-1,i-w)
        for (int i = 1; i <= K; i++) {
            prev = prev - (i - W - 1 >= 0 ? p[i - W - 1] : 0) + p[i - 1];
            p[i] = prev * prob;
        }

        double req = p[K];

        // From K+1, we don't add the p[i-1] term here as it is >= K
        // p(x)=p(k-1)+ p(k-2)+....+p(k-w) we can't get x from x>=k
        for (int i = K + 1; i <= N; i++) {
            prev = prev - (i - W - 1 >= 0 ? p[i - W - 1] : 0);
            p[i] = prev * prob;
            req += p[i];
            //System.out.println(p[i]);
        }

        return req;
    }


}
