import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class b0201_2169 {


    public static void main(String[] args) throws IOException {
        int[][] arr;
        int[][] dp;
        int[][] tmp;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer data = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(data.nextToken());
        int m = Integer.parseInt(data.nextToken());
        arr = new int[n][m];
        dp = new int[n][m];
        tmp = new int[2][m];

        // 기본 행렬 생성
        for (int i = 0; i < n; i++) {
            data = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                //System.out.println(data.nextToken());
                arr[i][j] = Integer.parseInt(data.nextToken());
            }
        }

        // 동적 프로그래밍 행렬 생성
        // 1) 첫 번째 행 채우기
        dp[0][0] = arr[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = arr[0][i] + dp[0][i - 1];
        }
        // 2) 나머지 행렬 채우기
        for(int i = 1 ; i< n ; i++){
            // 진행 방향 : 왼쪽 => 오른쪽 // 위와 왼쪽을 비교
            tmp[0][0] = dp[i-1][0] + arr[i][0];
            for (int j = 1 ; j < m ; j++){
                tmp[0][j] = Math.max(dp[i-1][j],tmp[0][j-1]) + arr[i][j];
            }
            // 진행 방향 : 왼쪽 <= 오른쪽 // 위와 오른쪽을 비교
            tmp[1][m-1] = dp[i-1][m-1] + arr[i][m-1];
            for(int j = m-2 ; j > -1 ; j--){
                tmp[1][j] = Math.max(dp[i-1][j],tmp[1][j+1]) + arr[i][j];
            }
            //tmp 배열 : max(max(위,왼쪽),max(위,오른쪽))
            for(int j = 0; j<m ; j++){
                dp[i][j] = Math.max(tmp[0][j],tmp[1][j]);
            }

            System.out.println(dp[i-1][m-1]);

            for(int k = 0; k < 2 ; k++){
                for(int j = 0; j < m ; j++){
                    System.out.print(tmp[k][j]+" ");
                }
                System.out.println();
            }
        }

        System.out.println();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //System.out.println(dp[n-1][m-1]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

    }
}

/*test case
*
5 5
10 25 7 8 13
68 24 -78 63 32
12 -69 100 -29 -25
-16 -22 -57 -33 99
7 -76 -11 77 15
* */