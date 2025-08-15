import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b0209_1947 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long mod = 1000000000;

        long dp[] = new long[1000001];
        dp[1] = 0;
        dp[2] = 1;
        for(int i=3; i<=N; i++){
            dp[i] = (i-1)*(dp[i-1]+dp[i-2])%mod;
        }
        System.out.println(dp[N]);
    }
}
