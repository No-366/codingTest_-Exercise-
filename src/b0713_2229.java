import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b0713_2229 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int [] score = new int[N+1];
        for(int i = 1; i < N+1; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }

        int [] dp = new int[N+1];

        for(int i = 1; i < N+1; i++){

            int maxScore = score[i];
            int minScore = score[i];

            for(int j = i-1; j>0 ; j--){

                maxScore = Math.max(maxScore, score[j]);
                minScore = Math.min(minScore, score[j]);

                dp[i] = Math.max(dp[i], (maxScore - minScore + dp[j-1]));

            }
        }

        System.out.println(dp[N]);
    }

}
