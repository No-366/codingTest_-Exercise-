import java.util.*;
import java.io.*;

public class b260301_12865 {
    static int N;
    static int K;
    static int [] w;
    static int [] v;
    static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        w = new int[N];
        v = new int[N];

        dp = new Integer[N][K+1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        int answer = find(N-1, K);
        System.out.println(answer);

    }

    static int find(int num, int limit){

        if(num < 0) return 0;
        System.out.println(num +" "+ limit);
        if(dp[num][limit] == null){

            if(w[num] > limit){
                dp[num][limit] = find(num-1,limit);
            }else{
                dp[num][limit] = Math.max(find(num-1,limit), find(num-1, limit-w[num]) + v[num]);
            }

        }
        return dp[num][limit];
    }
}
