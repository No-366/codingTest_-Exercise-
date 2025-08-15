import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class b0112_11048 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//버퍼에 입력 전체 저장
        String[] inputs = br.readLine().split(" ");//버퍼에서 한줄 읽어와 띄어쓰기단위로 분리하여 배열에 저장

        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        //자바 : 배열 생성시 자동으로 0으로 초기화
        int[][] map = new int[N+1][M+1];
        int[][] dp = new int[N+1][M+1];

        //기본 배열 구성 : 0행과 0열은 0으로 냅두고 (1,1)부터 시작
        for(int i=1; i<=N; i++){
            inputs = br.readLine().split(" ");
            for(int j=1; j<=M; j++){
                map[i][j] = Integer.parseInt(inputs[j-1]);
            }
        }

        //[dp] 배열 구성 : 오-아 vs 아-오 중 큰 값으로 dp[i][j]초기화
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                dp[i][j] = Math.max(map[i][j]+dp[i-1][j], map[i][j]+dp[i][j-1]);
            }
        }

        System.out.println(dp[N][M]);
    }
}