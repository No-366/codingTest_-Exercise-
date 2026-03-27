import java.util.*;
import java.io.*;

public class b260315_11055 {
    static int N;
    static int [] num;
    static int [] dp;
    static int max = 0;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int [N+1];
        dp = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<N+1; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i<N+1; i++){
            int cur = num[i];
            int preMax = 0;
            for(int j = 1; j < i; j++){
                if(cur > num[j]){
                    preMax = Math.max(preMax, dp[j]);
                }
            }
            dp[i] = preMax + cur;
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
