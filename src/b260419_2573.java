import java.util.*;
import java.io.*;

public class b260419_2573 {
    static int N,M;
    static int [][] board;
    static int [][] snapshot;
    static int [] dx = {-1, +1, 0, 0}; // 상하좌우
    static int [] dy = {0, 0, -1, +1};
    static Deque<int []> queue = new ArrayDeque<>();
    static boolean [][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int [N][M];
        snapshot = new int [N][M];
        visited = new boolean [N][M];

        int time = 0;
        int ice_cnt = 0;

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 빙산이 몇개의 덩어리인지 확인
        while(true){

            for(int i = 0; i<N; i++){
                System.arraycopy(board[i], 0, snapshot[i], 0, board[0].length);
            }
            visited = new boolean [N][M];
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(board[i][j] != 0 && !visited[i][j]){
                        ice_cnt ++;
                        queue.addLast(new int [] {i,j});
                        visited[i][j] = true;
                        while(!queue.isEmpty()){
                            int c_x = queue.peek()[0];
                            int c_y = queue.poll()[1];
                            int cnt = 0;
                            for(int k = 0 ; k<4; k++ ){
                                int n_x = c_x + dx[k];
                                int n_y = c_y + dy[k];
                                if(n_x < 0 || n_x >= N || n_y < 0 || n_y >= M) continue;
                                if(board[n_x][n_y] == 0){
                                    cnt ++;
                                }else{
                                    if(visited[n_x][n_y]) continue;
                                    queue.addLast(new int [] {n_x, n_y});
                                    visited[n_x][n_y] = true;
                                }
                            }
                            int change = snapshot[c_x][c_y] - cnt;
                            snapshot[c_x][c_y] = Math.max(change, 0);
                        }
                    }
                }
            }
            if(ice_cnt > 1) break;
            if(ice_cnt == 0) {
                time = 0;
                break;
            }
            ice_cnt = 0;
            for(int i = 0; i<N; i++){
                System.arraycopy(snapshot[i],0, board[i], 0, snapshot[0].length);
            }

            time ++;
        }

        System.out.println(time);

    }
}
