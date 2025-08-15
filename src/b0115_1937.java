import java.util.*;
import java.io.*;

public class b0115_1937{
    static int n;
    static int res = 0;
    static int[][] req = new int[501][501];
    static int[][] dp = new int[501][501];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static int dfs(int y, int x){
        // 만약 dp 배열 내에 이전에 방문한 값이 있다면 해당 값 출력
        if(dp[y][x] != 0) return dp[y][x];
        else{
            dp[y][x] = 1;

            for(int i=0;i<4;i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(ny<0 || ny>=n || nx<0 || nx>=n) continue;

                // 대나무가 많이 있는 지역으로 이동
                if(req[ny][nx] > req[y][x]){
                    // dp 배열 최신화
                    // ny,nx 위치에서 판다가 생존할 수 있는 시간+1과 현재값 중의 최댓값을 저장
                    dp[y][x] = Math.max(dp[y][x], dfs(ny,nx)+1);
                }
            }

            return dp[y][x];
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                req[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int ans = dfs(i,j);
                if(res < ans) res = ans;
            }
        }

        System.out.println(res);
    }
}