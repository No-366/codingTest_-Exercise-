import java.util.*;
import java.io.*;

public class b260315_11722 {
    static int N;
    static int [] arr;
    static int [] dp;
    static int max = 0;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(arr));

        for(int i = 0; i < N; i++){
            int len = 0;
            for(int j = 0; j < i; j++){
                if(arr[i] < arr[j]){
                    len = Math.max(len, dp[j]);
                }
            }
            dp[i] = len + 1;
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
